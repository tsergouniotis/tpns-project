package com.tpns.article.validation.domain.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.tpns.article.repository.CategoryRepository;
import com.tpns.article.validation.domain.constraints.ExistingCategory;
import com.tpns.domain.article.Category;

public class ExistingCategoryValidator implements ConstraintValidator<ExistingCategory, String> {

	@Autowired
	private CategoryRepository categoryDAO;

	@Override
	public void initialize(final ExistingCategory constraintAnnotation) {
		// TODO Auto-generated method stub
	}

	@Override
	public boolean isValid(final String name, final ConstraintValidatorContext context) {

		final Category persistent = categoryDAO.findByName(name);
		if (null == persistent) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(context.getDefaultConstraintMessageTemplate()).addConstraintViolation();
			return false;
		}

		return true;
	}

}
