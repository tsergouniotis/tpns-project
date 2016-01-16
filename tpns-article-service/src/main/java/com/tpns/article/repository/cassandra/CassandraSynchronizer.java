package com.tpns.article.repository.cassandra;

import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Component;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;

@Component
public class CassandraSynchronizer extends ThreadPoolTaskScheduler {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3252858651140724519L;

	private final AtomicInteger checks = new AtomicInteger();

//	@Autowired
	private TpnsCassandraSession session;

	@Scheduled(fixedRate = 60000)
	private void checkOnTheDaughters() {

/*		final String query = "SELECT article_id FROM tpns.articles";
		final ResultSet result = session.execute(query);

		for (final Row row : result) {

			System.out.println(row.getLong("article_id"));

		}
		checks.incrementAndGet();*/
	}

}
