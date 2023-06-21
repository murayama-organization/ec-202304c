package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.service.LoginUserService;

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
	
	@GetMapping("/to-login")
	public String toLogin() {
		return "ec/login";
	}
	
	@PostMapping("/login-user")
	private String loginUser() {
		return "ec/item_list";
	}
	
}
