package io.github.wanderboy131.weather.valleweatherservice.dto;

public class WeatherAnalysisResponseDto {
    private double rainProbability;
    private String description;
    private double temperature;
    private int humidity;
    private double windSpeed;
    private double windDirection;
    private double groundLevelPressure;
    private String iconCode;

    public WeatherAnalysisResponseDto() {
    }


    public WeatherAnalysisResponseDto(double rainProbability, String description, double temperature,
                                      int humidity, double windSpeed, double windDirection,
                                      double groundLevelPressure, String iconCode) {
        this.rainProbability = rainProbability;
        this.description = description;
        this.temperature = temperature;
        this.humidity = humidity;
        this.windSpeed = windSpeed;
        this.windDirection = windDirection;
        this.groundLevelPressure = groundLevelPressure;
        this.iconCode = iconCode;
    }


    public double getRainProbability() { return rainProbability; }
    public void setRainProbability(double rainProbability) { this.rainProbability = rainProbability; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public double getTemperature() { return temperature; }
    public void setTemperature(double temperature) { this.temperature = temperature; }

    public int getHumidity() { return humidity; }
    public void setHumidity(int humidity) { this.humidity = humidity; }

    public double getWindSpeed() { return windSpeed; }
    public void setWindSpeed(double windSpeed) { this.windSpeed = windSpeed; }

    public double getWindDirection() { return windDirection; }
    public void setWindDirection(double windDirection) { this.windDirection = windDirection; }

    public double getGroundLevelPressure() { return groundLevelPressure; }
    public void setGroundLevelPressure(double groundLevelPressure) { this.groundLevelPressure = groundLevelPressure; }

    public String getIconCode() { return iconCode; }
    public void setIconCode(String iconCode) { this.iconCode = iconCode; }
}

