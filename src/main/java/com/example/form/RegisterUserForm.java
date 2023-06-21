package com.example.form;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

/**
 * ユーザ情報に関するリクエストパラメータを格納するためのフォームクラスです.
 * 
 * @author yousuke.murayama
 *
 */
public class RegisterUserForm {

	/** 姓 */
	@NotBlank(message = "苗字は必須です")
	private String lastName;
	/** 名 */
	@NotBlank(message = "名前は必須です")
	private String firstName;
	/** Eメール */
	@NotBlank(message = "電話番号は必須です")
	@Email(message = "メールアドレスの形式が不正です")
	private String email;
	/** パスワード */
	@NotBlank(message = "パスワードは必須です")
	@Size(min = 8, max = 16, message = "パスワードは8字以上16字以内で入力してください")
	private String password;
	/** 郵便番号 */
	@NotBlank(message = "郵便番号は必須です")
	@Pattern(regexp = "^[0-9]{3}-[0-9]{4}$", message = "郵便番号はXXX-XXXXの形式で入力してください")
	private String zipcode;
	/** 県 */
	@NotBlank(message = "県は必須です")
	private String pref;
	/** 市町村 */
	@NotBlank(message = "市町村は必須です")
	private String municipalities;
	/** 住所 */
	@NotBlank(message = "住所は必須です")
	private String address;
	/** 電話番号 */
	@NotBlank(message = "電話番号は必須です")
	@Pattern(regexp = "^\\d{2,4}-\\d{2,4}-\\d{4}$", message = "電話番号はXXXX-XXXX-XXXXの形式で入力してください")
	private String telephone;
	/** 確認用パスワード */
	@NotBlank(message = "確認用パスワードは必須です")
	private String passwordConfirmation;

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public String getPasswordConfirmation() {
		return passwordConfirmation;
	}

	public void setPasswordConfirmation(String passwordConfirmation) {
		this.passwordConfirmation = passwordConfirmation;
	}

	@Override
	public String toString() {
		return "RegisterUserForm [lastName=" + lastName + ", firstName=" + firstName + ", email=" + email
				+ ", password=" + password + ", zipcode=" + zipcode + ", pref=" + pref + ", municipalities="
				+ municipalities + ", address=" + address + ", telephone=" + telephone + ", passwordConfirmation="
				+ passwordConfirmation + "]";
	}

}
