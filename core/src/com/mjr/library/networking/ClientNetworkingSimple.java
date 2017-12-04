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

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net.Protocol;
import com.badlogic.gdx.net.ServerSocket;
import com.badlogic.gdx.net.ServerSocketHints;
import com.badlogic.gdx.net.Socket;
import com.badlogic.gdx.net.SocketHints;
import com.mjr.library.networking.packets.Packet;
import com.mjr.library.networking.packets.server.ServerPingPongResponse;

public abstract class ClientNetworkingSimple implements ApplicationListener {

	private boolean connected = false;
	private int port = 0;
	private int serverPort = 0;

	public ClientNetworkingSimple(int port, int serverPort) {
		this.port = port;
		this.serverPort = serverPort;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public int getServerPort() {
		return serverPort;
	}

	public void setServerPort(int serverPort) {
		this.serverPort = serverPort;
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

	public boolean isConnected() {
		return connected;
	}

	public void setConnected(boolean connected) {
		this.connected = connected;
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
							System.out.println("Client: " + readLine);
						if (readLine.length() != 0 && readLine.substring(0, 6).equalsIgnoreCase("Client")) {
							switch (readLine.substring(7, readLine.indexOf("/"))) {
							case "Login":
								if (readLine.contains("Success"))
									setConnected(true);
								else
									setConnected(false);
								break;
							case "PingPong":
								String ip = readLine.substring(readLine.indexOf("IP:") + 3);
								sendPacket(new ServerPingPongResponse("Success"), ip);
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

	public void sendPacket(Packet packet, String serverIPAddress) {
		try {
			SocketHints socketHints = new SocketHints();
			socketHints.connectTimeout = 4000;
			Socket socket = Gdx.net.newClientSocket(Protocol.TCP, serverIPAddress, getServerPort(), socketHints);
			try {
				socket.getOutputStream().write(packet.toString().getBytes());
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
			socket.dispose();
		} catch (Exception e) {

		}
	}

	protected abstract void onPacketHandle(String readLine);
}
