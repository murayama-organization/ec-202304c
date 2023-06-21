package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.service.RegisterUserService;

/**
 * ユーザ登録に関する処理を行うコントローラーです.
 * 
 * @author yousuke.murayama
 */
@Controller
@RequestMapping("/register-user")
public class RegisterUserController {

	@Autowired
	private RegisterUserService registerUserService;
	
	/**
	 * ユーザ登録画面に遷移します.
	 * 
	 * @return ユーザ登録画面
	 */
	@GetMapping("/to-register")
	public String toRegister() {
		return "ec/register_user";
	}
	
	/**
	 * ユーザを登録します.
	 * 
	 * @return ログイン画面
	 */
	@PostMapping("/register-user")
	public String registerUser() {
		return "ec/login";
	}
	
}
