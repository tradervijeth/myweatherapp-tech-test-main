package com.weatherapp.myweatherapp.service;

import com.weatherapp.myweatherapp.model.CityInfo;
import com.weatherapp.myweatherapp.repository.VisualcrossingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalTime;
import java.time.Duration;
import java.time.format.DateTimeFormatter;

@Service
public class WeatherService {

    @Autowired
    VisualcrossingRepository weatherRepo;

    public CityInfo forecastByCity(String city) {
        // Fetches weather forecast for the specified city
        return weatherRepo.getByCity(city);
    }

    public String getCityWithLongestDay(String city1, String city2) {
        // Compares daylight hours between two cities and returns the city with the longest day
        CityInfo cityInfo1 = weatherRepo.getByCity(city1);
        CityInfo cityInfo2 = weatherRepo.getByCity(city2);

        Duration daylight1 = calculateDaylightHours(cityInfo1);
        Duration daylight2 = calculateDaylightHours(cityInfo2);

        return daylight1.compareTo(daylight2) > 0 ? city1 : city2;
    }

    public String checkRainingCity(String city1, String city2) {
        // Checks which of the two cities is currently experiencing rain
        CityInfo cityInfo1 = weatherRepo.getByCity(city1);
        CityInfo cityInfo2 = weatherRepo.getByCity(city2);

        boolean isRaining1 = isRaining(cityInfo1);
        boolean isRaining2 = isRaining(cityInfo2);

        if (isRaining1 && isRaining2) return "Both cities";
        if (isRaining1) return city1;
        if (isRaining2) return city2;
        return "None";
    }

    private Duration calculateDaylightHours(CityInfo cityInfo) {
        // Calculates the duration of daylight hours based on sunrise and sunset times
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        
        LocalTime sunrise = LocalTime.parse(cityInfo.getCurrentConditions().getSunrise(), formatter);
        LocalTime sunset = LocalTime.parse(cityInfo.getCurrentConditions().getSunset(), formatter);
        
        return Duration.between(sunrise, sunset);
    }

    private boolean isRaining(CityInfo cityInfo) {
        // Determines if it is currently raining based on weather conditions
        String conditions = cityInfo.getCurrentConditions().getConditions().toLowerCase();
        return conditions.contains("rain") || conditions.contains("drizzle");
    }
}