package com.example.feed.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.example.feed.DTO.Likes;

public class LikesMapper implements RowMapper<Likes> {

	@Override
	public Likes mapRow(ResultSet rs, int rowNum) throws SQLException {
		Likes likes = new Likes();
		
		likes.setId(rs.getInt("like_id"));
		likes.setUid(rs.getInt("like_uid"));
		likes.setResourceId(rs.getInt("like_resourceId"));
		likes.setName(rs.getString("like_name"));
		likes.setType(rs.getString("like_type"));
		return likes;

	}

	
}
