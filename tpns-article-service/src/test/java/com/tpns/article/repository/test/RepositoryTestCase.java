package com.tpns.article.repository.test;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tpns.article.Application;
import com.tpns.article.repository.ArticleRepository;
import com.tpns.domain.article.Article;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes=Application.class)
public class RepositoryTestCase {

	@Autowired
	@Qualifier("news24")
	private Parser news24;

	@Autowired
	private ArticleRepository repository;

	@Test
	public void testNews24Parser() {

		try {
			List<Article> articles = new News24Parser().parse();

			repository.save(articles);

		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}

	}

}
