package org.arise.features.commands.weather;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
class Current {

    private final String temperature;
    private final String humidity;
    private final String precipitation;
    private final String windSpeed;
    private final String windDirection;
    private final Condition condition;


    @JsonCreator
    Current(
            @JsonProperty(value = "temp_c",required = true) String temperature,
            @JsonProperty(value = "humidity",required = true) String humidity,
            @JsonProperty(value = "wind_kph",required = true) String windSpeed,
            @JsonProperty(value = "wind_dir",required = true) String windDirection,
            @JsonProperty(value = "precip_mm",required = true) String precipitation,
            @JsonProperty(value = "condition",required = true) Condition condition
    ){

        this.temperature = Objects.requireNonNull(temperature);
        this.humidity = Objects.requireNonNull(humidity);
        this.precipitation = Objects.requireNonNull(precipitation);
        this.windSpeed = Objects.requireNonNull(windSpeed);
        this.windDirection = Objects.requireNonNull(windDirection);
        this.condition = Objects.requireNonNull(Objects.requireNonNull(condition));
    }

    public String getTemperature() {
        return temperature;
    }

    public String getHumidity() {
        return humidity;
    }

    public String getPrecipitation() {
        return precipitation;
    }

    public String getWindSpeed() {
        return windSpeed;
    }

    public String getWindDirection() {
        return windDirection;
    }

    public Condition getCondition() {
        return condition;
    }
}
