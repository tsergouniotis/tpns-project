package com.tpns.article.validation.dto.validators;

import java.util.Arrays;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.tpns.article.validation.dto.constraints.ExistingAuthor;
import com.tpns.utils.StringUtils;

public class ExistingAuthorValidator implements ConstraintValidator<ExistingAuthor, String> {

	private static final Logger LOGGER = LoggerFactory.getLogger(ExistingAuthorValidator.class.getPackage().getName());

	@Autowired
	@Value("user.service.uri")
	private String userServiceURI;

	@Override
	public void initialize(ExistingAuthor constraintAnnotation) {
		// TODO Auto-generated method stub
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if (StringUtils.hasText(value)) {

			int status = check(value);
			if (-1 == status || !HttpStatus.valueOf(status).is2xxSuccessful()) {
				context.disableDefaultConstraintViolation();
				context.buildConstraintViolationWithTemplate(context.getDefaultConstraintMessageTemplate()).addConstraintViolation();
				return false;
			}

		}
		return true;
	}

	private int check(String value) {
		try {

			LOGGER.info("Check if user exists");
			String uri = userServiceURI + "?username=" + value;
			RestTemplate restTemplate = new RestTemplate();
			HttpHeaders headers = new HttpHeaders();
			headers.setAccept(Arrays.asList(org.springframework.http.MediaType.APPLICATION_JSON));
			HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
			ResponseEntity<String> result = restTemplate.exchange(uri, HttpMethod.GET, entity, String.class);
			LOGGER.debug("user service uri " + uri);
			return result.getStatusCode().value();
		} catch (Exception e) {
			LOGGER.error("Could not validate author using author service ", e);
			return -1;
		}
	}

}
