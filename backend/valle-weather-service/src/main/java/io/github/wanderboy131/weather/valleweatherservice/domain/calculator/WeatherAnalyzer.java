package io.github.wanderboy131.weather.valleweatherservice.domain.calculator;

import org.springframework.stereotype.Component;

@Component
public class WeatherAnalyzer {
    // Wind direction thresholds for Valledupar's microclimate
    private static final double WIND_DIR_SSW_MIN = 185.0;
    private static final double WIND_DIR_SSW_MAX = 225.0;
    private static final double WIND_DIR_NE_MIN = 40.0;
    private static final double WIND_DIR_NE_MAX = 100.0;

    public double calculateRainProbability(
            double temperature,
            int humidity,
            double groundLevelPressure,
            int cloudCover,
            double windSpeed,
            double windDirection
    ) {
        // 1. Determine local wind heuristic factor
        double windFactor = determineWindFactor(windSpeed, windDirection);

        // 2. Calculate Normalized Dew Point using Magnus Formula
        double dewPointNormalized = calculateNormalizedDewPoint(temperature, humidity);

        // 3. Normalize secondary environmental variables
        double normalizedHumidity = (humidity - 16.0) / (100.0 - 16.0);
        double normalizedPressure = Math.max(0, Math.min(1, (1005.0 - groundLevelPressure) / (1005.0 - 980.0)));
        double normalizedWind = Math.min(windSpeed / 25.0, 1.0);

        // Anti-false positive cloud cover adjustment based on humidity
        double adjustedClouds = (cloudCover / 100.0) * (humidity > 50 ? 1.1 : 0.7);

        // 4. Score calculation based on regional weights (DHIME / OpenMeteo benchmarks)
        double score = (dewPointNormalized * 0.35)
                + (adjustedClouds      * 0.30)
                + (normalizedWind      * 0.25)
                + (normalizedHumidity  * 0.05)
                + (normalizedPressure  * 0.05);

        // 5. Apply the wind direction factor
        double finalProbability = (score * 100.0) * windFactor;

        // 6. Safety boundaries caps (0% - 100%)
        finalProbability = Math.max(0, Math.min(100, finalProbability));

        // Clear sky penalty to prevent false positives under high humidity conditions
        if (cloudCover < 10) {
            finalProbability *= 0.3;
        }

        return Math.round(finalProbability * 100.0) / 100.0;
    }

    private double determineWindFactor(double speed, double direction) {
        if (direction >= WIND_DIR_SSW_MIN && direction <= WIND_DIR_SSW_MAX) {
            return (speed > 12.0) ? 1.25 : 1.10; // S/SSW winds act as rain catalysts
        } else if (direction >= WIND_DIR_NE_MIN && direction <= WIND_DIR_NE_MAX) {
            return 0.65; // NE Trade winds act as rain suppressors (dry air)
        }
        return 1.0; // Variable or calm winds
    }

    private double calculateNormalizedDewPoint(double temperature, double humidity) {
        double alpha = ((17.625 * temperature) / (243.04 + temperature)) + Math.log(humidity / 100.0);
        double dewPoint = (243.04 * alpha) / (17.625 - alpha);
        double deltaDew = temperature - dewPoint;
        return Math.exp(-deltaDew / 4.5);
    }
}
