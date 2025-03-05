package org.arise.features.commands.weather;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherData {

    private final String country;
    private final String city;
    private final String temperature;
    private final String humidity;
    private final String iconUrl;
    private final String description;
    private final String windSpeed;
    private final String windDirection;
    private final String precipitation;
    private final DayOfWeek dayOfWeek;
    private final LocalDateTime currentDateTime;


    @JsonCreator
    WeatherData(
            @JsonProperty(value = "location",required = true) Location location,
            @JsonProperty(value = "current",required = true) Current current
    ){

        Objects.requireNonNull(location);
        Objects.requireNonNull(current);

        this.country = location.getCountry();
        this.city = location.getName();
        this.currentDateTime = location.getCurrentDateTime();
        this.dayOfWeek = currentDateTime.getDayOfWeek();

        this.temperature = current.getTemperature();
        this.humidity = current.getHumidity();
        this.iconUrl = current.getCondition().getIconUrl();
        this.description = current.getCondition().getDescription();
        this.windSpeed = current.getWindSpeed();
        this.windDirection = current.getWindDirection();
        this.precipitation = current.getPrecipitation();
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public String getTemperature() {
        return temperature;
    }

    public String getHumidity() {
        return humidity;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public String getDescription() {
        return description;
    }

    public String getWindSpeed() {
        return windSpeed;
    }

    public String getWindDirection() {
        return windDirection;
    }

    public String getPrecipitation() {
        return precipitation;
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public LocalDateTime getCurrentDateTime() {
        return currentDateTime;
    }
}
