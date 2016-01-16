package com.tpns.article.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;

public class UserAuthenticationProvider implements AuthenticationProvider {

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {

		boolean result = true;// isValidUser(authentication.getPrincipal().toString(),
								// authentication.getCredentials().toString());

		if (result) {
			List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
			return new UserAuthenticationToken(authentication.getPrincipal(), authentication.getCredentials(), grantedAuthorities);
		} else {
			throw new BadCredentialsException("Bad User Credentials.");
		}
	}

	public boolean supports(Class<?> arg0) {
		return true;
	}

}
