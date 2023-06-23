package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/show-top-page")
public class ShowTopPage {

	/**
	 * 注目確認画面を表示します.
	 * 
	 * @param orderId 注文の主キー
	 * @return 注文確認画面
	 */
	@GetMapping("/top")
	public String showTopPage() {
		return "ec/top";
	}

}
