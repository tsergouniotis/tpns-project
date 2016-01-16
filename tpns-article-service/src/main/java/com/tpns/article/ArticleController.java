package com.tpns.article;

import java.util.List;

import javax.annotation.security.RolesAllowed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tpns.article.domain.Article;
import com.tpns.article.domain.ArticleStatus;
import com.tpns.article.managers.ArticleManager;
import com.tpns.utils.CollectionUtils;

@RestController
@RequestMapping("/v1/article")
public class ArticleController {

	@Autowired
	private ArticleManager service;

	@RequestMapping(produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }, method = RequestMethod.GET, path = "/published")
	public List<Article> findPublished() throws Exception {
		return service.findByStatus(ArticleStatus.PUBLISHED);
	}

	@RequestMapping(produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }, method = RequestMethod.GET, path = "/findPublishedByCategory")
	public List<Article> findPublished(@RequestParam("catName") String catName) throws Exception {
		return service.findByCategory(catName);
	}

	@RolesAllowed({ "AUTHOR" })
	@RequestMapping(produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }, method = RequestMethod.GET, path = "/{id}")
	public ResponseEntity<Article> find(@PathVariable("id") Long id) throws Exception {
		Article article = service.find(id);
		HttpStatus status = HttpStatus.OK;
		if (null == article) {
			status = HttpStatus.NOT_FOUND;
		}
		return new ResponseEntity<Article>(article, status);
	}

	@RequestMapping(produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }, method = RequestMethod.GET, path = "/search/{key}")
	public ResponseEntity<List<Article>> seach(@PathVariable("key") String key) throws Exception {
		List<Article> articles = service.findByKey(key);
		HttpStatus status = HttpStatus.OK;
		if (CollectionUtils.isEmpty(articles)) {
			status = HttpStatus.NOT_FOUND;
		}
		return new ResponseEntity<List<Article>>(articles, status);
	}

	@RequestMapping(consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, method = RequestMethod.PUT)
	public ResponseEntity<Article> save(Article article) throws Exception {
		service.save(article);
		return new ResponseEntity<Article>(article, HttpStatus.OK);
	}

	@RequestMapping(consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, method = RequestMethod.POST)
	public ResponseEntity<Article> update(Article article) throws Exception {
		service.update(article.getId(), article);
		return new ResponseEntity<Article>(article, HttpStatus.OK);
	}

	@RequestMapping(consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, method = RequestMethod.DELETE, path = "/{id}")
	public ResponseEntity<String> delete(@PathVariable("id") Long id) throws Exception {
		service.delete(id);
		return new ResponseEntity<String>(HttpStatus.OK);
	}

}
