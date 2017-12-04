package com.mjr.library.networking;

import java.util.ArrayList;
import java.util.List;

public class ServerManager {

	public ServerManager() {

	}

	private List<NetworkUser> users = new ArrayList<NetworkUser>();

	public List<NetworkUser> getUsers() {
		return users;
	}

	public void setUsers(List<NetworkUser> users) {
		this.users = users;
	}

	public void addUser(NetworkUser user) {
		this.users.add(user);
	}

	public void removeUser(NetworkUser user) {
		for (int i = 0; i < this.users.size(); i++) {
			if (this.users.get(i) == user)
				this.users.remove(i);
		}

	}
}
