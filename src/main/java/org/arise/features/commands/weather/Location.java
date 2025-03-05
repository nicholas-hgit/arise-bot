package org.arise.features.commands.weather;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
class Location {

    private final String name;
    private final String country;
    private final LocalDateTime currentDateTime;


    @JsonCreator
    Location(
            @JsonProperty(value = "name", required = true) String name,
            @JsonProperty(value = "country",required = true) String country,
            @JsonProperty(value = "localtime",required = true) String localTime
    ){

        this.name = Objects.requireNonNull(name);
        this.country = Objects.requireNonNull(country);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        this.currentDateTime = LocalDateTime.parse(localTime,formatter);
    }

    public String getName(){
        return name;
    }

    public String getCountry(){
        return country;
    }

    public LocalDateTime getCurrentDateTime(){
        return currentDateTime;
    }
}
