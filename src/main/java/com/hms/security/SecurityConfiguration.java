package com.hms.security;

import com.hms.service.TableUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.hms.service.UserService;


@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

	AuthenticationManager authenticationManager;

	@Configuration
	@Order(1)
	public static class App2ConfigurationAdapter {

		@Autowired
		private TableUserService tableUserService;

		@Bean
		public CustomAuthenticationSuccessHandler2 customAuthenticationSuccessHandler2()
		{
			return new CustomAuthenticationSuccessHandler2();
		}

		@Bean
		public SecurityFilterChain filterChain2(HttpSecurity http) throws Exception {

			AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
			authenticationManagerBuilder.userDetailsService(tableUserService);
//        authenticationManager = authenticationManagerBuilder.build();

			http
					.csrf(csrf -> csrf.disable())
					.authorizeHttpRequests(authz -> authz
							.requestMatchers("register","/counterlogin").permitAll()
							.anyRequest().authenticated())
					.formLogin(formLogin -> formLogin
							.loginPage("/counterlogin").permitAll()
							.successHandler(customAuthenticationSuccessHandler2())
							.failureUrl("/counterlogin?error=true"))
					.logout(logout ->logout
							.invalidateHttpSession(true)
							.clearAuthentication(true)
							.deleteCookies("JSESSIONID","USER")
							.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
							.logoutSuccessUrl("/login?logout")
							.permitAll());
			return http.build();
		}
	}

	@Configuration
	@Order(2)
	public static class App1ConfigurationAdapter {

		@Bean
		public CustomAuthenticationSuccessHandler1 customAuthenticationSuccessHandler1()
		{
			return new CustomAuthenticationSuccessHandler1();
		}

		@Autowired
		private UserService userService;

		@Bean
		public SecurityFilterChain filterChain1(HttpSecurity http) throws Exception {

			AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
			authenticationManagerBuilder.userDetailsService(userService);
//        authenticationManager = authenticationManagerBuilder.build();

			http
					.csrf(csrf -> csrf.disable())
					.authorizeHttpRequests(authz -> authz
							.requestMatchers("/register","/counterlogin").permitAll()
							.anyRequest().authenticated())
					.formLogin(formLogin -> formLogin
							.loginPage("/userlogin").permitAll()
							.successHandler(customAuthenticationSuccessHandler1())
							.failureUrl("/userlogin?error=true"))
					.logout(logout ->logout
							.invalidateHttpSession(true)
							.clearAuthentication(true)
							.deleteCookies("JSESSIONID","USER")
							.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
							.logoutSuccessUrl("/login?logout")
							.permitAll());
			return http.build();
		}
	}



}
