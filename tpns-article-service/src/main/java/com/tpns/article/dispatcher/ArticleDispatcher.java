package com.tpns.article.dispatcher;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.web.client.RestTemplate;

import com.tpns.article.repository.ApplicationRepository;
import com.tpns.common.domain.Application;
import com.tpns.domain.article.Article;
import com.tpns.utils.CollectionUtils;

@Async
public class ArticleDispatcher {

	private static Logger LOGGER = LoggerFactory.getLogger(ArticleDispatcher.class.getPackage().getName());

	@Autowired
	private ApplicationRepository applicationDAO;

	public Future<Boolean> dispatch(final Article article) {

		final boolean result = false;

		final List<String> destinations = new ArrayList<String>(article.getDestinations());

		if (CollectionUtils.isNonEmpty(destinations)) {

			final List<Application> applications = applicationDAO.findByIds(destinations);
			Optional.ofNullable(applications).orElse(Collections.emptyList())
					.forEach(app -> send(article, app.getEndpoint()));

		}

		return new AsyncResult<Boolean>(result);

	}

	private void send(final Article article, final String destination) {

		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

		HttpEntity<Article> entity = new HttpEntity<Article>(article, headers);

		ResponseEntity<String> result = restTemplate.exchange(destination, HttpMethod.PUT, entity, String.class);

		if (!HttpStatus.ACCEPTED.equals(result.getStatusCode())) {
			final StringBuilder builder = new StringBuilder("Article did not submitted successfully to : ")
					.append(destination).append(". Status : ").append(result.getStatusCode()).append(":")
					.append(result.getBody());
			LOGGER.warn(builder.toString());
		}

	}

}
