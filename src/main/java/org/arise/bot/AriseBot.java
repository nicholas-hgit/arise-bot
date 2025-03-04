package org.arise.bot;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;
import org.arise.configuration.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.EnumSet;
import java.util.Objects;

public class AriseBot {

    private static final Logger logger = LoggerFactory.getLogger(AriseBot.class);

    private final Configuration config;

    public AriseBot(Configuration config){
        this.config = Objects.requireNonNull(config);
    }

    public void run(){

        EnumSet<GatewayIntent> intents = EnumSet.of(
                GatewayIntent.GUILD_MEMBERS,
                GatewayIntent.MESSAGE_CONTENT
        );

        JDA jda = JDABuilder.createDefault(config.getToken(), intents)
                .build();


        try {

            jda.awaitReady();

        } catch (InterruptedException e) {
             logger.error("Bot has been interrupted during initialization",e);

        }
    }


    public static void onShutDown(){
        logger.info("Arise has been shut down");
    }

}
