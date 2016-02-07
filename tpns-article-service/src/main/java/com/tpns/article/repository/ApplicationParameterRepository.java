package com.tpns.article.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tpns.domain.article.ApplicationParameter;

public interface ApplicationParameterRepository extends JpaRepository<ApplicationParameter, String> {

	String findByKey(String key);

}
