package org.arise.features.commands.weather;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
class Condition {

    private final String description;
    private final String iconUrl;


    @JsonCreator
    Condition(
            @JsonProperty(value = "text",required = true) String description,
            @JsonProperty(value = "icon",required = true) String iconUrl
    ){
        this.description = Objects.requireNonNull(description);
        this.iconUrl = Objects.requireNonNull(iconUrl);
    }

    public String getDescription(){
        return description;
    }

    //e.g. iconUrl = //cdn.weatherapi.com/weather/64x64/night/143.png
    public String getIconUrl(){
        return "https:" + iconUrl;
    }
}
