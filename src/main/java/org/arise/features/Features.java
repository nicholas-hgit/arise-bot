package org.arise.features;

import org.arise.configuration.Configuration;
import org.arise.features.commands.basic.PingCommand;
import org.arise.features.commands.weather.WeatherCommand;
import org.arise.features.commands.weather.WeatherService;

import java.util.ArrayList;
import java.util.Collection;


public class Features {

    public Features() {
        throw new UnsupportedOperationException("construction not supported");
    }

    public static Collection<Feature> load(Configuration config){

        Collection<Feature> features = new ArrayList<>();

        //utilities
        WeatherService weatherService = new WeatherService(config);

        //slash commands
        features.add(new PingCommand());
        features.add(new WeatherCommand(weatherService));

        return features;
    }
}


