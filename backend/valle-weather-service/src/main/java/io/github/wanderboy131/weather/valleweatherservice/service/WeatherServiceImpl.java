package io.github.wanderboy131.weather.valleweatherservice.service;

import io.github.wanderboy131.weather.valleweatherservice.client.OpenWeatherClient;
import io.github.wanderboy131.weather.valleweatherservice.client.dto.OpenWeatherResponse;
import io.github.wanderboy131.weather.valleweatherservice.domain.calculator.WeatherAnalyzer;
import io.github.wanderboy131.weather.valleweatherservice.dto.WeatherAnalysisResponseDto;
import org.springframework.stereotype.Service;

@Service
public class WeatherServiceImpl implements WeatherService {
    private final OpenWeatherClient weatherClient;
     private final WeatherAnalyzer weatherAnalyzer;


    public WeatherServiceImpl(OpenWeatherClient weatherClient, WeatherAnalyzer weatherAnalyzer) {

        this.weatherClient = weatherClient;
        this.weatherAnalyzer = weatherAnalyzer;
    }

    @Override
    public WeatherAnalysisResponseDto analyzeWeather(double latitude, double longitude) {

        OpenWeatherResponse apiResponse = weatherClient.fetchCurrentWeather(latitude, longitude);


        double temperature = apiResponse.getMain().getTemp();
        int humidity = apiResponse.getMain().getHumidity();
        double windSpeed = apiResponse.getWind().getSpeed();
        double windDirection = apiResponse.getWind().getDeg();
        double pressure = apiResponse.getMain().getGroundLevelPressure();
        int cloudCover = apiResponse.getClouds() != null ? apiResponse.getClouds().getCloudCover() : 0;

        var weather = apiResponse.getWeather();
        var firstWeather = weather.isEmpty() ? null : weather.get(0);

        String descriptionText = firstWeather != null ? firstWeather.getDescription() : "Sin descripción";
        String icon = firstWeather != null ? firstWeather.getIcon() : "01d";



        double rainProbability = weatherAnalyzer.calculateRainProbability(
                temperature,
                humidity,
                pressure,
                cloudCover,
                windSpeed,
                windDirection
        );


        WeatherAnalysisResponseDto response = new WeatherAnalysisResponseDto();
        response.setRainProbability(rainProbability);
        response.setDescription(descriptionText);
        response.setTemperature(temperature);
        response.setHumidity(humidity);
        response.setWindSpeed(windSpeed);
        response.setWindDirection(windDirection);
        response.setGroundLevelPressure(pressure);
        response.setIconCode(icon);

        return response;
    }
}
