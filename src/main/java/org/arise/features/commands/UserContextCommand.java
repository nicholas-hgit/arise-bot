package org.arise.features.commands;

import net.dv8tion.jda.api.events.interaction.command.UserContextInteractionEvent;

public interface UserContextCommand {

    void onUserContextInteraction(UserContextInteractionEvent event);
}
