package com.tpns.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tpns.domain.user.User;
import com.tpns.user.services.UserService;

@RestController
@RequestMapping("/v1/user")
public class UserController {

	@Autowired
	private UserService service;

	@RequestMapping(produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }, method = RequestMethod.GET, value = "/find/{username}")
	public ResponseEntity<User> find(@PathVariable("username") String username) {
		User user = service.findByUsername(username);
		return response(user);

	}

	private static <T> ResponseEntity<T> response(T t) {
		HttpStatus status = HttpStatus.OK;
		if (null == t) {
			status = HttpStatus.NOT_FOUND;
		}
		return new ResponseEntity<T>(t, status);
	}

	@PreAuthorize("#oauth2.hasScope('read')")
	@Secured("ROLE_ADMIN")
	@RequestMapping(produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }, method = RequestMethod.GET, value = "/{id}")
	public ResponseEntity<User> find(@PathVariable("id") Long id) throws Exception {
		User user = service.find(id);
		return response(user);
	}

	@PreAuthorize("#oauth2.hasScope('write')")
	@Secured("ROLE_ADMIN")
	@RequestMapping(consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, method = RequestMethod.PUT)
	public ResponseEntity<User> save(User user) throws Exception {
		service.save(user);
		return response(user);
	}

	@RequestMapping(consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, method = RequestMethod.POST)
	public ResponseEntity<User> update(User user) throws Exception {
		service.update(user);
		return response(user);
	}

	@RequestMapping(produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }, method = RequestMethod.DELETE, path = "/{id}")
	public ResponseEntity<String> delete(@PathVariable("id") Long id) throws Exception {
		service.delete(id);
		return new ResponseEntity<String>(HttpStatus.OK);
	}

}
