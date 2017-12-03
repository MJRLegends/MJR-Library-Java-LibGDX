package com.mjr.library.networking.packets;


public class TestPacket extends Packet{

	public TestPacket(String header, String body) {
		super(header, body);
	}

	@Override
	public void handlePacket(String data) {
		if(data != "")
			System.out.println(data);
	}
}
