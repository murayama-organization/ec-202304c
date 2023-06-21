package com.example.form;

import java.util.List;

public class AddItemToShoppingCartForm {
	private String orderItemId;
	private String size;
	private String quantity;
	private List<String> toppingList;

	public String getOrderItemId() {
		return orderItemId;
	}

	public void setOrderItemId(String orderItemId) {
		this.orderItemId = orderItemId;
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
		return "AddItemToShoppingCartForm [orderItemId=" + orderItemId + ", size=" + size + ", quantity=" + quantity
				+ ", toppingList=" + toppingList + "]";
	}
}
