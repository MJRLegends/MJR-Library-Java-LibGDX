package com.mjr.library.networking.packets;

public abstract class Packet {

	// Format: Client/Server:Type/Data
	
	private String header;
	private String body;

	public Packet(String header, String body) {
		this.header = header;
		this.body = body;
	}

	public String getHeader() {
		return header;
	}

	public void setHeader(String header) {
		this.header = header;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	@Override
	public String toString() {
		return this.header + "/" + this.body;
	}
}
