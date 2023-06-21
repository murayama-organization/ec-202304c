package com.example.domain;

import java.util.List;

/**
 * 注文商品情報を表すドメイン.
 * 
 * @author miku.ueda
 *
 */
public class OrderItem {
	/** id */
	private Integer id;
	/** 商品id */
	private Integer itemId;
	/** オーダーid */
	private Integer orderId;
	/** 数量 */
	private Integer quantity;
	/** サイズ */
	private Character size;
	/** 商品 */
	private Item item;
	/** トッピングリスト */
	private List<OrderTopping> orderToppingList;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getItemId() {
		return itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Character getSize() {
		return size;
	}

	public void setSize(Character size) {
		this.size = size;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public List<OrderTopping> getOrderToppingList() {
		return orderToppingList;
	}

	public void setOrderToppingList(List<OrderTopping> orderToppingList) {
		this.orderToppingList = orderToppingList;
	}

	@Override
	public String toString() {
		return "OrderItem [id=" + id + ", itemId=" + itemId + ", orderId=" + orderId + ", quantity=" + quantity
				+ ", size=" + size + ", item=" + item + ", orderToppingList=" + orderToppingList + "]";
	}

	public int getSubTotal() {
		int totalPrice = 0;
		int quantity = this.getQuantity();
		List<OrderTopping> toppingList = this.getOrderToppingList();
		if (this.getSize().equals('L')) {
			totalPrice += this.item.getPriceL();
			for (var topping : toppingList) {
				totalPrice += topping.getTopping().getPriceL();
			}
		} else if (this.getSize().equals('M')) {
			totalPrice += this.item.getPriceM();
			for (var topping : toppingList) {
				totalPrice += topping.getTopping().getPriceM();
			}
		}
		totalPrice *= quantity;
		return totalPrice;
	}
}
