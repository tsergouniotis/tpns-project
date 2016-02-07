package com.tpns.article.conf;

import java.io.IOException;
import java.util.Properties;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.stereotype.Component;

@Component
public class ArticleModuleInitializer extends PropertyPlaceholderConfigurer {

	@Override
	protected void loadProperties(Properties props) throws IOException {

		props.put("user.service.uri", "http://localhost:8080/user-service/v1/user");

		super.loadProperties(props);
	}

}
