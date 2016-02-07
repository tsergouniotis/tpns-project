package com.tpns.article.lucene;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.LongField;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.springframework.stereotype.Component;

import com.tpns.domain.article.Article;

@Component
public class ArticleDocumentConverter implements DocumentConverter<Article> {

	@Override
	public Document convert(final Article article) {
		final Document document = new Document();
		document.add(new LongField(LuceneFields.ID.name(), article.getId(), Field.Store.YES));
		document.add(new StringField(LuceneFields.TITLE.name(), article.getSubject(), Field.Store.YES));
		document.add(new TextField(LuceneFields.CONTENT.name(), article.getContent(), Field.Store.YES));
		return document;
	}

	@Override
	public Article convert(final Document doc) {
		final String id = doc.get(LuceneFields.ID.name());
		final String title = doc.get(LuceneFields.TITLE.name());
		final String content = doc.get(LuceneFields.CONTENT.name());

		return Article.create(Long.parseLong(id), title, content);

	}

}
