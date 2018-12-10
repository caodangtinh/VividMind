package com.test.backend.service;

import java.io.IOException;
import java.util.List;

public interface TourService {
    List<String> findTourNames(String filter);

    void refreshTours(String filter) throws IOException;
}
