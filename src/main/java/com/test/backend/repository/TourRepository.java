package com.test.backend.repository;

import com.test.backend.model.Tour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TourRepository extends JpaRepository<Tour, Integer> {

    List<Tour> findByNameContainingIgnoreCase(String value);
}
