package com.tpns.article.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class TpnsUserDetailsService implements UserDetailsService {

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		List<GrantedAuthority> authorizedGrantTypes = new ArrayList<>();
		authorizedGrantTypes.add(new SimpleGrantedAuthority("ROLE_CLIENT"));
		authorizedGrantTypes.add(new SimpleGrantedAuthority("ROLE_TRUSTED_CLIENT"));
		authorizedGrantTypes.add(new SimpleGrantedAuthority("ROLE_USER"));
		return new User(username, "password", authorizedGrantTypes);
	}

}
