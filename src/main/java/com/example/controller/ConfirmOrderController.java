package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Order;
import com.example.domain.OrderItem;
import com.example.service.ConfirmOrderService;

/**
 * 注目確認画面を表示する処理を行うコントローラーです.
 * 
 * @author yousuke.murayama
 */
@Controller
@RequestMapping("/confirm-order")
public class ConfirmOrderController {

	@Autowired
	private ConfirmOrderService confirmOrderService;

	/**
	 * 注目確認画面を表示します.
	 * 
	 * @param orderId 注文の主キー
	 * @return 注文確認画面
	 */
	@GetMapping("/to-confirm-order")
	public String toConfirmOrder(Integer orderId, Model model) {
		Order order = confirmOrderService.showOrder(orderId);
		
		System.out.println(order);
		for(OrderItem orderItem : order.getOrderItemList()) {
			System.out.println(orderItem);
		}

		model.addAttribute("order", order);
		
		model.addAttribute("totalTax", order.getTax());
		model.addAttribute("totalPrice", order.getCalcPrice());

		return "ec/order_confirm";

	}

}
