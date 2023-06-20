package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/showList")
public class ShowItemListController {
	@GetMapping("")
	public String showList() {
		return "ec/item_list";
	}
}
