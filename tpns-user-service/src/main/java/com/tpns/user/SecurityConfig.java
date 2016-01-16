package com.tpns.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	@Autowired // <-- This is crucial otherwise Spring Boot creates its own
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// log.info("Defining inMemoryAuthentication (2 users)");
		auth.inMemoryAuthentication()

				.withUser("user").password("password").roles("USER")

				.and()

				.withUser("admin").password("password").roles("USER", "ADMIN");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.formLogin()

				.and()

				.httpBasic().disable().anonymous().disable().authorizeRequests().anyRequest().authenticated();
	}
}
