package com.example.feed.DTO;

import java.util.List;

import com.example.feed.model.LikesModel;

public class Feed {

	private int uid;
	private int id;
	private String title;
	private String body;
	private List<Comments> comments;
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
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
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
	public List<Comments> getComments() {
		return comments;
	}
	public void setComments(List<Comments> comments) {
		this.comments = comments;
	}
	public Feed(int uid, int id, String title, String body, List<Comments> comments, LikesModel likes) {
		super();
		this.uid = uid;
		this.id = id;
		this.title = title;
		this.body = body;
		this.comments = comments;
		this.likes = likes;
	}
	public Feed() {
	}
	
	
	
}
