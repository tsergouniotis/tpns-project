package com.tpns.article.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;

public class GuestServiceImpl implements ClientDetailsService {

	@Override
	public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {

		List<String> authorizedGrantTypes = new ArrayList<String>();
		authorizedGrantTypes.add("password");
		authorizedGrantTypes.add("refresh_token");
		authorizedGrantTypes.add("client_credentials");

		BaseClientDetails clientDetails = new BaseClientDetails();
		clientDetails.setClientId("client");
		clientDetails.setClientSecret("secret");
		clientDetails.setAuthorizedGrantTypes(authorizedGrantTypes);

		return clientDetails;
	}

}
