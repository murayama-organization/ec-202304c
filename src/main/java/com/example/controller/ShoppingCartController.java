package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.form.AddItemToShoppingCartForm;

@Controller
@RequestMapping("/shopping-cart")
public class ShoppingCartController {

	@GetMapping("/add-item")
	public String add(AddItemToShoppingCartForm form) {
		return null;
	}
}
