package org.arise.features.commands;

import org.arise.features.Feature;


import java.util.Collection;
import java.util.stream.Stream;

public interface CommandProvider {

    Collection<Feature> getFeatures();

    default Stream<BotCommand> getCommands(){
        return getFeatures().stream()
                .filter(BotCommand.class::isInstance)
                .map(BotCommand.class::cast);
    }
}
