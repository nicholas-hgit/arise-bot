package org.arise.bot;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.requests.restaction.CommandListUpdateAction;
import org.arise.configuration.Configuration;
import org.arise.features.Feature;
import org.arise.features.Features;
import org.arise.features.commands.BotCommand;
import org.arise.features.commands.CommandContext;
import org.arise.features.commands.CommandProvider;
import org.arise.features.listeners.BotCommandListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.EnumSet;
import java.util.List;
import java.util.Objects;


@SuppressWarnings("ResultOfMethodCallIgnored")
public class AriseBot implements CommandProvider {

    private static final Logger logger = LoggerFactory.getLogger(AriseBot.class);

    private final Configuration config;
    private final Collection<Feature> features;

    public AriseBot(Configuration config){
        this.config = Objects.requireNonNull(config);
        this.features = Features.load(config);
    }

    public void run(){

        EnumSet<GatewayIntent> intents = EnumSet.of(
                GatewayIntent.GUILD_MEMBERS,
                GatewayIntent.MESSAGE_CONTENT
        );

        JDA jda = JDABuilder.createDefault(config.getToken(), intents)
                .addEventListeners(new BotCommandListener(getCommands()))
                .build();


        try {

            jda.awaitReady();
            loadGlobalCommands(jda);
            loadGuildCommands(jda);


        } catch (InterruptedException e) {
             logger.error("Bot has been interrupted during initialization",e);

        }
    }


    public static void onShutDown(){
        logger.info("Bot has been shut down");
    }

    private void loadGlobalCommands(JDA jda){

        CommandListUpdateAction actions = jda.updateCommands();

        getCommands()
                .filter(command -> command.getContext().equals(CommandContext.GLOBAL))
                .map(BotCommand::getData)
                .forEach(actions::addCommands);

        actions.queue();

    }

    private void loadGuildCommands(JDA jda){

        Collection<CommandData> commands =  getCommands()
                .filter(command -> command.getContext().equals(CommandContext.GUILD))
                .map(BotCommand::getData)
                .toList();


        for(Guild guild : getGuilds(jda)){

            CommandListUpdateAction actions = guild.updateCommands();
            commands.forEach(actions::addCommands);
            actions.queue();
        }

    }

    private List<Guild> getGuilds(JDA jda){
        return jda.getGuildCache().stream().toList();
    }

    @Override
    public Collection<Feature> getFeatures() {
        return features;
    }
}
