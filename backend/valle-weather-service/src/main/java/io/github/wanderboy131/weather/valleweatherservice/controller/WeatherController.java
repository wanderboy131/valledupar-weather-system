package io.github.wanderboy131.weather.valleweatherservice.controller;

import io.github.wanderboy131.weather.valleweatherservice.dto.WeatherAnalysisResponseDto;
import io.github.wanderboy131.weather.valleweatherservice.service.WeatherService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/weather")
@CrossOrigin(origins = "*")
public class WeatherController {

    private final WeatherService weatherService;

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/analyze")
    public ResponseEntity<WeatherAnalysisResponseDto> analyzeWeather(@RequestParam double lat, @RequestParam double lon) {
        WeatherAnalysisResponseDto analysis = weatherService.analyzeWeather(lat, lon);
        return ResponseEntity.ok(analysis);
    }



}
