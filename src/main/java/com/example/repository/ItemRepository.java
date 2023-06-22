package com.example.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.domain.Item;

/**
 * itemsテーブルを操作するリポジトリ.
 * 
 * @author miku.ueda
 *
 */
@Repository
public class ItemRepository {

	/**
	 * Itemオブジェクトを生成するローマッパー.
	 */
	private static final RowMapper<Item> ITEM_ROW_MAPPER = new BeanPropertyRowMapper<>(Item.class);

	@Autowired
	private NamedParameterJdbcTemplate template;

	/**
	 * 商品一覧情報を値段の安い順で取得します.
	 * 
	 * @return 全商品一覧(商品が存在しない場合はサイズ0件の商品一覧を返します)
	 */
	public List<Item> findAll(String line) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT id,name,description,price_m,price_l,image_path,deleted FROM items ORDER BY ");
		if ("low".equals(line)) {
			sql.append("price_m ASC, id");
		} else if ("high".equals(line)) {
			sql.append("price_m DESC ,id");
		} else if ("initi".equals(line)) {
			sql.append("name, id");
		}
		List<Item> itemList = template.query(sql.toString(), ITEM_ROW_MAPPER);
		return itemList;
	}

	/**
	 * 商品一覧情報から商品名であいまい検索をし、値段の安い順で取得します.
	 * 
	 * @param name 商品名
	 * @return 商品一覧(商品が存在しない場合はサイズ0件の商品一覧を返します)
	 */
	public List<Item> searchByNameContaining(String name) {
		String sql = "SELECT id,name,description,price_m,price_l,image_path,deleted FROM items WHERE name LIKE :name ORDER BY price_m;";
		SqlParameterSource param = new MapSqlParameterSource().addValue("name", "%" + name + "%");
		List<Item> itemList = template.query(sql, param, ITEM_ROW_MAPPER);
		return itemList;
	}

	/**
	 * 主キーから商品情報を取得します.
	 * 
	 * @param id 検索したい商品ID
	 * @return 検索された商品情報
	 */
	public Item load(Integer id) {
		String sql = "SELECT id,name,description,price_m,price_l,image_path,deleted FROM items WHERE id=:id;";
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
		Item item = template.queryForObject(sql, param, ITEM_ROW_MAPPER);
		return item;
	}
}
