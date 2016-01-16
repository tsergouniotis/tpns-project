package com.tpns.article.managers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tpns.article.domain.Category;
import com.tpns.article.repository.CategoryRepository;

@Component
public class CategoryManager {

	@Autowired
	private CategoryRepository categoryDAO;

	public List<Category> getCategories() {
		return categoryDAO.findAll();
	}

	public Category getByName(final String name) {
		return categoryDAO.find(name);
	}

}
