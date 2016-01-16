package com.tpns.article.lucene;

import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tpns.article.domain.Article;

@Component
public class LuceneRepository {

	private static final Logger LOGGER = LoggerFactory.getLogger(LuceneRepository.class);

	@Autowired
	private ArticleIndexWriter indexWriter;

	@Autowired
	private ArticleIndexReader indexReader;

	public void save(Article article) {
		indexWriter.index(article);
	}

	public List<Article> findArticles(String key) {
		try {
			return indexReader.search(key, LuceneFields.CONTENT.name());
		} catch (Exception e) {
			LOGGER.info("No articles found.");
			return Collections.emptyList();
		}
	}

	public void delete(Long id) {

		indexWriter.delete(id);

	}

}
