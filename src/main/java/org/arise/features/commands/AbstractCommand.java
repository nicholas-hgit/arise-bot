package org.arise.features.commands;

import net.dv8tion.jda.api.interactions.commands.Command;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;

import static org.arise.features.commands.CommandType.*;

public class AbstractCommand implements BotCommand {

    private final String name;
    private final CommandType type;
    private final CommandContext context;
    private final CommandData data;

    public AbstractCommand(CommandData data,CommandContext context){
        this.data = data;
        this.context = context;

        this.name = data.getName();
        this.type = setCommandType(data.getType());
    }

    private CommandType setCommandType(Command.Type type){

       return switch (type){

           case SLASH -> SLASH_COMMAND;
           case MESSAGE -> MESSAGE_CONTEXT_COMMAND;
           case USER -> USER_CONTEXT_COMMAND;
           case UNKNOWN -> throw new UnsupportedOperationException("command type: %s is not supported".formatted(type));

        };
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public CommandType getType() {
        return type;
    }

    @Override
    public CommandContext getContext() {
        return context;
    }

    @Override
    public CommandData getData() {
        return data;
    }
}
