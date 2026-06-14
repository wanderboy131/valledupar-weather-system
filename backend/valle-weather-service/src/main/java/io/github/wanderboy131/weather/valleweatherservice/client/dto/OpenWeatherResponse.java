package io.github.wanderboy131.weather.valleweatherservice.client.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class OpenWeatherResponse {
    private MainData main;
    private WindData wind;
    private CloudData clouds;
    private List<WeatherDescription> weather;

    // Getters y Setters
    public MainData getMain() { return main; }
    public void setMain(MainData main) { this.main = main; }

    public WindData getWind() { return wind; }
    public void setWind(WindData wind) { this.wind = wind; }

    public CloudData getClouds() { return clouds; }
    public void setClouds(CloudData clouds) { this.clouds = clouds; }

    public List<WeatherDescription> getWeather() { return weather; }
    public void setWeather(List<WeatherDescription> weather) { this.weather = weather; }

    // --- Clases Internas Estáticas para mapear los sub-objetos del JSON ---

    public static class MainData {
        private double temp;
        private int humidity;

        @JsonProperty("grnd_level") // Esto le dice a Jackson que mapee el snake_case del JSON a camelCase en Java
        private double groundLevelPressure;

        // Getters y Setters
        public double getTemp() { return temp; }
        public void setTemp(double temp) { this.temp = temp; }

        public int getHumidity() { return humidity; }
        public void setHumidity(int humidity) { this.humidity = humidity; }

        public double getGroundLevelPressure() { return groundLevelPressure; }
        public void setGroundLevelPressure(double groundLevelPressure) { this.groundLevelPressure = groundLevelPressure; }
    }

    public static class WindData {
        private double speed;
        private double deg;

        // Getters y Setters
        public double getSpeed() { return speed; }
        public void setSpeed(double speed) { this.speed = speed; }

        public double getDeg() { return deg; }
        public void setDeg(double deg) { this.deg = deg; }
    }

    public static class CloudData {
        @JsonProperty("all") // OpenWeather manda el porcentaje de nubes en la propiedad "all"
        private int cloudCover;

        // Getters y Setters
        public int getCloudCover() { return cloudCover; }
        public void setCloudCover(int cloudCover) { this.cloudCover = cloudCover; }
    }

    public static class WeatherDescription {
        private String description;
        private String icon;

        // Getters y Setters
        public String getDescription() { return description; }
        public void setDescription(String description) { this.description = description; }

        public String getIcon() { return icon; }
        public void setIcon(String icon) { this.icon = icon; }
    }
}
