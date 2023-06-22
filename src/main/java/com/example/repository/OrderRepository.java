package com.example.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.domain.Order;
import com.example.domain.OrderItem;
import com.example.domain.OrderTopping;

@Repository
public class OrderRepository {
	// insert用マッパー
	private static final RowMapper<Order> ORDER_ROW_MAPPER = new BeanPropertyRowMapper<>(Order.class);
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
		System.out.println("==========" + loopCount + "================");
		while (rs.next()) {
			// 初回のみOrderを詰める
			if (loopCount) {
				System.out.println("==========" + loopCount + "================");
				// orderテーブルの項目
				order.setId(rs.getInt("order_id"));
				order.setUserId(rs.getInt("user_id"));
				order.setStatus(rs.getInt("status"));
				order.setTotalPrice(rs.getInt("total_price"));
				order.setOrderDate(rs.getDate("order_date"));
				order.setDestinationName(rs.getString("destination_name"));
				order.setDestinationEmail(rs.getString("destination_email"));
				order.setDestinationZipcode(rs.getString("destination_zipcode"));
				order.setDestinationPref(rs.getString("destination_pref"));
				order.setDestinationMunicipalities(rs.getString("destination_municipalities"));
				order.setDestinationAddress(rs.getString("destination_address"));
				order.setDestinationTel(rs.getString("destination_tel"));
				order.setDeliveryTime(rs.getTimestamp("delivery_time"));
				order.setPaymentMethod(rs.getInt("payment_method"));
				orderItemList = new ArrayList<OrderItem>();
				order.setOrderItemList(orderItemList);
				loopCount = false;
			}
			int nowOrderItemId = rs.getInt("order_item_id");
			if (nowOrderItemId != beforeOrderItemId) {
				orderItem = new OrderItem();
				orderItem.setId(rs.getInt("order_item_id"));
				orderItem.setItemId(rs.getInt("item_id"));
				orderItem.setOrderId(rs.getInt("order_id"));
				orderItem.setQuantity(rs.getInt("quantity"));
				orderItem.setSize(rs.getString("size").charAt(0));
				orderToppingList = new ArrayList<OrderTopping>();
				orderItem.setOrderToppingList(orderToppingList);
				orderItemList.add(orderItem);
			}
			// 注文商品だけあって、toppingがない場合はOrderToppingオブジェクトは作らない
			if (rs.getInt("order_topping_id") != 0) {
				OrderTopping orderTopping = new OrderTopping();
				orderTopping.setId(rs.getInt("order_topping_id"));
				orderTopping.setToppingId(rs.getInt("topping_id"));
				orderTopping.setOrderItemId(rs.getInt("order_item_id"));
				orderItem.getOrderToppingList().add(orderTopping);
			}
		}
		return orderList;
	};
	@Autowired
	private NamedParameterJdbcTemplate template;

	public Order findByUserIdAndStatus(Integer userId, Integer status) {
		// 検索
		StringBuilder sb = new StringBuilder();
		sb.append(
				"SELECT orders.id as order_id, user_id,status, total_price, order_date,destination_name,destination_email, destination_zipcode,destination_pref,destination_municipalities,destination_address,destination_tel,delivery_time,payment_method,");
		sb.append("oItem.id as order_item_id, item_id, order_id,quantity,size,");
		sb.append("oTopping.id as order_topping_id, topping_id,order_item_id ");
		sb.append("FROM orders ");
		sb.append("LEFT OUTER JOIN order_items as oItem on order_id = oItem.order_id ");
		sb.append("LEFT OUTER JOIN order_toppings as oTopping on oItem.id = oTopping.order_item_id ");
		sb.append("WHERE user_id = :userId AND status=:status;");

		System.out.println(sb.toString());
		SqlParameterSource param = new MapSqlParameterSource().addValue("userId", userId).addValue("status", status);

		List<Order> orderList = template.query(sb.toString(), param, ORDER_RESULT_SET_EXTRACTOR);
		if (orderList.size() == 0) {
			return null;
		} else {
			return orderList.get(0);
		}
	}

	public void updateId(Integer sessionId, Integer userId) {
		SqlParameterSource param = new MapSqlParameterSource().addValue("userId", userId).addValue("sessionId",
				sessionId);
		String updateSql = "UPDATE orders SET user_id=:userId WHERE user_id=:sessionId";
		template.update(updateSql, param);
	}
}
