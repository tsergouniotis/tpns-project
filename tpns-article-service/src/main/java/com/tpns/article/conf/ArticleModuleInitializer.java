package com.tpns.article.conf;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.stereotype.Component;

import com.tpns.article.domain.ApplicationParameter;
import com.tpns.article.repository.ApplicationParameterRepository;

//@Component
public class ArticleModuleInitializer extends PropertyPlaceholderConfigurer {

	@Autowired
	private ApplicationParameterRepository applicationParameterDAO;

	@Override
	protected void loadProperties(Properties props) throws IOException {

/*		List<ApplicationParameter> parameters = applicationParameterDAO.findAll();

		for (ApplicationParameter param : parameters) {
			props.put(param.getKey(), param.getValue());
		}*/

		props.put("article.lucene.directory", "/home/sergouniotis/Downloads/tpns/index");
		props.put("cassandra.contact.point.address", "localhost");
		props.put("cassandra.core.connections.per.host", "1");
		props.put("cassandra.max.connections.per.host", "1");
		props.put("user.service.uri", "http://localhost:8080/user-service/v1/user");

		super.loadProperties(props);
	}

}
