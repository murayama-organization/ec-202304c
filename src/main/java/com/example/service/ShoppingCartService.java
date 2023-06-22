package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Item;
import com.example.domain.Order;
import com.example.domain.OrderItem;
import com.example.domain.OrderTopping;
import com.example.domain.Topping;
import com.example.repository.ItemRepository;
import com.example.repository.OrderRepository;
import com.example.repository.ToppingRepository;

@Service
@Transactional
public class ShoppingCartService {
	private final Integer STATUS = 0;// 注文前
	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	public ItemRepository itemRepository;
	@Autowired
	public ToppingRepository toppingRepository;

	public List<OrderItem> showShoppingCartContents(Integer sessionOrUserId) {
		// TEST用
		sessionOrUserId = 1111;
		Order order = orderRepository.findByUserIdAndStatus(sessionOrUserId, STATUS);
		List<OrderItem> cartContent = order.getOrderItemList();
		for (OrderItem orderItem : cartContent) {
			Item item = itemRepository.load(orderItem.getItemId());

			List<OrderTopping> orderToppingList = orderItem.getOrderToppingList();
			for (OrderTopping orderTopping : orderToppingList) {
				Topping topping = toppingRepository.findById(orderTopping.getToppingId());
				orderTopping.setTopping(topping);
			}
			orderItem.setItem(item);
		}
		return cartContent;
	}

	public void updateUserId(Integer userId, Integer sessionId) {
		Order noLogingOrder = orderRepository.findByUserIdAndStatus(sessionId, 0);
		orderRepository.updateId(noLogingOrder.getUserId(), userId);
	}

	public void addItemToShoppingCart() {

	}

	public void removeItemFromShoppingCart(Integer orderItemId) {

	}
}
