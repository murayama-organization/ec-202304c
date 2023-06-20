package com.example.common;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * セキュリティの設定.
 * 
 * @author shun.takeda
 * 
 */
@EnableWebSecurity
@Configuration // 設定用のクラス
public class SecurityConfig {

	/**
	 * @param http
	 * @return 構築されるオブジェクト、または実装が許容する場合は null。
	 * @throws Exception
	 */
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(authorize -> authorize.requestMatchers("/**").permitAll());
		return http.build();
	}

	/**
	 * <pre>
	 * bcryptアルゴリズムでハッシュ化する実装を返します.
	 * これを指定することでパスワードハッシュ化やマッチ確認する際に
	 * &#64;Autowired
	 * private PasswordEncoder passwordEncoder;
	 * と記載するとDIされるようになります。
	 * </pre>
	 * 
	 * @return bcryptアルゴリズムでハッシュ化する実装オブジェクト
	 */
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
