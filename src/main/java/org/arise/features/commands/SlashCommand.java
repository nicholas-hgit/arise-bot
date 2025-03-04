package org.arise.features.commands;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public interface SlashCommand {

    String getDescription();

    void onSlashCommand(SlashCommandInteractionEvent event);
}
