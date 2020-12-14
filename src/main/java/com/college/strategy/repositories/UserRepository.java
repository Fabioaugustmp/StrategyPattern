package com.college.strategy.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.college.strategy.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
}
