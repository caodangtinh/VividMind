package com.test.backend.service;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface TourService {
	public List<String> findTourNames(String filter);
	
	public void refreshTours(String filter) throws JsonProcessingException, IOException;
}
