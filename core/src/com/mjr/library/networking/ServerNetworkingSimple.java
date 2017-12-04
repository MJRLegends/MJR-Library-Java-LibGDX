package com.mjr.library.networking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net.Protocol;
import com.badlogic.gdx.net.ServerSocket;
import com.badlogic.gdx.net.ServerSocketHints;
import com.badlogic.gdx.net.Socket;
import com.badlogic.gdx.net.SocketHints;
import com.mjr.library.networking.packets.Packet;
import com.mjr.library.networking.packets.client.ClientLoginPacketResponse;
import com.mjr.library.networking.packets.client.ClientPingPongPacket;

public abstract class ServerNetworkingSimple implements ApplicationListener {

	private ServerManager serverManager;

	private int port = 0;
	private int clientPort = 0;

	public ServerNetworkingSimple(int port, int clientPort) {
		this.port = port;
		this.clientPort = clientPort;
		setServerManager(new ServerManager());
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public int getClientPort() {
		return clientPort;
	}

	public void setClientPort(int clientPort) {
		this.clientPort = clientPort;
	}

	public List<String> getIpAddresses() {
		List<String> addresses = new ArrayList<String>();
		try {
			Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
			for (NetworkInterface ni : Collections.list(interfaces)) {
				for (InetAddress address : Collections.list(ni.getInetAddresses())) {
					if (address instanceof Inet4Address) {
						addresses.add(address.getHostAddress());
					}
				}
			}
		} catch (SocketException e) {
			e.printStackTrace();
		}
		return addresses;
	}

	public ServerManager getServerManager() {
		return serverManager;
	}

	public void setServerManager(ServerManager serverManager) {
		this.serverManager = serverManager;
	}

	@Override
	public void create() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				ServerSocketHints serverSocketHint = new ServerSocketHints();
				serverSocketHint.acceptTimeout = 0;

				ServerSocket serverSocket = Gdx.net.newServerSocket(Protocol.TCP, getPort(), serverSocketHint);

				while (true) {
					Socket socket = serverSocket.accept(null);
					BufferedReader buffer = new BufferedReader(new InputStreamReader(socket.getInputStream()));

					try {
						String readLine = buffer.readLine();
						if (readLine != "")
							System.out.println("Server: " + readLine);
						if (readLine.length() != 0 && readLine.substring(0, 6).equalsIgnoreCase("Server")) {
							String packetData = readLine.substring(7, readLine.indexOf("/"));
							switch (packetData) {
							case "Login":
								String userName = readLine.substring(readLine.indexOf("User:") + 5);
								userName = userName.substring(0, userName.indexOf("IP:"));
								String ipAddress = readLine.substring(readLine.indexOf("IP:") + 3);								
								getServerManager().addUser(new NetworkUser(userName, ipAddress));
								sendPacket(new ClientLoginPacketResponse("Success"), ipAddress);
								break;
							case "PingPong":
								String user = readLine.substring(readLine.indexOf("User:") + 5);
								for (NetworkUser tempUser : getServerManager().getUsers()) {
									if (tempUser.equals(user)) {
										tempUser.setTimeSinceLastPing(0);
									}
								}
								break;
							default:
								onPacketHandle(readLine);
								break;
							}
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
	}

	@Override
	public void resize(int width, int height) {

	}

	@Override
	public void render() {

	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void dispose() {

	}

	public void sendPacket(Packet packet, String clientIPAddress) {
		try{
			SocketHints socketHints = new SocketHints();
			socketHints.connectTimeout = 4000;
			Socket socket = Gdx.net.newClientSocket(Protocol.TCP, clientIPAddress, getClientPort(), socketHints);
			try {
				socket.getOutputStream().write(packet.toString().getBytes());
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
			socket.dispose();
		}
		catch(Exception e){
			
		}
	}

	public void runPingPongPacket() {
		if (getServerManager().getUsers().size() == 0)
			return;
		for (int i = 0; i < getServerManager().getUsers().size(); i++) {
			NetworkUser tempUser = getServerManager().getUsers().get(i);
			this.sendPacket(new ClientPingPongPacket(tempUser), tempUser.getIpAddress());
		}
	}

	public void runTimeCheck() {
		if (getServerManager().getUsers().size() == 0)
			return;
		for (int i = 0; i < getServerManager().getUsers().size(); i++) {
			NetworkUser tempUser = getServerManager().getUsers().get(i);
			long time = tempUser.getTimeSinceLastPing() + 1;
			tempUser.setTimeSinceLastPing(time);
			long stop2 = System.nanoTime() + TimeUnit.MINUTES.toNanos(2);
			if (stop2 > System.nanoTime()) {
				System.out.println(tempUser.userName + " has timed out");
				getServerManager().removeUser(tempUser);
			}
		}

	}

	protected abstract void onPacketHandle(String readLine);
}
