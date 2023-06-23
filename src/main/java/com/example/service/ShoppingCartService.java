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
import com.example.form.AddItemToShoppingCartForm;
import com.example.repository.ItemRepository;
import com.example.repository.OrderItemRepository;
import com.example.repository.OrderRepository;
import com.example.repository.OrderToppingRepository;
import com.example.repository.ToppingRepository;

@Service
@Transactional
public class ShoppingCartService {
	private final Integer STATUS = 0;// 注文前
	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private OrderItemRepository orderItemRepository;
	@Autowired
	private OrderToppingRepository orderToppingRepository;
	@Autowired
	public ItemRepository itemRepository;
	@Autowired
	public ToppingRepository toppingRepository;

	public Order showShoppingCartContents(Integer sessionOrUserId) {
		// TEST用
//		sessionOrUserId = 1111;
		Order order = orderRepository.findByUserIdAndStatus(sessionOrUserId, STATUS);

		if (order == null) {
			return new Order();
		}
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
		order.setOrderItemList(cartContent);
		return order;
	}

	public void updateUserId(Integer userId, Integer sessionId) {
		Order noLogingOrder = orderRepository.findByUserIdAndStatus(sessionId, 0);
		if (noLogingOrder != null) {
			orderRepository.updateId(noLogingOrder.getUserId(), userId);
		}
	}

	public void addItemToShoppingCart(AddItemToShoppingCartForm form, Integer sessionOrUserId) {
		// orderがない場合Orderを作る
		Order order = orderRepository.findByUserIdAndStatus(sessionOrUserId, 0);
		Integer orderId = null;
		if (order == null) {
			order = new Order();
			order.setUserId(sessionOrUserId);
			order.setStatus(0);
			order.setTotalPrice(0);
			orderId = orderRepository.insert(order);
		} else {
			orderId = order.getId();
		}
		// OrderItemオブジェクトの設定
		OrderItem orderItem = new OrderItem();
		orderItem.setItemId(Integer.valueOf(form.getItemId()));
		orderItem.setOrderId(orderId);
		orderItem.setQuantity(Integer.valueOf(form.getQuantity()));
		orderItem.setSize(form.getSize().charAt(0));
		Integer orderItemId = orderItemRepository.insert(orderItem);
		List<String> toppingList = form.getToppingList();
		if (toppingList == null) {
			return;
		}
		for (String toppingId : form.getToppingList()) {
			OrderTopping orderTopping = new OrderTopping();
			orderTopping.setToppingId(Integer.valueOf(toppingId));
			orderTopping.setOrderItemId(orderItemId);
			orderToppingRepository.insert(orderTopping);
		}
	}

	public void removeItemFromShoppingCart(Integer orderItemId) {
		orderItemRepository.deleteById(orderItemId);
	}
}
