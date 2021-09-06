package com.example.feed.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.example.feed.DTO.Comments;

public class CommentsMapper implements RowMapper<Comments>{

	@Override
	public Comments mapRow(ResultSet rs, int rowNum) throws SQLException {
		Comments comments = new Comments();
		
		comments.setId(rs.getInt("comment_id"));
		comments.setUid(rs.getInt("comment_uid"));
		comments.setPostId(rs.getInt("comment_postid"));
		comments.setName(rs.getString("comment_name"));
		comments.setBody(rs.getString("comment_body"));
		return comments;
	}
	

}
