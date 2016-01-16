package com.tpns.article.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tpns.common.domain.Application;

public interface ApplicationRepository extends JpaRepository<Application, Long> {

	List<Application> findByIds(List<String> ids);

}
