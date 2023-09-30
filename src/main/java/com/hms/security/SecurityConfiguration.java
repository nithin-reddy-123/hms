package com.hms.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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

	@Autowired
	private UserService userService;

	AuthenticationManager authenticationManager;

	@Bean
	public CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler()
	{
		return new CustomAuthenticationSuccessHandler();
	}

	@Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(userService);
//        authenticationManager = authenticationManagerBuilder.build();

		http
	  .csrf(csrf -> csrf.disable())
	  .authorizeHttpRequests(authz -> authz
			  							.requestMatchers("/register").permitAll()
			  							.anyRequest().authenticated())
      .formLogin(formLogin -> formLogin
    		  						.loginPage("/userlogin").permitAll()
    		  						.successHandler(customAuthenticationSuccessHandler())
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
