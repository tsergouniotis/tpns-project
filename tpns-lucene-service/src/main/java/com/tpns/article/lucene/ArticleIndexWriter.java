package com.tpns.article.lucene;

import java.io.IOException;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.Query;
import org.apache.lucene.store.Directory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import com.tpns.domain.article.Article;

@Component
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS, value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class ArticleIndexWriter {

	private static final Logger LOGGER = LoggerFactory.getLogger(ArticleIndexWriter.class.getPackage().getName());

	@Autowired
	private Directory directory;

	@Autowired
	private DocumentConverter<Article> documentConverter;

	public void index(Article article) {
		try (IndexWriter indexWriter = new IndexWriter(directory, new IndexWriterConfig(new StandardAnalyzer()))) {
			Document document = documentConverter.convert(article);
			indexWriter.addDocument(document);
		} catch (IOException e) {
			LOGGER.error("There was an error while writing on index ", e);
		}

	}

	public void delete(Long id) {

		Analyzer analyzer = new StandardAnalyzer();

		try (IndexWriter indexWriter = new IndexWriter(directory, new IndexWriterConfig(analyzer))) {

			QueryParser parser = new QueryParser(LuceneFields.ID.name(), analyzer);

			Query query = parser.parse(id.toString());

			indexWriter.deleteDocuments(query);

		} catch (Exception e) {

			LOGGER.error(String.format("There was an error while deleting file with key {0} on index", id.toString()), e);

		}

	}

}
