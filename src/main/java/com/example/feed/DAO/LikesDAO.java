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

import com.example.feed.DTO.Likes;
import com.example.feed.info.LikesInfo;
import com.example.feed.mapper.LikesMapper;
import com.example.feed.util.CommonsUtil;

@Component
public class LikesDAO {

	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;

	Logger log = CommonsUtil.getLogger("LikesDAO");

	public void create(LikesInfo likeInfo) {
		SqlParameterSource parameters = new MapSqlParameterSource().addValue("like_id", likeInfo.getId())
				.addValue("like_resourceId", likeInfo.getResourceId()).addValue("like_uid", likeInfo.getUid())
				.addValue("like_name", likeInfo.getName()).addValue("like_type", likeInfo.getType());

		String statement = new StringBuilder(" INSERT INTO likes( like_id, like_resourceId, like_uid, like_name, like_type ) ")
				.append(" values(:like_id,:like_resourceId,:like_uid,:like_name,:like_type) ").toString();

		jdbcTemplate.update(statement, parameters);
	}

	public List<Likes> getLikeDetails(String likeType, int resourceId) throws Exception {
		List<Likes> likeDetails = new ArrayList<>();
		try {

			String queryString = new StringBuilder(" SELECT * FROM likes ").append(" WHERE like_type = :likeType ")
					.append(" AND like_resourceId = :resourceId").toString();

			Map<String, Object> params = new HashMap<>();
			params.put("likeType", likeType);
			params.put("resourceId", resourceId);

			likeDetails = jdbcTemplate.query(queryString, params, new LikesMapper());
			return likeDetails;

		} catch (Exception e) {
			throw new Exception("Like details could not be fetched", e);
		}
	}
}
