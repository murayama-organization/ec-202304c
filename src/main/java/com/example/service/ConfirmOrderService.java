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

/**
 * 注文確認画面の表示に関する処理を行うサービスクラスです.
 * 
 * @author yousuke.murayama
 */
@Service
@Transactional
public class ConfirmOrderService {

	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private ItemRepository itemRepository;
	
	@Autowired
	private ToppingRepository toppingRepository;

	/**
	 * 主キーから注文情報を取得します.
	 * 
	 * @param orderId 注文の主キー
	 * @return 注文
	 */
	public Order showOrder(Integer orderId) {
		Order order = orderRepository.load(orderId);

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

}
