package com.weatherapp.myweatherapp.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.util.List;

@Data
public class CityInfo {

  // Represents the weather data for a city
  @JsonProperty("address")
  private String address;

  @JsonProperty("description")
  private String description;

  @JsonProperty("currentConditions")
  private CurrentConditions currentConditions;

  @JsonProperty("days")
  private List<Days> days;

  @Data
  public static class CurrentConditions {
    // Represents the current weather conditions
    @JsonProperty("temp")
    private String currentTemperature;

    @JsonProperty("sunrise")
    private String sunrise;

    @JsonProperty("sunset")
    private String sunset;

    @JsonProperty("feelslike")
    private String feelslike;

    @JsonProperty("humidity")
    private String humidity;

    @JsonProperty("conditions")
    private String conditions;
  }

  @Data
  public static class Days {
    // Represents the weather conditions for a specific day
    @JsonProperty("datetime")
    private String date;

    @JsonProperty("temp")
    private String currentTemperature;

    @JsonProperty("tempmax")
    private String maxTemperature;

    @JsonProperty("tempmin")
    private String minTemperature;

    @JsonProperty("conditions")
    private String conditions;

    @JsonProperty("description")
    private String description;
  }
}