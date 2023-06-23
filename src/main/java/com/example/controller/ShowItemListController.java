package com.example.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	 * @param line  並び替え名
	 * @return 商品一覧画面
	 */
	@GetMapping("/show-item-list")
	public String showItemList(Model model, String name, String line) {
		List<Item> itemList = showItemListService.searchByName(name, line);
		if (itemList.size() == 0) {
			itemList = showItemListService.showItemList(line);
			model.addAttribute("message", "該当する商品がありません");
		}
		if (itemList.size() == 0) {
			model.addAttribute("message", "商品は1件も存在しません");
		}

		Map<String, String> lineMap = new HashMap<>();
		lineMap.put("initi", "名前順");
		lineMap.put("low", "価格の安い順");
		lineMap.put("high", "価格の高い順");
		model.addAttribute("lineMap", lineMap);

		model.addAttribute("itemList", itemList);
		model.addAttribute("linename", line);
		model.addAttribute("name", name);
		return "ec/item_list";
	}
}
