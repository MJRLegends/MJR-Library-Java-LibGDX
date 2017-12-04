package com.mjr.library.networking.packets.client;

import com.mjr.library.networking.NetworkUser;
import com.mjr.library.networking.packets.Packet;

public class ClientPingPongPacket extends Packet {

	public ClientPingPongPacket(NetworkUser user) {
		super("Client:PingPong", user.toString());
	}

}
