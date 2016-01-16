package com.tpns.article.managers;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tpns.article.domain.Article;
import com.tpns.article.domain.ArticleStatus;
import com.tpns.article.lucene.LuceneRepository;
import com.tpns.article.repository.ArticleRepository;
import com.tpns.common.domain.errors.BusinessException;
import com.tpns.utils.Assert;

@Component
public class ArticleManager {

	@Autowired
	private ArticleRepository articleDAO;

	@Autowired
	private LuceneRepository luceneDAO;

	public void save(@Valid Article article) throws BusinessException {
		final Article entity = articleDAO.save(article);
		luceneDAO.save(entity);
	}

	public Article find(final Long id) {
		return articleDAO.findOne(id);
	}

	public List<Article> findAll() {
		return articleDAO.findAll();
	}

	public List<Article> findByStatus(final ArticleStatus status) {
		Assert.notNull(status);
		return articleDAO.findByStatus(status);
	}

	public List<Article> findByCategory(final String categoryName) {
		Assert.notNull(categoryName);
		return articleDAO.findByCategoryName(categoryName);
	}

	public List<Article> findByKey(final String key) {
		return luceneDAO.findArticles(key);
	}

	public void delete(@NotNull final Long id) {
		final Article article = articleDAO.findOne(id);
		Assert.notNull(article);
		articleDAO.delete(article);
	}

	public void update(final Long articleId, @Valid Article article) throws BusinessException {
		final Article persistent = articleDAO.findOne(articleId);
		Assert.notNull(persistent);
		persistent.update(article);
	}

}
