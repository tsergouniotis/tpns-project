package com.tpns.article.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tpns.article.domain.Category;
import com.tpns.article.managers.CategoryManager;
import com.tpns.utils.CollectionUtils;

@Service
public class CategoryService {

	@Autowired
	private CategoryManager categoryManager;

	public List<String> getCategories() {

		final List<Category> categories = categoryManager.getCategories();

		final List<String> result = new ArrayList<>();

		if (CollectionUtils.isNonEmpty(categories)) {
			categories.forEach(category -> result.add(category.getName()));
		}

		return result;
	}
}
