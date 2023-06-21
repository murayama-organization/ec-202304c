package com.example.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.User;
import com.example.form.RegisterUserForm;
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
	public String toRegister(RegisterUserForm form) {
		return "ec/register_user";
	}

	/**
	 * ユーザを登録します.
	 * 
	 * @return ログイン画面
	 */
	@PostMapping("/register-user")
	public String registerUser(@Validated RegisterUserForm form, BindingResult result) {

		User exitUser = registerUserService.searchByEmail(form.getEmail());

		if (exitUser != null) {
			result.rejectValue("email", null, "そのメールアドレスは既に使われています");
		}

		if (!(form.getPasswordConfirmation().equals(form.getPassword()))) {
			result.rejectValue("passwordConfirmation", null, "パスワードと確認用パスワードが不一致です");
		}

		if (result.hasErrors()) {
			return toRegister(form);
		}

		User user = new User();
		BeanUtils.copyProperties(form, user);

		String name = form.getLastName() + form.getFirstName();

		user.setName(name);

		registerUserService.registerUser(user);

		return "ec/login";
	}

}
