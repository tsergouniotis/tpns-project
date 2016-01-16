package com.tpns.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tpns.user.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {

	@Query(name="User.findByUsername")
	User findByUsername(@Param("username") String username);

}
