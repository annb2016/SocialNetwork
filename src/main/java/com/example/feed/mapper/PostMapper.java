package com.example.feed.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.example.feed.DTO.Post;

public class PostMapper implements RowMapper<Post>{

	@Override
	public Post mapRow(ResultSet rs, int rowNum) throws SQLException {
		Post post = new Post();
		
		 post.setId(rs.getInt("post_id"));
		 post.setUid(rs.getInt("post_uid"));
		 post.setTitle(rs.getString("post_title"));
		 post.setBody(rs.getString("post_body"));
		 
		return post;
	}

}
