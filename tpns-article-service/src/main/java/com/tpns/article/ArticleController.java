package com.tpns.article;

import java.util.Collections;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tpns.article.repository.ArticleRepository;
import com.tpns.domain.article.Article;
import com.tpns.domain.article.ArticleStatus;
import com.tpns.utils.CollectionUtils;

@RestController
@RequestMapping("/v1/article")
public class ArticleController {

	@Autowired
	private ArticleRepository articleRepository;

	@RequestMapping(produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, method = RequestMethod.GET, path = "/published")
	public List<Article> findPublished() throws Exception {
		return articleRepository.findByStatus(ArticleStatus.PUBLISHED);
	}

	@RequestMapping(produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, method = RequestMethod.GET, path = "/findPublishedByCategory")
	public List<Article> findPublished(@RequestParam("catName") @NotNull String catName) throws Exception {
		return articleRepository.findByCategoryName(catName);
	}

	@RequestMapping(produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, method = RequestMethod.GET, path = "/{id}")
	public ResponseEntity<Article> find(@PathVariable("id") @NotNull Long id) throws Exception {
		Article article = articleRepository.findOne(id);
		HttpStatus status = HttpStatus.OK;
		if (null == article) {
			status = HttpStatus.NOT_FOUND;
		}
		return new ResponseEntity<Article>(article, status);
	}

	@RequestMapping(produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, method = RequestMethod.GET, path = "/search/{key}")
	public ResponseEntity<List<Article>> seach(@PathVariable("key") String key) throws Exception {
		List<Article> articles = Collections.emptyList();
		// TODO call lucene repository if advanced search or search by keyword
		HttpStatus status = HttpStatus.OK;
		if (CollectionUtils.isEmpty(articles)) {
			status = HttpStatus.NOT_FOUND;
		}
		return new ResponseEntity<List<Article>>(articles, status);
	}

	@RequestMapping(consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }, method = RequestMethod.PUT)
	public ResponseEntity<Article> save(@Valid @RequestBody Article article) throws Exception {
		articleRepository.save(article);
		return new ResponseEntity<Article>(article, HttpStatus.OK);
	}

	@RequestMapping(consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }, method = RequestMethod.POST)
	public ResponseEntity<Article> update(@Valid @RequestBody Article article) throws Exception {
		// TODO create update method on repository level
		// articleRepository.update(article);
		return new ResponseEntity<Article>(article, HttpStatus.OK);
	}

	@RequestMapping(consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, method = RequestMethod.DELETE, path = "/{id}")
	public ResponseEntity<String> delete(@PathVariable("id") Long id) throws Exception {
		articleRepository.delete(id);
		return new ResponseEntity<String>(HttpStatus.OK);
	}

}
