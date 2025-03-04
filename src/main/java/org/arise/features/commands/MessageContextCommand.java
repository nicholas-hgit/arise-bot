package org.arise.features.commands;

import net.dv8tion.jda.api.events.interaction.command.MessageContextInteractionEvent;

public interface MessageContextCommand {

    void onMessageContextInteraction(MessageContextInteractionEvent event);
}
