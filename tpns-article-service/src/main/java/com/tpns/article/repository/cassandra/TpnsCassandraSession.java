package com.tpns.article.repository.cassandra;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Session;

//@Component
//@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class TpnsCassandraSession implements DisposableBean {

//	@Autowired
	private CassandraConnectionPool pool;

	private Session session;

	@PostConstruct
	public void postConstruct() {
//		this.session = pool.getCluster().connect();
	}

	@Override
	public void destroy() throws Exception {
//		this.session.close();
	}

	public ResultSet execute(String query) {
//		return session.execute(query);
		return null;
	}

}
