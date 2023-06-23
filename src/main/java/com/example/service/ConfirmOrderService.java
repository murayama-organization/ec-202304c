package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Order;
import com.example.repository.OrderRepository;

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
	
	
	/**
	 * 注文確認画面を
	 * 
	 * @param orderId
	 * @return
	 */
	
	
	
	
}
