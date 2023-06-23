package com.example.controller;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Order;
import com.example.form.SendOrderForm;
import com.example.service.SendOrderService;

/**
 * 注文をする処理を行うコントローラー.
 * 
 * @author miku.ueda
 *
 */
@Controller
@RequestMapping("/send-order")
public class SendOrderController {
	@Autowired
	private SendOrderService sendOrderService;

	/**
	 * 注文をします.
	 * 
	 * @param form  フォーム
	 * @param model モデル
	 * @return 注文完了画面
	 */
	@PostMapping("/send-order")
	public String sendOrder(@Validated SendOrderForm form, Model model) {
		Order order = sendOrderService.sendOrder(form);
		BeanUtils.copyProperties(form, order);
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		SimpleDateFormat sdf = new SimpleDateFormat("kk");
		String now = sdf.format(timestamp);
		String delevery = form.getDeliveryTime();
//		if(delevery-now<3) {
//			
//		}
		
		return "ec/order_finished";
	}
}
