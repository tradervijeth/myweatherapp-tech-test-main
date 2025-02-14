package com.weatherapp.myweatherapp.service;

import com.weatherapp.myweatherapp.model.CityInfo;
import com.weatherapp.myweatherapp.repository.VisualcrossingRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class WeatherServiceTest {

    @Mock
    private VisualcrossingRepository weatherRepo;

    @InjectMocks
    private WeatherService weatherService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldReturnCityWithLongestDay() {
        // Test to verify the city with the longest daylight hours
        CityInfo london = createCityInfo("06:00:00", "20:00:00", "clear");
        CityInfo paris = createCityInfo("06:30:00", "19:30:00", "clear");

        when(weatherRepo.getByCity("London")).thenReturn(london);
        when(weatherRepo.getByCity("Paris")).thenReturn(paris);

        String result = weatherService.getCityWithLongestDay("London", "Paris");
        assertEquals("London", result);
    }

    @Test
    void shouldCheckRainingCities() {
        // Test to verify which city is currently experiencing rain
        CityInfo london = createCityInfo("06:00:00", "20:00:00", "rain");
        CityInfo paris = createCityInfo("06:30:00", "19:30:00", "clear");

        when(weatherRepo.getByCity("London")).thenReturn(london);
        when(weatherRepo.getByCity("Paris")).thenReturn(paris);

        String result = weatherService.checkRainingCity("London", "Paris");
        assertEquals("London", result);
    }

    private CityInfo createCityInfo(String sunrise, String sunset, String conditions) {
        // Helper method to create CityInfo objects for testing
        CityInfo cityInfo = new CityInfo();
        CityInfo.CurrentConditions currentConditions = new CityInfo.CurrentConditions();
        currentConditions.setSunrise(sunrise);
        currentConditions.setSunset(sunset);
        currentConditions.setConditions(conditions);
        cityInfo.setCurrentConditions(currentConditions);
        return cityInfo;
    }
}