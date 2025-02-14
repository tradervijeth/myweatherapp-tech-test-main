package com.weatherapp.myweatherapp.controller;

import com.weatherapp.myweatherapp.model.CityInfo;
import com.weatherapp.myweatherapp.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestClientException;

@RestController
public class WeatherController {

    @Autowired
    WeatherService weatherService;

    @GetMapping("/forecast/{city}")
    public ResponseEntity<?> forecastByCity(@PathVariable("city") String city) {
        // Endpoint to fetch weather forecast for a specified city
        try {
            CityInfo ci = weatherService.forecastByCity(city);
            return ResponseEntity.ok(ci);
        } catch (RestClientException e) {
            return ResponseEntity.badRequest()
                .body("Unable to fetch weather data for " + city);
        }
    }

    @GetMapping("/compare-daylight/{city1}/{city2}")
    public ResponseEntity<?> compareDaylight(
            @PathVariable("city1") String city1,
            @PathVariable("city2") String city2) {
        // Endpoint to compare daylight hours between two cities
        try {
            String cityWithLongestDay = weatherService.getCityWithLongestDay(city1, city2);
            return ResponseEntity.ok(cityWithLongestDay + " has longer daylight hours");
        } catch (RestClientException e) {
            return ResponseEntity.badRequest()
                .body("Unable to compare daylight hours");
        }
    }

    @GetMapping("/check-rain/{city1}/{city2}")
    public ResponseEntity<?> checkRain(
            @PathVariable("city1") String city1,
            @PathVariable("city2") String city2) {
        // Endpoint to check which of the two cities is currently experiencing rain
        try {
            String rainingCity = weatherService.checkRainingCity(city1, city2);
            return ResponseEntity.ok("Currently raining in: " + rainingCity);
        } catch (RestClientException e) {
            return ResponseEntity.badRequest()
                .body("Unable to check rain conditions");
        }
    }
}