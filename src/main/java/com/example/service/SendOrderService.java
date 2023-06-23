package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Order;
import com.example.form.SendOrderForm;
import com.example.repository.OrderRepository;

/**
 * 注文に関する業務処理を行うサービスクラスです.
 * 
 * @author miku.ueda
 *
 */
@Service
@Transactional
public class SendOrderService {
	@Autowired
	private OrderRepository orderRepository;

	/**
	 * 注文状況を支払い方法に応じて更新します.
	 * 
	 * @param form フォーム
	 * @return 注文情報
	 */
	public Order sendOrder(SendOrderForm form) {
		Order order = orderRepository.load(form.getOrderId());
		Integer status = 0;
		if ("1".equals(form.getPaymentMethod())) {
			status = 1;
		} else if ("2".equals(form.getPaymentMethod())) {
			status = 2;
		}
		orderRepository.updateStatus(order, status);
		return order;
	}
}
