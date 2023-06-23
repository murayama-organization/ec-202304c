package com.example.form;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

/**
 * 注文情報に関するリクエストパラメーターを格納するためのフォームクラス.
 * 
 * @author miku.ueda
 *
 */
public class SendOrderForm {
	/** 注文ID */
	private Integer orderId;
	/** 名前 */
	@NotBlank(message = "名前を入力して下さい")
	private String name;
	/** Eメール */
	@NotBlank(message = "メールアドレスを入力して下さい")
	@Email(message = "メールアドレスの形式が不正です")
	private String email;
	/** 郵便番号 */
	@NotBlank(message = "郵便番号を入力して下さい")
	@Pattern(regexp = "^[0-9]{3}-[0-9]{4}$", message = "郵便番号はXXX-XXXXの形式で入力してください")
	private String zipcode;
	/** 県 */
	@NotBlank(message = "県を入力して下さい")
	private String pref;
	/** 市町村 */
	@NotBlank(message = "市町村を入力して下さい")
	private String municipalities;
	/** 住所 */
	@NotBlank(message = "住所を入力して下さい")
	private String address;
	/** 電話番号 */
	@NotBlank(message = "電話番号を入力して下さい")
	@Pattern(regexp = "^\\d{2,4}-\\d{2,4}-\\d{4}$", message = "電話番号はXXXX-XXXX-XXXXの形式で入力してください")
	private String telephone;
	/** 配達時間 */
	@NotEmpty(message = "配達日時を入力して下さい")
	private String deliveryTime;
	/** 支払方法 */
	@NotEmpty(message = "支払方法を入力して下さい")
	private String paymentMethod;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getPref() {
		return pref;
	}

	public void setPref(String pref) {
		this.pref = pref;
	}

	public String getMunicipalities() {
		return municipalities;
	}

	public void setMunicipalities(String municipalities) {
		this.municipalities = municipalities;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getDeliveryTime() {
		return deliveryTime;
	}

	public void setDeliveryTime(String deliveryTime) {
		this.deliveryTime = deliveryTime;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	@Override
	public String toString() {
		return "SendOrderForm [orderId=" + orderId + ", name=" + name + ", email=" + email + ", zipcode=" + zipcode
				+ ", pref=" + pref + ", municipalities=" + municipalities + ", address=" + address + ", telephone="
				+ telephone + ", deliveryTime=" + deliveryTime + ", paymentMethod=" + paymentMethod + "]";
	}

}
