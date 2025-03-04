package org.arise.features.commands;

import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData;

public abstract class AbstractSlashCommand extends AbstractCommand implements SlashCommand{

    private final String description;
    private final SlashCommandData data;

    public AbstractSlashCommand(String name, String description, CommandContext context) {
        super(Commands.slash(name,description),context);

        this.description = description;
        this.data = (SlashCommandData) super.getData();

    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public SlashCommandData getData() {
        return data;
    }
}
