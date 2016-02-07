package com.tpns.article.validation.domain.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.tpns.article.validation.domain.constraints.ExistingAuthor;
import com.tpns.domain.article.Article;

public class ExistingAuthorValidator implements ConstraintValidator<ExistingAuthor, Article> {

	@Override
	public void initialize(ExistingAuthor constraintAnnotation) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isValid(Article value, ConstraintValidatorContext context) {
		// TODO Auto-generated method stub
		return false;
	}

}
