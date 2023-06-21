package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Item;
import com.example.repository.ItemRepository;

/**
 * 商品情報を操作するサービス.
 * 
 * @author miku.ueda
 *
 */
@Service
@Transactional
public class ShowItemListService {
	@Autowired
	private ItemRepository itemRepository;

	/**
	 * 商品情報から名前であいまい検索し、取得します.(引数もしくは検索結果が存在しない場合、全商品情報を取得します)
	 * 
	 * @param name 名前
	 * @return 商品情報一覧
	 */
	public List<Item> showItemList(String name) {
		if (name == null) {
			List<Item> itemList = itemRepository.findAll();
			return itemList;
		}
		List<Item> itemList = itemRepository.searchByNameContaining(name);
		if (itemList.size() == 0) {
			itemList = itemRepository.findAll();
			return itemList;
		}
		return itemList;

	}
}
