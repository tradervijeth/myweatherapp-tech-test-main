# MyWeather App Tech Test

Welcome to the MyWeather App Tech Test.

## How the Project Works

The MyWeather App is a Java application built with the Spring framework. It interacts with the Visual Crossing Weather API to fetch weather data for specified cities. The application provides endpoints to compare daylight hours between two cities and to check which city is currently experiencing rain.

### Key Components

- **WeatherController**: Handles HTTP requests and responses.
- **WeatherService**: Contains the business logic for fetching and processing weather data.
- **VisualcrossingRepository**: Interacts with the Visual Crossing Weather API to fetch weather data.
- **CityInfo**: Represents the weather data model.

## What I Did to Implement It

### Features Implemented

1. **Daylight Hours Comparison**: 
   - Added an endpoint to compare the length of daylight hours between two cities.
   - Implemented logic to calculate daylight hours based on sunrise and sunset times.

2. **Rain Check**: 
   - Added an endpoint to check which of the two cities is currently experiencing rain.
   - Implemented logic to determine if it is raining based on weather conditions.

### Steps Taken

1. **Updated `CityInfo` Model**:
   - Added necessary fields and used Lombok annotations to generate getters and setters.

2. **Implemented Business Logic in `WeatherService`**:
   - Added methods to calculate daylight hours and check for rain.
   - Used the Visual Crossing Weather API to fetch weather data.

3. **Added Endpoints in `WeatherController`**:
   - Created endpoints for daylight hours comparison and rain check.
   - Included exception handling for API errors.

4. **Wrote Unit Tests**:
   - Added unit tests to verify the functionality of the new features.
   - Used Mockito to mock dependencies.

## API Documentation

### Endpoints

#### 1. Daylight Hours Comparison

**Endpoint**: `/compare-daylight/{city1}/{city2}`

**Method**: GET

**Description**: Compares the length of daylight hours between two cities and returns the city with the longest day.

**Parameters**:
- `city1`: Name of the first city.
- `city2`: Name of the second city.

**Response**:
- `200 OK`: Returns the name of the city with the longest daylight hours.
- `400 Bad Request`: If there is an error fetching weather data.

**Example**:
- GET /compare-daylight/London/Paris Response: "London has longer daylight hours"

#### 2. Rain Check

**Endpoint**: `/check-rain/{city1}/{city2}`

**Method**: GET

**Description**: Checks which of the two cities is currently experiencing rain.

**Parameters**:
- `city1`: Name of the first city.
- `city2`: Name of the second city.

**Response**:
- `200 OK`: Returns the name of the city where it is currently raining.
- `400 Bad Request`: If there is an error fetching weather data.

**Example**:
- GET /check-rain/London/Paris Response: "Currently raining in: London"

### Prerequisites

- [Java SDK 17](https://openjdk.java.net/projects/jdk/17/)
- [Maven 3.6.3+](https://maven.apache.org/install.html)
- API key for [Visual Crossing Weather API](https://www.visualcrossing.com/weather-data-editions)
- Create a free account on the above link and add your key to the `weather.visualcrossing.key` field in `src/main/resources/application.properties`.