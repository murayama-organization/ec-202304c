package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.OrderItem;
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
	public String showCart(Model model) {
		User user = (User) httpSession.getAttribute("currentUser");
		Integer accessId = null;
		if (user == null) {
			accessId = httpSession.getId().hashCode();
		} else {
			accessId = user.getId();
		}
		List<OrderItem> showShoppingCartContents = cartService.showShoppingCartContents(accessId);
		System.out.println(showShoppingCartContents);
		model.addAttribute("cartItem", showShoppingCartContents);
		return "ec/cart_list";
	}

	@PostMapping("/add-item")
	public String addItem(AddItemToShoppingCartForm form) {
		User user = (User) httpSession.getAttribute("currentUser");
		Integer accessId = null;
		if (user == null) {
			accessId = httpSession.getId().hashCode();
		} else {
			accessId = user.getId();
		}
		cartService.addItemToShoppingCart(form, accessId);
		return "redirect:/shopping-cart/show-cart";
	}

}
