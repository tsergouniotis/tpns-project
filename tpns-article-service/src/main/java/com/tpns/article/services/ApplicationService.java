package com.tpns.article.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tpns.article.repository.ApplicationRepository;
import com.tpns.common.domain.Application;

@Service
public class ApplicationService {

	@Autowired
	private ApplicationRepository applicationRepository;

	public List<String> findAll() {
		final List<Application> applications = applicationRepository.findAll();

		final List<String> result = new ArrayList<>();

		Optional.ofNullable(applications).orElse(Collections.emptyList()).forEach(app -> result.add(app.getClientId()));

		return result;
	}
}
