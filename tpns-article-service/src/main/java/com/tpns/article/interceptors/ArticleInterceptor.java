package com.tpns.article.interceptors;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tpns.domain.errors.BusinessError;
import com.tpns.domain.errors.BusinessErrorCode;
import com.tpns.domain.errors.BusinessException;

@Aspect
public class ArticleInterceptor {

	private static final Logger LOGGER = LoggerFactory.getLogger(ArticleInterceptor.class);

	@Around("execution(* com.tpns.article.services.*.*(..))")
	public Object proceed(ProceedingJoinPoint jp) throws Exception {

		try {
			Instant start = Instant.now();

			Object result = jp.proceed(jp.getArgs());

			Instant end = Instant.now();

			LOGGER.debug(jp.getSignature().toString() + "\t" + Duration.between(start, end).toString());
			return result;

		} catch (final BusinessException e) {

			throw e;

		} catch (final ConstraintViolationException e) {
			throw newBusinessException(e.getConstraintViolations());
		} catch (final Throwable e) {
			throw BusinessException.create(e.getMessage());
		}

	}

	private static BusinessException newBusinessException(final Set<ConstraintViolation<?>> constraintViolations) {
		final List<BusinessError> errors = new ArrayList<>();
		for (final ConstraintViolation<?> constraintViolation : constraintViolations) {
			errors.add(BusinessError.create(constraintViolation.getMessage(), BusinessErrorCode.VALIDATION));
		}
		return BusinessException.create(errors);
	}

}