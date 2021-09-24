package com.shHair.reservation.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import com.shHair.reservation.service.MyUserDetailsService;


@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	private MyUserDetailsService myUserDetailsService;
	
//	@Autowired
//	private JwtRequestFilter jwtRequestFiler;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.userDetailsService(myUserDetailsService);
	}
	
	

	@Override
	protected void configure(HttpSecurity http) throws Exception {
//		https://www.baeldung.com/spring-channel-security-https
		
		http.csrf().disable()
		.authorizeRequests().anyRequest().authenticated()
			.and()
			.formLogin()
					.loginPage("/showLoginPage")
					.loginProcessingUrl("/authenticateUser")
					.permitAll()
			.and()
			.logout().invalidateHttpSession(true).permitAll();
		
//		http.csrf().disable()
//				.authorizeRequests().antMatchers("/authenticate").permitAll()
//				.anyRequest().authenticated()
//				.and().sessionManagement()
//				.sessionCreationPolicy(SessionCreationPolicy.STATELESS); // don't make a seesion
//		
//		http.addFilterBefore(jwtRequestFiler, UsernamePasswordAuthenticationFilter.class); // A가 B보다 먼저 호출
		
		// authorizeRequests() : security 처리에 HttpServletRequest를 이용함
		// antMatchers() : 특정한 경로를 지정
		// permitAll() : 모든 사용자가 접근할 수 있음
		// formLogin() : form 태그 기반의 로그인 지원
		// invalidateHttpSession(true) : 브라우저 종료되면 세션 종료
		
				
	}
	
	
	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		
		// 암호화할 방법을 정해서 암호화된 비밀번호를 반환
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}
	
}
