package com.tpns.article.validation.domain.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.tpns.article.repository.CategoryRepository;
import com.tpns.article.validation.domain.constraints.ValidArticle;
import com.tpns.domain.article.Article;
import com.tpns.domain.article.Category;

public class ArticleValidator implements ConstraintValidator<ValidArticle, Article> {

	@Autowired
	private CategoryRepository categoryDAO;

	@Override
	public void initialize(final ValidArticle constraintAnnotation) {
	}

	@Override
	public boolean isValid(final Article article, final ConstraintValidatorContext context) {
		if (null == article) {
			return true;
		}
		final Category category = article.getCategory();
		if (Category.hasValue(category)) {
			final Category persistent = categoryDAO.findByName(category.getName());
			if (null == persistent) {
				context.disableDefaultConstraintViolation();
				context.buildConstraintViolationWithTemplate(context.getDefaultConstraintMessageTemplate()).addPropertyNode("category").addConstraintViolation();
				return false;
			}
			article.setCategory(persistent);
		}
		return true;
	}

}
