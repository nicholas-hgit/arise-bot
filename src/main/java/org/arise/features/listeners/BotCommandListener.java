package org.arise.features.listeners;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.arise.features.Feature;
import org.arise.features.commands.BotCommand;
import org.arise.features.commands.SlashCommand;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BotCommandListener extends ListenerAdapter {

    private final Map<String, BotCommand> nameToBotCommand;
    private final ExecutorService COMMAND_EXECUTOR;

    public BotCommandListener(Stream<BotCommand> commands){

        this.nameToBotCommand =  commands.collect(Collectors.toMap(BotCommand::getName, Function.identity()));
        this.COMMAND_EXECUTOR = Executors.newCachedThreadPool();
    }

    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {

        SlashCommand command = (SlashCommand) nameToBotCommand.get(event.getName());
        COMMAND_EXECUTOR.execute(() -> command.onSlashCommand(event));
    }
}
