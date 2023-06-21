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
}
