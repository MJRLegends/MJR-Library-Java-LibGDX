package com.mjr.library.networking.packets.client;

import com.mjr.library.networking.packets.Packet;

public class ClientLoginPacketResponse extends Packet {

	public ClientLoginPacketResponse(String status) {
		super("Client:Login", "Status:" + status);
	}
}
