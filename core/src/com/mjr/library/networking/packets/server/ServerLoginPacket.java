package com.mjr.library.networking.packets.server;

import com.mjr.library.networking.packets.Packet;

public class ServerLoginPacket extends Packet {

	public ServerLoginPacket(String userName, String clientIP) {
		super("Server:Login", "User:" + userName + "IP:" + clientIP);
	}
}
