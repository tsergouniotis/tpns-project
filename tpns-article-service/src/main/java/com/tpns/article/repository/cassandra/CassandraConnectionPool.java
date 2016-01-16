package com.tpns.article.repository.cassandra;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.HostDistance;
import com.datastax.driver.core.PoolingOptions;

@Component
public class CassandraConnectionPool {

/*	@Autowired
	@Value("${cassandra.contact.point.address}")
	private String address;

	@Autowired
	@Value("${cassandra.core.connections.per.host}")
	private String coreConnectionsPerHost;

	@Autowired
	@Value("${cassandra.max.connections.per.host}")
	private String maxConnectionsPerHost;*/

	private Cluster cluster;

	public Cluster getCluster() {
		return cluster;
	}

	@PostConstruct
	public void postConstruct() {

/*		final PoolingOptions poolingOptions = new PoolingOptions();
		poolingOptions.setCoreConnectionsPerHost(HostDistance.LOCAL, Integer.valueOf(coreConnectionsPerHost))
				.setMaxConnectionsPerHost(HostDistance.LOCAL, Integer.valueOf(maxConnectionsPerHost))
				.setCoreConnectionsPerHost(HostDistance.REMOTE, Integer.valueOf(coreConnectionsPerHost))
				.setMaxConnectionsPerHost(HostDistance.REMOTE, Integer.valueOf(maxConnectionsPerHost));

		cluster = Cluster.builder().addContactPoint(address).withPoolingOptions(poolingOptions).build();*/

	}

	@PreDestroy
	public void preDestroy() {
//		cluster.close();
	}

}
