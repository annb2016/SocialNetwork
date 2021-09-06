package com.example.feed.DTO;

import com.example.feed.model.LikesModel;

public class Comments {

	private int postId;
	private int id;
	private int uid;
	private String name;
	private String body;
	private LikesModel likes;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public int getPostId() {
		return postId;
	}
	public void setPostId(int postId) {
		this.postId = postId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public LikesModel getLikes() {
		return likes;
	}
	public void setLikes(LikesModel likes) {
		this.likes = likes;
	}
	public Comments(int postId, int id, int uid, String name, String body, LikesModel likes) {
		super();
		this.postId = postId;
		this.id = id;
		this.uid = uid;
		this.name = name;
		this.body = body;
		this.likes = likes;
	}
	public Comments() {
	}
	
	
	
}
