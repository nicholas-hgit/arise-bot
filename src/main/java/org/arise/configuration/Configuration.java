package org.arise.configuration;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Objects;

public class Configuration {

    private final String token;

    @JsonCreator
    public Configuration(@JsonProperty(value = "token",required = true) String token){
        this.token = Objects.requireNonNull(token);
    }

    public static Configuration loadFromPath(Path path) throws IOException {
        return new ObjectMapper().readValue(path.toFile(), Configuration.class);
    }

    public String getToken(){
        return token;
    }
}
