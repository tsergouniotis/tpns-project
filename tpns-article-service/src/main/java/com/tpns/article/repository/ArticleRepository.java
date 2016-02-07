package com.tpns.article.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tpns.domain.article.Article;
import com.tpns.domain.article.ArticleStatus;

public interface ArticleRepository extends JpaRepository<Article, Long> {

	List<Article> findByStatus(ArticleStatus status);

	List<Article> findByCategoryName(String categoryName);

}
