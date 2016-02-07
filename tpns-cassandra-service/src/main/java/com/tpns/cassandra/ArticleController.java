package com.tpns.cassandra;

import java.util.Collections;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tpns.domain.article.Article;

@RestController
@RequestMapping("/v1/article")
public class ArticleController {

	@RequestMapping(produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, method = RequestMethod.GET, path = "/findPublishedByCategory")
	public List<Article> findPublished(@RequestParam("catName") @NotNull String catName) throws Exception {
		// TODO implement
		return Collections.emptyList();
	}

	@RequestMapping(produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, method = RequestMethod.GET, path = "/{id}")
	public ResponseEntity<Article> find(@PathVariable("id") @NotNull Long id) throws Exception {
		// TODO implement
		Article article = Article.create(id, "A title", "A content");
		HttpStatus status = HttpStatus.OK;
		if (null == article) {
			status = HttpStatus.NOT_FOUND;
		}
		return new ResponseEntity<Article>(article, status);
	}

}
