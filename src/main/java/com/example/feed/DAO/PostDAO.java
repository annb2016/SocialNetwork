package com.example.feed.DAO;

import java.util.*;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;

import com.example.feed.DTO.Post;
import com.example.feed.info.PostInfo;
import com.example.feed.mapper.*;
import com.example.feed.util.CommonsUtil;

@Component
public class PostDAO {

	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;

	Logger log = CommonsUtil.getLogger("PostDAO");

	public void create(PostInfo postInfo) {
		SqlParameterSource parameters = new MapSqlParameterSource().addValue("post_id", postInfo.getId())
				.addValue("post_uid", postInfo.getUid()).addValue("post_title", postInfo.getTitle())
				.addValue("post_body", postInfo.getBody());

		String statement = new StringBuilder(" INSERT INTO post( post_id, post_uid, post_title, post_body) ")
				.append(" values(:post_id,:post_uid,:post_title,:post_body) ").toString();

		jdbcTemplate.update(statement, parameters);
	}

	public void update(PostInfo postInfo) {
		String query = " UPDATE post SET " + "post_title = :post_title, post_body = :post_body, post_uid = :post_uid "
				+ "where post_id = :post_id";

		SqlParameterSource parameters = new MapSqlParameterSource().addValue("post_id", postInfo.getId())
				.addValue("post_uid", postInfo.getUid()).addValue("post_title", postInfo.getTitle())
				.addValue("post_body", postInfo.getBody());

		jdbcTemplate.update(query, parameters);
	}

	public boolean delete(int id) {
		String sqlQuery = " DELETE FROM post where post_id = :id";
		SqlParameterSource parameters = new MapSqlParameterSource().addValue("id", id);
		return jdbcTemplate.update(sqlQuery, parameters) > 0;
	}

	public List<Post> getPostDetails(int pageSize, int pageId) throws Exception {
		List<Post> postDetails = new ArrayList<>();
		try {
			int offset = pageSize * (pageId - 1);

			String queryString = new StringBuilder(" SELECT * FROM post p ").append(" LIMIT :offset, :pageSize ") // 0,10
					.toString();

			Map<String, Object> params = new HashMap<>();
			params.put("pageSize", pageSize);
			params.put("offset", offset);

			postDetails = jdbcTemplate.query(queryString, params, new PostMapper());
			return postDetails;

		} catch (Exception e) {
			throw new Exception("Post details could not be fetched", e);
		}
	}
}
