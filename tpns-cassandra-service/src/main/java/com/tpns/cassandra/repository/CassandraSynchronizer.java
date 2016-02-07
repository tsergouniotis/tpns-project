package com.tpns.cassandra.repository;

import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Component;
import org.springframework.data.cassandra.core.CassandraOperations;

@Component
public class CassandraSynchronizer extends ThreadPoolTaskScheduler {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3252858651140724519L;

	private final AtomicInteger checks = new AtomicInteger();

	@Autowired
	private CassandraOperations cassandraOperations;

	@Scheduled(fixedRate = 60000)
	private void checkOnTheDaughters() {

		final String cql = "SELECT article_id FROM tpns.articles";

		cassandraOperations.execute(cql);

	}

}
