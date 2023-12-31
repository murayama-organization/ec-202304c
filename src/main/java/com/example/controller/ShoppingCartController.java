package com.example.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.domain.LoginUser;
import com.example.domain.Order;
import com.example.domain.OrderItem;
import com.example.domain.User;
import com.example.form.AddItemToShoppingCartForm;
import com.example.service.ShoppingCartService;
import com.example.service.ShowItemDetailService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/shopping-cart")
public class ShoppingCartController {
	@Autowired
	private HttpSession httpSession;

	@Autowired
	public ShoppingCartService cartService;

	@Autowired
	public ShowItemDetailService showItemDetailService;

	@GetMapping("/show-cart")
	public String showCart(Model model, @AuthenticationPrincipal LoginUser loginUser) {
		User user = null;
		if (loginUser != null) {
			user = loginUser.getUser();
		}

		Integer accessId = null;
		if (user == null) {
			accessId = httpSession.getId().hashCode();
		} else {
			accessId = user.getId();
			cartService.updateUserId(accessId, httpSession.getId().hashCode());
		}
		Order order = cartService.showShoppingCartContents(accessId);
		System.out.println(order);
		// 何も注文してない状態で遷移
		if (order.getId() == null) {
			model.addAttribute("noItemMessage", "カートに商品がありません");
			return "ec/cart_list";
		}
		// 注文全削除状態で遷移
		List<OrderItem> showShoppingCartContents = order.getOrderItemList();
		if (showShoppingCartContents == null || showShoppingCartContents.size() == 0) {
			model.addAttribute("noItemMessage", "カートに商品がありません");
			return "ec/cart_list";
		}
		// orderItemごとの金額を出して格納
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		for (OrderItem orderItem : showShoppingCartContents) {
			Integer orderPrice = 0;
			if (orderItem.getSize().equals('M')) {
				orderPrice = orderItem.getSubTotal();
			} else if (orderItem.getSize().equals('L')) {
				orderPrice = orderItem.getSubTotal();
			} else {
				System.err.println("サイズが存在しません");
			}
			map.put(orderItem.getId(), orderPrice);
		}
		model.addAttribute("orderPrice", map);
		model.addAttribute("cartItem", showShoppingCartContents);
		model.addAttribute("totalTax", order.getTax());
		model.addAttribute("totalPrice", order.getCalcPrice());
		//追加
		model.addAttribute("order", order);
		return "ec/cart_list";
	}

	@PostMapping("/add-item")
	public String addItem(AddItemToShoppingCartForm form, RedirectAttributes redirectAttributes) {
		System.out.println(form);
		if (form.getQuantity() == null) {
			redirectAttributes.addFlashAttribute("qerr", "数量を選択してください");
			return "redirect:/show-detail/show-item-detail?itemId=" + form.getItemId();
		}
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

	@PostMapping("/remove-order-item")
	public String removeOrderItemFromShoppingCart(Integer orderItemId) {
		cartService.removeItemFromShoppingCart(orderItemId);
		return "redirect:/shopping-cart/show-cart";
	}
}
