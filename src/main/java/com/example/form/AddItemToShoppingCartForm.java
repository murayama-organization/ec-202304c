package com.example.form;

import java.util.List;

import jakarta.validation.constraints.Pattern;

public class AddItemToShoppingCartForm {
	private String itemId;
	private String size;
	@Pattern(regexp = "/^([+-])?([0-9]+)(\\.)?([0-9]+)?$/", message = "数量を選択")
	private String quantity;
	private List<String> toppingList;

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public List<String> getToppingList() {
		return toppingList;
	}

	public void setToppingList(List<String> toppingList) {
		this.toppingList = toppingList;
	}

	@Override
	public String toString() {
		return "AddItemToShoppingCartForm [itemId=" + itemId + ", size=" + size + ", quantity=" + quantity
				+ ", toppingList=" + toppingList + "]";
	}
}
