package com.test.backend.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.backend.model.Tour;
import com.test.backend.repository.TourRepository;
import com.test.backend.service.TourService;


@Service
public class TourServiceImpl implements TourService {
	
	private static final String ID_PATH = "id";
	private static final String NAME_PATH = "name";
	private static final String LONG_DESC_PATH = "longDesc";

	@Autowired
	private TourRepository tourRepository;

	@Transactional(readOnly = true)
	public List<String> findTourNames(String filter) {
		List<Tour> tours;
		if(filter == null || filter.isEmpty())
		{
			tours = this.tourRepository.findAll();
		}
		else {
			tours = this.tourRepository.findByNameContainingIgnoreCase(filter);
		}
		
		List<String> tourNames = new ArrayList<>();
		for(Tour tour: tours)
		{
			tourNames.add(tour.getName());
		}
		return tourNames;
	}

	@Override
	@Transactional
	public void refreshTours(String filter) throws JsonProcessingException, IOException {

		// Delete old tours from DB
		this.tourRepository.deleteAll();

		ObjectMapper mapper = new ObjectMapper();
		File jsonInputFile = new File("tours.json");

		JsonNode rootNode;

		rootNode = mapper.readTree(jsonInputFile);

		// read employee id
		JsonNode toursNode = rootNode.path("tours");
		Iterator<JsonNode> toursItr = toursNode.elements();
		Tour tour;
		while (toursItr.hasNext()) {
			JsonNode tourNode = toursItr.next();
			JsonNode idNode = tourNode.path(ID_PATH);
			JsonNode nameNode = tourNode.path(NAME_PATH);
			if (filter != null && !filter.isEmpty()) {
				if (!nameNode.textValue().toLowerCase().contains(filter.toLowerCase())) {
					continue;
				}
			}
			JsonNode longDescNode = tourNode.path(LONG_DESC_PATH);
			tour = new Tour();
			tour.setId(idNode.intValue());
			tour.setName(nameNode.textValue());
			tour.setLongDesc(longDescNode.textValue());
			this.tourRepository.save(tour);
		}

	}

}
