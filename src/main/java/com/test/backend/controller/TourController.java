package com.test.backend.controller;

import com.test.backend.service.TourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin("*")
public class TourController {

    @Autowired
    private TourService tourService;

    @GetMapping("/tours")
    public List<String> getTourNames(@RequestParam(required = false) String filter) {
        return tourService.findTourNames(filter);
    }

    @PostMapping("/tours/refresh")
    public void refreshTours(@RequestParam(required = false) String filter, HttpServletResponse response) {
        try {
            this.tourService.refreshTours(filter);
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            try {
                response.getWriter().write("Error reading JSON file.");
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

}
