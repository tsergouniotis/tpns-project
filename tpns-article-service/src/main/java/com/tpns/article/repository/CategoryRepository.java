package com.tpns.article.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tpns.domain.article.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

	Category findByName(String categoryName);

}
