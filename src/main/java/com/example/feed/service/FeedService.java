package com.example.feed.service;

import java.util.List;

import com.example.feed.DTO.Feed;
import com.example.feed.info.PostInfo;

public interface FeedService {

	public List<Feed> getFeeds(int pageSize, int pageId) throws Exception;
	
}
