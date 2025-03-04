package org.arise;

import org.arise.bot.AriseBot;
import org.arise.configuration.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Path;


public class Application {

    private static final Logger logger = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {


        if(args.length != 1){
            throw new IllegalArgumentException("Expected 1 argument but found " + args.length);
        }

        Path path = Path.of(args[0]);

        Configuration config;

        try {
            config = Configuration.loadFromPath(path);
        } catch (IOException e) {
           logger.error("Could not load configurations from path: {}",path);
           return;
        }

        Runtime.getRuntime().addShutdownHook(new Thread(AriseBot::onShutDown));

        AriseBot bot = new AriseBot(config);
        bot.run();

    }
}
