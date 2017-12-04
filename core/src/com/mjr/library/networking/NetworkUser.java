package com.mjr.library.networking;

public class NetworkUser {

	public String userName = "";
	public String ipAddress = "";
	
	public NetworkUser(String userName, String ipAddress){
		this.userName = userName;
		this.ipAddress = ipAddress;
	}

	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getIpAddress() {
		return ipAddress;
	}
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	
}
