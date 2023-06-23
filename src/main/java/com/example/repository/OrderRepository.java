package com.example.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.example.domain.Order;
import com.example.domain.OrderItem;
import com.example.domain.OrderTopping;

@Repository
public class OrderRepository {
	// 検索用マッパー
	private static final ResultSetExtractor<List<Order>> ORDER_RESULT_SET_EXTRACTOR = (rs) -> {
		// 注文が入るOrderを生成
		List<Order> orderList = new ArrayList<Order>();
		Order order = new Order();
		orderList.add(order);
		List<OrderItem> orderItemList = null;
		List<OrderTopping> orderToppingList = null;
		int beforeOrderItemId = 0;
		boolean loopCount = true;
		OrderItem orderItem = null;

		while (rs.next()) {
			// 初回のみOrderを詰める
			if (loopCount) {
				// orderテーブルの項目
				order.setId(rs.getInt("o_id"));
				order.setUserId(rs.getInt("o_user_id"));
				order.setStatus(rs.getInt("o_status"));
				order.setTotalPrice(rs.getInt("o_total_price"));
				order.setOrderDate(rs.getDate("o_order_date"));
				order.setDestinationName(rs.getString("o_destination_name"));
				order.setDestinationEmail(rs.getString("o_destination_email"));
				order.setDestinationZipcode(rs.getString("o_destination_zipcode"));
				order.setDestinationPref(rs.getString("o_destination_pref"));
				order.setDestinationMunicipalities(rs.getString("o_destination_municipalities"));
				order.setDestinationAddress(rs.getString("o_destination_address"));
				order.setDestinationTel(rs.getString("o_destination_tel"));
				order.setDeliveryTime(rs.getTimestamp("o_delivery_time"));
				order.setPaymentMethod(rs.getInt("o_payment_method"));
				orderItemList = new ArrayList<OrderItem>();
				order.setOrderItemList(orderItemList);
				loopCount = false;
			}
			int nowOrderItemId = rs.getInt("oItem_id");
			if (nowOrderItemId != beforeOrderItemId) {
				orderItem = new OrderItem();
				orderItem.setId(rs.getInt("oItem_id"));
				orderItem.setItemId(rs.getInt("oItem_item_id"));
				orderItem.setOrderId(rs.getInt("oItem_order_id"));
				orderItem.setQuantity(rs.getInt("oItem_quantity"));
				orderItem.setSize(rs.getString("oItem_size").charAt(0));
				orderToppingList = new ArrayList<OrderTopping>();
				orderItem.setOrderToppingList(orderToppingList);
				orderItemList.add(orderItem);
				beforeOrderItemId = nowOrderItemId;
			}
			// 注文商品だけあって、toppingがない場合はOrderToppingオブジェクトは作らない
			if (rs.getInt("oTopping_id") != 0) {
				OrderTopping orderTopping = new OrderTopping();
				orderTopping.setId(rs.getInt("oTopping_id"));
				orderTopping.setToppingId(rs.getInt("oTopping_topping_id"));
				orderTopping.setOrderItemId(rs.getInt("oTopping_order_item_id"));
				orderItem.getOrderToppingList().add(orderTopping);
			}
		}
		return orderList;
	};
	@Autowired
	private NamedParameterJdbcTemplate template;

	/**
	 * 主キーから注文情報を取得します.
	 * 
	 * @param orderId 注文の主キー
	 * @return 注文
	 */
	public Order load(Integer orderId) {

		StringBuilder sb = new StringBuilder();
		sb.append(
				"SELECT o.id as o_id, o.user_id AS o_user_id, o.status AS o_status, o.total_price AS o_total_price, o.order_date AS o_order_date, o.destination_name AS o_destination_name, o.destination_email AS o_destination_email, o.destination_zipcode AS o_destination_zipcode, o.destination_pref AS o_destination_pref, o.destination_municipalities AS o_destination_municipalities, ");
		sb.append(
				"o.destination_address AS o_destination_address, o.destination_tel AS o_destination_tel, o.delivery_time AS o_delivery_time, o.payment_method AS o_payment_method, ");
		sb.append(
				"oItem.id AS oItem_id, oItem.item_id AS oItem_item_id, oItem.order_id AS oItem_order_id, oItem.quantity AS oItem_quantity, oItem.size AS oItem_size, ");
		sb.append(
				"oTopping.id AS oTopping_id, oTopping.topping_id AS oTopping_topping_id, oTopping.order_item_id AS oTopping_order_item_id ");
		sb.append("FROM orders AS o LEFT OUTER JOIN order_items as oItem ON o.id = oItem.order_id ");
		sb.append("LEFT OUTER JOIN order_toppings as oTopping ON oItem.id = oTopping.order_item_id WHERE o.id = :id");
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", orderId);

		List<Order> orderList = template.query(sb.toString(), param, ORDER_RESULT_SET_EXTRACTOR);

		return orderList.get(0);
	}

