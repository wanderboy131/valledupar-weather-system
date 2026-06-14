package io.github.wanderboy131.weather.valleweatherservice.service;

import io.github.wanderboy131.weather.valleweatherservice.dto.WeatherAnalysisResponseDto;

public interface WeatherService {
    WeatherAnalysisResponseDto analyzeWeather(double latitude, double longitude);
}
