package com.example.form;

/**
 * ログインに関するリクエストパラメータを格納するためのフォームクラスです.
 * 
 * @author yousuke.murayama
 */
public class LoginUserForm {

	/** メールアドレス */
	private String email;
	/** パスワード */
	private String password;

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

	@Override
	public String toString() {
		return "LoginUserForm [email=" + email + ", password=" + password + "]";
	}

}
