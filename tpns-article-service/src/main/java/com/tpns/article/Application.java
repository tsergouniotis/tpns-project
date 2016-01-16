package com.tpns.article;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@Configuration
@EnableJpaRepositories
// @EnableTransactionManagement
// @EnableOAuth2Client
// @EnableResourceServer
@ImportResource("classpath:context.xml")
public class Application extends SpringBootServletInitializer {

	@Value("${config.oauth2.accessTokenUri}")
	private String accessTokenUri;

	@Value("${config.oauth2.userAuthorizationUri}")
	private String userAuthorizationUri;

	@Value("${config.oauth2.clientID}")
	private String clientID;

	@Value("${config.oauth2.clientSecret}")
	private String clientSecret;

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(Application.class);
	}

	/*
	 * @Bean public OAuth2RestOperations restTemplate(OAuth2ClientContext
	 * oauth2ClientContext) { return new OAuth2RestTemplate(resource(),
	 * oauth2ClientContext); }
	 * 
	 * private OAuth2ProtectedResourceDetails resource() {
	 * AuthorizationCodeResourceDetails resource = new
	 * AuthorizationCodeResourceDetails(); resource.setClientId(clientID);
	 * resource.setClientSecret(clientSecret);
	 * resource.setAccessTokenUri(accessTokenUri);
	 * resource.setUserAuthorizationUri(userAuthorizationUri);
	 * resource.setScope(Arrays.asList("read"));
	 * 
	 * return resource; }
	 */

	/*
	 * @Bean AuthorizationServerConfigurer createAuthorizationServerConfigurer()
	 * { OAuth2AuthorizationServerConfiguration result = new
	 * OAuth2AuthorizationServerConfiguration(); return result; }
	 * 
	 * @Bean BaseClientDetails createBaseClientDetails() { return new
	 * BaseClientDetails(); }
	 * 
	 * @Bean AuthorizationServerProperties
	 * createAuthorizationServerProperties(){ return new
	 * AuthorizationServerProperties(); }
	 */

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
