package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.User;
import com.example.repository.UserRepository;

/**
 * ユーザのログインに関する処理を行うサービスクラスです.
 * 
 * @author yousuke.murayama
 */
@Service
@Transactional
public class LoginUserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	/**
	 * ログインします. 
	 * パスワード不一致、メールアドレスが不一致の場合nullを返します
	 * 
	 * @param email    メールアドレス
	 * @param password パスワード
	 * @return ユーザ
	 */
	public User loginUser(String email, String password) {
		User user = userRepository.findByEmail(email);

		if (user == null) {
			return null;
		}

		if (!passwordEncoder.matches(password, user.getPassword())) {
			return null;
		}

		return user;
	}

}
