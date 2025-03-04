package org.arise.features.commands;

import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import org.arise.features.Feature;

public interface BotCommand extends Feature {

    String getName();

    CommandType getType();

    CommandContext getContext();

    CommandData getData();
}
