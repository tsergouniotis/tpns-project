package com.tpns.article.managers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tpns.article.repository.ApplicationRepository;
import com.tpns.common.domain.Application;

@Component
public class ApplicationManager {

	@Autowired
	private ApplicationRepository applicationDao;

	public List<Application> findAll() {
		return applicationDao.findAll();
	}
}
