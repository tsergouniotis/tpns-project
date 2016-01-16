package com.tpns.article.security;

import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;

public class TpnsClientDetailsService implements org.springframework.security.oauth2.provider.ClientDetailsService {

	@Override
	public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
		BaseClientDetails baseClientDetails = new BaseClientDetails(clientId, null, "read,write,trust", "password", "ROLE_CLIENT, ROLE_TRUSTED_CLIENT, ROLE_USER");
		String password = "client".equals(clientId) ? "secret" : "password";
		baseClientDetails.setClientSecret(password);
		return baseClientDetails;
	}

}
