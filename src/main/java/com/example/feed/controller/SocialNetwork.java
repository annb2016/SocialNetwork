package com.example.feed.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.feed.DAO.CommentsDAO;
import com.example.feed.DAO.LikesDAO;
import com.example.feed.DAO.PostDAO;
import com.example.feed.DTO.Feed;
import com.example.feed.info.CommentsInfo;
import com.example.feed.info.LikesInfo;
import com.example.feed.info.PostInfo;
import com.example.feed.service.FeedService;
import com.example.feed.util.CommonsUtil;

import io.swagger.annotations.ApiOperation;



/**
 * @author Annie Borah
 *
 */
@RestController
@RequestMapping("/api")
public class SocialNetwork {

	Logger log = CommonsUtil.getLogger("Controller");
	
	@Autowired
	private PostDAO postDAO;
	
	@Autowired
	private CommentsDAO commentsDAO;

	@Autowired
	private LikesDAO likesDAO;
	
	@Autowired
	private FeedService feedService;
	
	
//	Post related CRUD APIs
	@RequestMapping(value = "/v1/createPost", method = RequestMethod.POST)
	@ApiOperation(value = "Create a new post")
	public @ResponseBody ResponseEntity<?> createPost(
			@RequestBody PostInfo postPayload) throws Exception {
		
		log.info("Creating new post");
		try {
			postDAO.create(postPayload);
		}catch (Exception e) {
			log.error("Error in creating new post", e);
			throw new Exception(e);
		}
		return new ResponseEntity<>("Successfully created new post", HttpStatus.OK);
	}
	
	@RequestMapping(value = "/v1/createPost", method = RequestMethod.PUT)
	@ApiOperation(value = "Update an existing post")
	public @ResponseBody ResponseEntity<?> updatePost(
			@RequestBody PostInfo postPayload) throws Exception {
		
		log.info("Updating post");
		try {
			postDAO.update(postPayload);
		}catch (Exception e) {
			log.error("Error in updating post", e);
			throw new Exception(e);
		}
		return new ResponseEntity<>("Successfully updated post", HttpStatus.OK);
	}
	
	@RequestMapping(value = "/v1/feeds/", method = RequestMethod.GET)
	@ApiOperation(value = "Get all posts in feed",
					notes = "Provide the page Id and the page size to get the feed of the required page")
	@ResponseBody
	public List<Feed> getFeed(
			@RequestParam(required = true, value = "pageSize") int pageSize,
			@RequestParam(required = true, value = "pageId") int pageId) throws Exception {
		
		log.info("Getting feed details");
		pageSize = 10;
		List<Feed> feed = new ArrayList<>();
		try {
			feed = feedService.getFeeds(pageSize, pageId);
		}catch (Exception e) {
			log.error("Error in Controller", e);
			throw new Exception(e);
		}
		
		return feed;
	}
	
	@RequestMapping(value = "/v1/deletePost", method = RequestMethod.DELETE)
	@ApiOperation(value = "Delete a post")
	public @ResponseBody ResponseEntity<?> deletePost(
			@RequestParam int postid) throws Exception {
		
		log.info("Deleting post");
		try {
			postDAO.delete(postid);
		}catch (Exception e) {
			log.error("Error in deleting post", e);
			throw new Exception(e);
		}
		return new ResponseEntity<>("Successfully deleted post", HttpStatus.OK);
	}
	
	
//	Create comments API
	@RequestMapping(value = "/v1/createComment", method = RequestMethod.POST)
	@ApiOperation(value = "Create a new comment")
	public @ResponseBody ResponseEntity<?> createComment(
			@RequestBody CommentsInfo commentPayload) throws Exception {
		
		log.info("Creating new comment");
		try {
			commentsDAO.create(commentPayload);
		}catch (Exception e) {
			log.error("Error in creating new comment", e);
			throw new Exception(e);
		}
		return new ResponseEntity<>("Successfully created new comment", HttpStatus.OK);
	}
	
	
//	Create likes API
	@RequestMapping(value = "/v1/createLike", method = RequestMethod.POST)
	@ApiOperation(value = "Create a new like")
	public @ResponseBody ResponseEntity<?> createLike(
			@RequestBody LikesInfo likePayload) throws Exception {
		
		log.info("Creating new like");
		try {
			likesDAO.create(likePayload);
		}catch (Exception e) {
			log.error("Error in creating new like", e);
			throw new Exception(e);
		}
		return new ResponseEntity<>("Successfully created new like", HttpStatus.OK);
	}
	
}
