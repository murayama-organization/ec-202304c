package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Item;
import com.example.service.ShowItemListService;

/**
 * 商品情報を操作するコントローラー.
 * 
 * @author miku.ueda
 *
 */
@Controller
@RequestMapping("/show-item")
public class ShowItemListController {

	@Autowired
	private ShowItemListService showItemListService;

	/**
	 * 商品一覧画面を出力します.
	 * 
	 * @param model モデル
	 * @param name  商品名
	 * @return 商品一覧画面
	 */
	@GetMapping("/show-item-list")
	public String showItemList(Model model, String name) {
		List<Item> itemList = showItemListService.searchByName(name);
		if (itemList.size() == 0) {
			itemList = showItemListService.showItemList();
			model.addAttribute("message", "該当する商品がありません");
		}
		model.addAttribute("itemList", itemList);
		return "ec/item_list";
	}
}
