package com.tpns.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tpns.domain.user.User;

public interface UserRepository extends JpaRepository<User, Long> {

	User findByUsername(String username);

}
