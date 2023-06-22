package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Item;
import com.example.service.ShowItemDetailService;

/**
 * 商品詳細情報を操作するコントローラー.
 * 
 * @author miku.ueda
 *
 */
@Controller
@RequestMapping("/show-detail")
public class ShowItemDetailController {
	@Autowired
	private ShowItemDetailService showItemDetailService;

	/**
	 * 商品詳細画面を出力します.
	 * 
	 * @param model  モデル
	 * @param itemId 商品ID
	 * @return 商品詳細画面
	 */
	@GetMapping("/show-item-detail")
	public String showItemDetail(Model model, Integer itemId) {
		Item item = showItemDetailService.showItemDetail(itemId);
		model.addAttribute("item", item);
		@SuppressWarnings("unused")
		String qerr = (String) model.getAttribute("qerr");
		System.out.println(qerr);
		model.addAttribute("qerr", qerr);
		return "ec/item_detail";
	}
}
