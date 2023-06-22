package com.example.domain;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * 注文を表すドメインです.
 * 
 * @author yousuke.murayama
 *
 */
public class Order {
	/** 主キー（ID） */
	private Integer id;
	/** ユーザの主キー（ID） */
	private Integer userId;
	/** 状態 */
	private Integer status;
	/** 合計金額 */
	private Integer totalPrice;
	/** 注文日 */
	private Date orderDate;
	/** 宛先氏名 */
	private String destinationName;
	/** 宛先Eメール */
	private String destinationEmail;
	/** 宛先郵便番号 */
	private String destinationZipcode;
	/** 都道府県 */
	private String destinationPref;
	/** 市町村 */
	private String destinationMunicipalities;
	/** 宛先番地 */
	private String destinationAddress;
	/** 宛先TEL */
	private String destinationTel;
	/** 配達時間 */
	private Timestamp deliveryTime;
	/** 支払方法 */
	private Integer paymentMethod;
	/** ユーザ */
	private User user;
	/** 注文商品リスト */
	private List<OrderItem> orderItemList;

	@Override
	public String toString() {
		return "Order [id=" + id + ", userId=" + userId + ", status=" + status + ", totalPrice=" + totalPrice
				+ ", orderDate=" + orderDate + ", destinationName=" + destinationName + ", destinationEmail="
				+ destinationEmail + ", destinationZipcode=" + destinationZipcode + ", destinationPref="
				+ destinationPref + ", destinationMunicipalities=" + destinationMunicipalities + ", destinationAddress="
				+ destinationAddress + ", destinationTel=" + destinationTel + ", deliveryTime=" + deliveryTime
				+ ", paymentMethod=" + paymentMethod + ", user=" + user + ", orderItemList=" + orderItemList + "]";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Integer totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public String getDestinationName() {
		return destinationName;
	}

	public void setDestinationName(String destinationName) {
		this.destinationName = destinationName;
	}

	public String getDestinationEmail() {
		return destinationEmail;
	}

	public void setDestinationEmail(String destinationEmail) {
		this.destinationEmail = destinationEmail;
	}

	public String getDestinationZipcode() {
		return destinationZipcode;
	}

	public void setDestinationZipcode(String destinationZipcode) {
		this.destinationZipcode = destinationZipcode;
	}

	public String getDestinationPref() {
		return destinationPref;
	}

	public void setDestinationPref(String destinationPref) {
		this.destinationPref = destinationPref;
	}

	public String getDestinationMunicipalities() {
		return destinationMunicipalities;
	}

	public void setDestinationMunicipalities(String destinationMunicipalities) {
		this.destinationMunicipalities = destinationMunicipalities;
	}

	public String getDestinationAddress() {
		return destinationAddress;
	}

	public void setDestinationAddress(String destinationAddress) {
		this.destinationAddress = destinationAddress;
	}

	public String getDestinationTel() {
		return destinationTel;
	}

	public void setDestinationTel(String destinationTel) {
		this.destinationTel = destinationTel;
	}

	public Timestamp getDeliveryTime() {
		return deliveryTime;
	}

	public void setDeliveryTime(Timestamp deliveryTime) {
		this.deliveryTime = deliveryTime;
	}

	public Integer getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(Integer paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<OrderItem> getOrderItemList() {
		return orderItemList;
	}

	public void setOrderItemList(List<OrderItem> orderItemList) {
		this.orderItemList = orderItemList;
	}

	public double getTAX_RATE() {
		double tax_rate = 0.1;
		return tax_rate;
	}

	/**
	 * 注文商品の合計金額の消費税額を返すメソッド.
	 * 
	 * @return 消費税分の金額
	 */
	public int getTax() {
		int basePrice = 0;
		// 商品ごとの合計金額を合算
		for (var orderItem : this.orderItemList) {
			basePrice += orderItem.getSubTotal();
		}
		// 小数点以下切り捨て
		int tax = (int) (basePrice * this.getTAX_RATE());
		return tax;
	}

	/**
	 * 注文の合計を返すメソッド.
	 * 
	 * @return 注文商品の合計金額
	 */
	public int getCalcPrice() {
		int basePrice = 0;
		// 商品ごとの合計金額を合算
		for (var orderItem : this.orderItemList) {
			basePrice += orderItem.getSubTotal();
		}
		return basePrice;
	}
}
