package org.arise.features.commands.basic;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import org.arise.features.commands.AbstractSlashCommand;
import org.arise.features.commands.CommandContext;

public class PingCommand extends AbstractSlashCommand {

    public PingCommand(){
        super("ping","ping a user", CommandContext.GUILD);

         getData()
                 .addOption(OptionType.USER,"user","user to ping",true);
    }

    @SuppressWarnings("DataFlowIssue")
    @Override
    public void onSlashCommand(SlashCommandInteractionEvent event) {

        event.reply("Pinged %s".formatted(event.getOption("user").getAsMember().getAsMention()))
                .queue();
    }
}
