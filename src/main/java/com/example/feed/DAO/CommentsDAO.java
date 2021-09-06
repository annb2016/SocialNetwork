package com.example.feed.DAO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;

import com.example.feed.DTO.Comments;
import com.example.feed.info.CommentsInfo;
import com.example.feed.mapper.CommentsMapper;
import com.example.feed.util.CommonsUtil;

@Component
public class CommentsDAO {

	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;

	Logger log = CommonsUtil.getLogger("CommentsDAO");

	public void create(CommentsInfo commentInfo) {
		SqlParameterSource parameters = new MapSqlParameterSource().addValue("comment_id", commentInfo.getId())
				.addValue("comment_postid", commentInfo.getPostid()).addValue("comment_uid", commentInfo.getUid())
				.addValue("comment_name", commentInfo.getName()).addValue("comment_body", commentInfo.getBody());

		String statement = new StringBuilder(" INSERT INTO comments( comment_id, comment_postid, comment_uid, comment_name, comment_body) ")
				.append(" values(:comment_id,:comment_postid,:comment_uid,:comment_name,:comment_body) ").toString();

		jdbcTemplate.update(statement, parameters);
	}

	public List<Comments> getCommentDetails(int postid) throws Exception {
		List<Comments> commentDetails = new ArrayList<>();
		try {

			String queryString = new StringBuilder(" SELECT * FROM comments ")
					.append(" WHERE comment_postid = :postid ").toString();

			Map<String, Object> params = new HashMap<>();
			params.put("postid", postid);

			commentDetails = jdbcTemplate.query(queryString, params, new CommentsMapper());
			return commentDetails;

		} catch (Exception e) {
			throw new Exception("Comment details could not be fetched", e);
		}
	}
}
