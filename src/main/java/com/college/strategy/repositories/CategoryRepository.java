package com.college.strategy.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.college.strategy.entities.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
	
}
