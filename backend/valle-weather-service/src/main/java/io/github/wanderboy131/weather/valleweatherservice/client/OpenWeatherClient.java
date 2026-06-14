package io.github.wanderboy131.weather.valleweatherservice.client;

import io.github.wanderboy131.weather.valleweatherservice.client.dto.OpenWeatherResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class OpenWeatherClient {
    private final RestTemplate restTemplate;

    @Value("${openweather.api.base-url}")
    private String baseUrl;

    @Value("${openweather.api.key}")
    private String apiKey;

    public OpenWeatherClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    // Recibe latitud y longitud de forma dinámica desde el frontend
    public OpenWeatherResponse fetchCurrentWeather(double latitude, double longitude) {
        String url = UriComponentsBuilder.fromHttpUrl(baseUrl + "/weather")
                .queryParam("lat", latitude)
                .queryParam("lon", longitude)
                .queryParam("appid", apiKey)
                .queryParam("units", "metric") // Retorna temperatura en Celsius y velocidad en m/s
                .toUriString();

        try {
            return restTemplate.getForObject(url, OpenWeatherResponse.class);
        } catch (Exception e) {
            // Manejo de errores si OpenWeather falla o el token expira
            throw new RuntimeException("Failed to fetch weather data from OpenWeather API", e);
        }
    }
}
