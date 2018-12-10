package com.test.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.test.backend.model.Tour;

@Repository
public interface TourRepository extends JpaRepository<Tour, Integer> {
	
	public List<Tour> findByNameContainingIgnoreCase(String value);
}
