package org.arise.features;

import org.arise.features.commands.basic.PingCommand;

import java.util.ArrayList;
import java.util.Collection;


public class Features {

    public Features() {
        throw new UnsupportedOperationException("construction not supported");
    }

    public static Collection<Feature> load(){

        Collection<Feature> features = new ArrayList<>();

        //slash commands
        features.add(new PingCommand());

        return features;
    }
}


