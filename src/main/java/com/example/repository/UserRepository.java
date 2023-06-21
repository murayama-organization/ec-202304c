package com.example.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.domain.User;

/**
 * ユーザテーブルを操作するリポジトリです.
 * 
 * @author yousuke.murayama
 */
@Repository
public class UserRepository {

	@Autowired
	private NamedParameterJdbcTemplate template;
	
//	private static final RowMapper<User> USER_ROW_MAPPER = new BeanPropertyRowMapper<>(User.class);
	
	/**
	 * ユーザの登録します.
	 * 
	 * @param user ユーザオブジェクト
	 */
	public void insert(User user) {
		SqlParameterSource param = new BeanPropertySqlParameterSource(user);
		
		String sql = "INSERT INTO users(name, email, zipcode, pref, municipalities, address, telephone, password) "
				+ " VALUES(:name, :email, :zipcode, :pref, :municipalities, :address, :telephone, :password);";
		
		template.update(sql, param);
	}
	
}
