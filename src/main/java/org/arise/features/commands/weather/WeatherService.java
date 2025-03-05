package org.arise.features.commands.weather;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.arise.configuration.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.Optional;

public class WeatherService {

    private static  final Logger logger = LoggerFactory.getLogger(WeatherService.class);
    private final String apiKey;

    public WeatherService(Configuration config){
        this.apiKey = Objects.requireNonNull(config.getWeatherApiKey());
    }

    public Optional<WeatherData> getCurrentWeatherData(String city){

        try(HttpClient client = HttpClient.newHttpClient()) {

            //encode cities with multiple words into url format
            city = URLEncoder.encode(city, StandardCharsets.UTF_8);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://api.weatherapi.com/v1/current.json?key=%s&q=%s&aqi=no".formatted(apiKey,city)))
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            WeatherData data = new ObjectMapper().readValue(response.body(),WeatherData.class);

            return Optional.of(data);

        } catch (IOException | InterruptedException e) {

            logger.error("an error occurred while fetching weather data data",e);
            return Optional.empty();
        }
    }
}
