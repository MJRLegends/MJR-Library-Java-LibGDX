package com.mjr.library.networking.packets.server;

import com.mjr.library.networking.packets.Packet;

public class ServerPingPongResponse extends Packet{

	public ServerPingPongResponse(String status) {
		super("Server:PingPong", "Status:" + status);
	}

}
