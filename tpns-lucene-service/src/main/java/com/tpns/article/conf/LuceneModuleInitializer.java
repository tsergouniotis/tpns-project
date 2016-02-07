package com.tpns.article.conf;

import java.io.IOException;
import java.util.Properties;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.stereotype.Component;

@Component
public class LuceneModuleInitializer extends PropertyPlaceholderConfigurer {

	@Override
	protected void loadProperties(Properties props) throws IOException {

		props.put("article.lucene.directory", "/home/sergouniotis/Downloads/tpns/index");

		super.loadProperties(props);
	}

}
