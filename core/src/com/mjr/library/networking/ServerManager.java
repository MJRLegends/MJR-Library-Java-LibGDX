package com.mjr.library.networking;

import java.util.ArrayList;
import java.util.List;

public class ServerManager {

	public ServerManager(){
		
	}
	
	public List<NetworkUser> users = new ArrayList<NetworkUser>();

	public List<NetworkUser> getUsers() {
		return users;
	}
	
	public void setUsers(List<NetworkUser> users) {
		this.users = users;
	}
	
	public void addUsers(NetworkUser user) {
		this.users.add(user);
	}
}
