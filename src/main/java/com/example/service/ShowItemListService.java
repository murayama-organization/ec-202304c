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
	 * 商品情報を全件取得します.
	 * 
	 * @param line 並び替え名
	 * @return 商品情報一覧
	 */
	public List<Item> showItemList(String line) {
		List<Item> itemList = itemRepository.findAll(line);
		return itemList;

	}

	/**
	 * 商品情報から名前であいまい検索し、取得します.(引数もしくは検索結果が存在しない場合はnullを返します.)
	 * 
	 * @param name 名前
	 * @return 商品情報一覧
	 */
	public List<Item> searchByName(String name, String line) {
		if (name == null) {
			List<Item> itemList = itemRepository.findAll(line);
			return itemList;
		}
		List<Item> itemList = itemRepository.searchByNameContaining(name,line);
		if (itemList.size() == 0) {
			return itemList;
		}
		return itemList;
	}
}
