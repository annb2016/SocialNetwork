package com.example.feed.model;

import java.util.List;

public class LikesModel {

	private List<String> users;
	private long count;
	
	public List<String> getUsers() {
		return users;
	}
	public void setUsers(List<String> users) {
		this.users = users;
	}
	public long getCount() {
		return count;
	}
	public void setCount(long count) {
		this.count = count;
	}
	public LikesModel(List<String> users, long count) {
		super();
		this.users = users;
		this.count = count;
	}
	public LikesModel() {
	}
	
	
}
