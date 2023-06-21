package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.User;
import com.example.repository.UserRepository;

/**
 * ユーザに関する業務処理を行うサービスクラスです.
 * 
 * @author yousuke.murayama
 */
@Service
@Transactional
public class RegisterUserService {

	@Autowired
	private UserRepository userRepository;

	/**
	 * ユーザ登録します.
	 * 
	 * @param user ユーザオブジェクト
	 */
	public void registerUser(User user) {
		userRepository.insert(user);
	}
	
	/**
	 * メールアドレスからユーザ情報を検索します.
	 * ユーザ情報がない場合はnullを返します
	 * 
	 * @param email メールアドレス
	 * @return　ユーザ情報
	 */
	public User searchByEmail(String email) {
		User user = userRepository.findByEmail(email);
		
		return user;
	}
}
