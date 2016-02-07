package com.tpns.article.lucene;

import java.io.File;
import java.io.IOException;

import org.apache.lucene.store.Directory;
import org.apache.lucene.store.NativeFSLockFactory;
import org.apache.lucene.store.SimpleFSDirectory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LuceneDirectoryInitializer {

	@Autowired
	@Value("article.lucene.directory")
	private String indexLocation;

	@Bean
	public Directory createDirectory() throws IOException {
		final File directoryAsFileObject = new File(indexLocation);
		return new SimpleFSDirectory(directoryAsFileObject.toPath(), NativeFSLockFactory.INSTANCE);
	}

}
