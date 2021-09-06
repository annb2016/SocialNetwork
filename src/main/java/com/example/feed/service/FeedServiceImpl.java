package com.example.feed.service;

import java.util.*;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.feed.DAO.CommentsDAO;
import com.example.feed.DAO.LikesDAO;
import com.example.feed.DAO.PostDAO;
import com.example.feed.DTO.Comments;
import com.example.feed.DTO.Feed;
import com.example.feed.DTO.Likes;
import com.example.feed.DTO.Post;
import com.example.feed.model.LikesModel;
import com.example.feed.util.CommonsUtil;



/**
 * @author Annie Borah
 *
 */

@Service
public class FeedServiceImpl implements FeedService {

	@Autowired
	private PostDAO postDAO;

	@Autowired
	private CommentsDAO commentsDAO;

	@Autowired
	private LikesDAO likesDAO;

	Logger log = CommonsUtil.getLogger("FeedService");

	@Override
	public List<Feed> getFeeds(int pageSize, int pageId) throws Exception {
		List<Feed> feedList = new ArrayList<>();

		try {
			log.info("Inside FeedService");
			List<Post> postList = postDAO.getPostDetails(pageSize, pageId);
			List<Comments> commentsList = new ArrayList<>();
			List<Likes> postLikesList = new ArrayList<>();

			for (Post p : postList) {
				Feed feed = new Feed();

				feed.setId(p.getId());
				feed.setUid(p.getUid());
				feed.setTitle(p.getTitle());
				feed.setBody(p.getBody());

				commentsList = commentsDAO.getCommentDetails(feed.getId());
				postLikesList = likesDAO.getLikeDetails("feed", p.getId());

				long countLikesForPost = postLikesList.stream().count();

//				setting likes for post
				LikesModel likes = new LikesModel();
				List<String> users = postLikesList.stream().map(e -> e.getName()).collect(Collectors.toList());
				likes.setCount(countLikesForPost);
				likes.setUsers(users);

//				setting likes for comments
				for (Comments eachComment : commentsList) {
					LikesModel commentLikes = new LikesModel();

					List<Likes> commentLikesList = likesDAO.getLikeDetails("comments", eachComment.getId());
					long countLikesForComments = commentLikesList.stream().count();
					List<String> likeUsers = commentLikesList.stream().map(e -> e.getName())
							.collect(Collectors.toList());
					commentLikes.setCount(countLikesForComments);
					commentLikes.setUsers(likeUsers);
					if (commentLikes != null) {
						eachComment.setLikes(commentLikes);
					}
				}

				feed.setComments(commentsList);
				feed.setLikes(likes);

				feedList.add(feed);
			}
		} catch (Exception e) {
			log.error("Error in constructing feed details", e);
			throw new Exception(e);
		}

		return feedList;
	}

}
