package com.example.common;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

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

	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(ahr -> ahr
				.requestMatchers("/", "/register-user/to-register", "/register-user/register-user",
						"/show-item/show-item-list", "/shopping-cart/show-cart", "/show-detail/show-item-detail")
				.permitAll() // 「/」などのパスは全てのユーザに許可
				// .requestMatchers("/admin/**").hasRole("ADMIN")
				// /admin/から始まるパスはADMIN権限でログインしている場合のみアクセス可(権限設定時の「ROLE_」を除いた文字列を指定)
				// .requestMatchers("/user/**").hasRole("USER") //
				// /user/から始まるパスはUSER権限でログインしている場合のみアクセス可(権限設定時の「ROLE_」を除いた文字列を指定)
				.anyRequest().authenticated()); // それ以外のパスの場合はログイン認証が必要

		http.formLogin(login -> login // ログインに関する設定
				.loginPage("/") // ログイン画面に遷移させるパス(ログイン認証が必要なパスを指定してかつログインされていないとこのパスに遷移される)
				.loginProcessingUrl("/to-login") // ログインボタンを押した際に遷移させるパス(ここに遷移させれば自動的にログインが行われる)
				.failureUrl("/?error=true") // ログイン失敗に遷移させるパス
				.defaultSuccessUrl("/show-item/show-item-list", false) // 第1引数:デフォルトでログイン成功時に遷移させるパス
				// 第2引数: true :認証後常に第1引数のパスに遷移
				// false:認証されてなくて一度ログイン画面に飛ばされてもログインしたら指定したURLに遷移
				.usernameParameter("mailAddress") // 認証時に使用するユーザ名のリクエストパラメータ名(今回はメールアドレスを使用)
				.passwordParameter("password")); // 認証時に使用するパスワードのリクエストパラメータ名

		http.logout(logout -> logout // ログアウトに関する設定
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout**")) // ログアウトさせる際に遷移させるパス
				.logoutSuccessUrl("/show-item/show-item-list") // ログアウト後に遷移させるパス(ここではログイン画面を設定)
				.deleteCookies("JSESSIONID") // ログアウト後、Cookieに保存されているセッションIDを削除
				.invalidateHttpSession(true)); // true:ログアウト後、セッションを無効にする false:セッションを無効にしない
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
