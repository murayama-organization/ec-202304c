package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.User;
import com.example.form.LoginUserForm;
import com.example.service.LoginUserService;

import jakarta.servlet.http.HttpSession;

/**
 * ログインに関する処理を行うコントローラーです.
 * 
 * @author yousuke.murayama
 */
@Controller
@RequestMapping("/login-user")
public class LoginUserController {

	@Autowired
	private LoginUserService loginUserService;
	
	@Autowired
	private HttpSession session;
	
	/**
	 * ログイン画面に遷移します.
	 * 
	 * @param model　モデル
	 * @return ログイン画面
	 */
	@GetMapping("/to-login")
	public String toLogin(Model model) {
		return "ec/login";
	}
	
	/**
	 * ログインします.
	 * 
	 * @param form ログインフォーム
	 * @param model モデル
	 * @return 商品一覧画面
	 */
	@PostMapping("/login-user")
	private String loginUser(LoginUserForm form, Model model) {
		User user = loginUserService.loginUser(form.getEmail(), form.getPassword());
		
		if(user == null) {
			model.addAttribute("errorMsg", "メールアドレスまたはパスワードが不正です");
			return toLogin(model);
		}
		
		session.setAttribute("currentUser", user);
		
		return "redirect:/showList";
	}
	
	/**
	 * ログアウトします.
	 * 
	 * @return ログイン画面
	 */
	@GetMapping("/logout")
	public String logout() {
		session.invalidate();
		return "redirect:/login-user/to-login";
	}
	
}
