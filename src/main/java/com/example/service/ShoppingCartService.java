package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Order;
import com.example.domain.OrderItem;
import com.example.repository.OrderRepository;

@Service
@Transactional
public class ShoppingCartService {
	private final Integer STATUS = 0;// 注文前
	@Autowired
	private OrderRepository orderRepository;

	public List<OrderItem> showShoppingCartContents(Integer sessionOrUserId) {
		Order order = orderRepository.findByUserIdAndStatus(sessionOrUserId, STATUS);
		List<OrderItem> cartContent = order.getOrderItemList();
		return cartContent;
	}

	public void addItemToShoppingCart() {

	}

	public void removeItemFromShoppingCart(Integer orderItemId) {

	}
}
