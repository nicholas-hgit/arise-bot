package org.arise.configuration;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Objects;

public class Configuration {

    private final String token;
    private final String weatherApiKey;

    @JsonCreator
    public Configuration(
            @JsonProperty(value = "token",required = true) String token,
            @JsonProperty(value = "weatherApiKey",required = true) String weatherApiKey
    ){
        this.token = Objects.requireNonNull(token);
        this.weatherApiKey = Objects.requireNonNull(weatherApiKey);
    }

    public static Configuration loadFromPath(Path path) throws IOException {
        return new ObjectMapper().readValue(path.toFile(), Configuration.class);
    }

    public String getToken(){
        return token;
    }

    public String getWeatherApiKey() {
        return weatherApiKey;
    }
}
