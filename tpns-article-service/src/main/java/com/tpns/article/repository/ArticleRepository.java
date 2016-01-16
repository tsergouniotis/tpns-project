package com.tpns.article.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tpns.article.domain.Article;
import com.tpns.article.domain.ArticleStatus;

public interface ArticleRepository extends JpaRepository<Article, Long> {

	@Query(name = "Article.findByStatus")
	List<Article> findByStatus(@Param("status")ArticleStatus status);

	@Query(name = "Article.findByCategoryName")
	List<Article> findByCategoryName(String categoryName);

}
