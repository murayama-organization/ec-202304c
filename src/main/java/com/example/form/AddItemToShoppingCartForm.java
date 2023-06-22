package com.example.form;

import java.util.List;

public class AddItemToShoppingCartForm {
	private String itemId;
	private String size;
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
		return "AddItemToShoppingCartForm [orderItemId=" + itemId + ", size=" + size + ", quantity=" + quantity
				+ ", toppingList=" + toppingList + "]";
	}
}