	public Order findByUserIdAndStatus(Integer userId, Integer status) {
		// 検索
		SqlParameterSource param = new MapSqlParameterSource().addValue("userId", userId).addValue("status", status);
		StringBuilder sb = new StringBuilder();
		sb.append(
				"SELECT o.id as o_id, o.user_id AS o_user_id, o.status AS o_status, o.total_price AS o_total_price, o.order_date AS o_order_date, o.destination_name AS o_destination_name, o.destination_email AS o_destination_email, o.destination_zipcode AS o_destination_zipcode, o.destination_pref AS o_destination_pref, o.destination_municipalities AS o_destination_municipalities, ");
		sb.append(
				"o.destination_address AS o_destination_address, o.destination_tel AS o_destination_tel, o.delivery_time AS o_delivery_time, o.payment_method AS o_payment_method, ");
		sb.append(
				"oItem.id AS oItem_id, oItem.item_id AS oItem_item_id, oItem.order_id AS oItem_order_id, oItem.quantity AS oItem_quantity, oItem.size AS oItem_size, ");
		sb.append(
				"oTopping.id AS oTopping_id, oTopping.topping_id AS oTopping_topping_id, oTopping.order_item_id AS oTopping_order_item_id ");
		sb.append("FROM orders AS o LEFT OUTER JOIN order_items as oItem ON o.id = oItem.order_id ");
		sb.append(
				"LEFT OUTER JOIN order_toppings as oTopping ON oItem.id = oTopping.order_item_id WHERE o.user_id = :userId AND o.status = :status;");
		List<Order> orderList = template.query(sb.toString(), param, ORDER_RESULT_SET_EXTRACTOR);
		if (orderList.get(0).getId() == null) {
			return null;
		} else {
			return orderList.get(0);
		}
	}

	/**
	 * UsrIdをupdateするリポジトリ.
	 * 
	 * @param sessionId
	 * @param userId
	 */
	public void updateId(Integer sessionId, Integer userId) {
		SqlParameterSource param = new MapSqlParameterSource().addValue("userId", userId).addValue("sessionId",
				sessionId);
		String updateSql = "UPDATE orders SET user_id=:userId WHERE user_id=:sessionId";
		template.update(updateSql, param);
	}

	/**
	 * Orderを追加するリポジトリ.
	 * 
	 * @param order
	 * @return
	 */
	public Integer insert(Order order) {
		SqlParameterSource param = new BeanPropertySqlParameterSource(order);

		// インサート処理
		StringBuilder sb = new StringBuilder();
		sb.append(
				"INSERT INTO orders( user_id,status, total_price, order_date,destination_name,destination_email, destination_zipcode,destination_pref,destination_municipalities,destination_address,destination_tel,delivery_time,payment_method) ");
		sb.append(
				"VALUES(:userId,:status, :totalPrice, :orderDate,:destinationName,:destinationEmail, :destinationZipcode,:destinationPref,:destinationMunicipalities,:destinationAddress,:destinationTel,:deliveryTime,:paymentMethod)");
		KeyHolder keyHolder = new GeneratedKeyHolder();
		String[] keyColumnNames = { "id" };
		template.update(sb.toString(), param, keyHolder, keyColumnNames);
		return keyHolder.getKey().intValue();
	}
}
