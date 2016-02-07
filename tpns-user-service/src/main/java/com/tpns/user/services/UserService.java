package com.tpns.user.services;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tpns.domain.user.User;
import com.tpns.user.repository.UserRepository;
import com.tpns.utils.Assert;

@Service
public class UserService {

	@Autowired
	private UserRepository userDAO;

	public void save(@Valid User User) {
		userDAO.save(User);
	}

	public User find(Long id) {
		return userDAO.findOne(id);
	}

	public User findByUsername(String username) {
		return userDAO.findByUsername(username);
	}

	public void delete(Long id) {
		User user = userDAO.findOne(id);
		Assert.notNull(user);
		userDAO.delete(user);
	}

	public void update(@Valid User user) {
		User persistent = userDAO.findOne(user.getId());
		Assert.notNull(persistent);
		persistent.update(user);
	}

}
