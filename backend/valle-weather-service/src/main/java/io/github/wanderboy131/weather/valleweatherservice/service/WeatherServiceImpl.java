package io.github.wanderboy131.weather.valleweatherservice.service;

import io.github.wanderboy131.weather.valleweatherservice.client.OpenWeatherClient;
import io.github.wanderboy131.weather.valleweatherservice.client.dto.OpenWeatherResponse;
import io.github.wanderboy131.weather.valleweatherservice.dto.WeatherAnalysisResponseDto;
import org.springframework.stereotype.Service;

@Service
public class WeatherServiceImpl implements WeatherService {
    private final OpenWeatherClient weatherClient;
    // Aquí puedes declarar tu clase de lógica matemática de la carpeta domain si la necesitas
    // private final WeatherAnalyzer weatherAnalyzer;

    // Inyección por constructor del cliente que acabas de explicar y entender a la perfección
    public WeatherServiceImpl(OpenWeatherClient weatherClient) {
        this.weatherClient = weatherClient;
    }

    @Override
    public WeatherAnalysisResponseDto analyzeWeather(double latitude, double longitude) {
        // 1. Llamamos al cliente externo usando las coordenadas dinámicas del navigator
        OpenWeatherResponse apiResponse = weatherClient.fetchCurrentWeather(latitude, longitude);

        // 2. Extraemos las métricas desde la estructura de la API
        double temperature = apiResponse.getMain().getTemp();
        int humidity = apiResponse.getMain().getHumidity();
        double windSpeed = apiResponse.getWind().getSpeed();
        double windDirection = apiResponse.getWind().getDeg();
        double pressure = apiResponse.getMain().getGroundLevelPressure(); // Presión a nivel de suelo

        // OpenWeather maneja una lista de condiciones climáticas, sacamos la primera
        String descriptionText = apiResponse.getWeather().isEmpty() ? "Sin descripción"
                : apiResponse.getWeather().get(0).getDescription();
        String icon = apiResponse.getWeather().isEmpty() ? "01d"
                : apiResponse.getWeather().get(0).getIcon();

        // 3. Probabilidad simulada (luego la cambias por tu lógica matemática de domain)
        double rainProbability = 45.5;

        // 4. Construimos el DTO con tus setters reales
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
