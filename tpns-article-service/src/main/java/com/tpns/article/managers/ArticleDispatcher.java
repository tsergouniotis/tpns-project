package com.tpns.article.managers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.web.client.RestTemplate;

import com.tpns.article.domain.Article;
import com.tpns.article.repository.ApplicationRepository;
import com.tpns.common.domain.Application;
import com.tpns.utils.CollectionUtils;

@Async
public class ArticleDispatcher {

	private static Logger LOGGER = LoggerFactory.getLogger(ArticleDispatcher.class.getPackage().getName());

	@Autowired
	private ApplicationRepository applicationDAO;

	public Future<Boolean> dispatch(final Article article) {

		final boolean result = false;

		final Set<String> destinations = article.getDestinations();

		if (CollectionUtils.isNonEmpty(destinations)) {

			// String[] array = destinations.toArray(new
			// String[destinations.size()]);
			final List<Application> applications = applicationDAO.findByIds(new ArrayList<String>(destinations));

			if (CollectionUtils.isNonEmpty(applications)) {
				applications.forEach(app -> send(article, app.getEndpoint()));
			}

		}

		return new AsyncResult<Boolean>(result);

	}

	private void send(final Article article, final String destination) {

		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(org.springframework.http.MediaType.APPLICATION_JSON));

		HttpEntity<Article> entity = new HttpEntity<Article>(article, headers);

		ResponseEntity<String> result = restTemplate.exchange(destination, HttpMethod.PUT, entity, String.class);

		if (!HttpStatus.ACCEPTED.equals(result.getStatusCode())) {
			final StringBuilder builder = new StringBuilder("Article did not submitted successfully to : ").append(destination).append(". Status : ")
					.append(result.getStatusCode()).append(":").append(result.getBody());
			LOGGER.warn(builder.toString());
		}

	}

}
