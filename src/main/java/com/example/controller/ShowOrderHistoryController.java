package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/show-order-history")
public class ShowOrderHistoryController {

	@GetMapping("")
	public String showOrderHistory() {
		return "ec/order_history";
	}
	
}
