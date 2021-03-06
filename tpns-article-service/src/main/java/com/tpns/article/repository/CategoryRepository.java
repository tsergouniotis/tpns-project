package com.tpns.article.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tpns.article.domain.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

	@Query(name="Category.findByName")
	Category find(String categoryName);

}
