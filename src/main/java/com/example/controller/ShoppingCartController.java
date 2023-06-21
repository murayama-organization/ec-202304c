package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.OrderItem;
import com.example.domain.OrderTopping;
import com.example.domain.User;
import com.example.form.AddItemToShoppingCartForm;
import com.example.service.ShoppingCartService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/shopping-cart")
public class ShoppingCartController {
	@Autowired
	private HttpSession httpSession;

	@Autowired
	public ShoppingCartService cartService;

	@GetMapping("/show-cart")
	public String showCart() {
		User user = (User) httpSession.getAttribute("currentUser");
		Integer accessId = null;
		if (user == null) {
			accessId = httpSession.getId().hashCode();
		} else {
			accessId = user.getId();
		}
		List<OrderItem> showShoppingCartContents = cartService.showShoppingCartContents(accessId);
		for (OrderItem item : showShoppingCartContents) {
			item.setItem();
			List<OrderTopping> orderToppingList = item.getOrderToppingList();
			for (OrderTopping orderTopping : orderToppingList) {

			}
		}
		return "ec/cart_list";
	}

	@GetMapping("/add-item")
	public String addItem(AddItemToShoppingCartForm form) {
		return null;
	}

}
