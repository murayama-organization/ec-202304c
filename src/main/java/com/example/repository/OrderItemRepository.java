package com.example.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.example.domain.OrderItem;

@Repository
public class OrderItemRepository {
	@Autowired
	private NamedParameterJdbcTemplate template;

	public Integer insert(OrderItem orderItem) {
		SqlParameterSource param = new BeanPropertySqlParameterSource(orderItem);

		// インサート処理
		StringBuilder sb = new StringBuilder();
		sb.append("INSERT INTO order_items(item_id , order_id , quantity, size) ");
		sb.append("VALUES(:itemId,:orderId, :quantity,:size);");
		KeyHolder keyHolder = new GeneratedKeyHolder();
		String[] keyColumnNames = { "id" };
		template.update(sb.toString(), param, keyHolder, keyColumnNames);
		return keyHolder.getKey().intValue();
	}

	public void deleteById(Integer id) {
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
		String sql = "DELETE FROM order_items WHERE id = :id";
		template.update(sql, param);
	}
}
