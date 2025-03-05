package org.arise.features.commands.weather;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.InteractionHook;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.SubcommandData;
import net.dv8tion.jda.api.utils.messages.MessageCreateData;
import org.arise.features.commands.AbstractSlashCommand;
import org.arise.features.commands.CommandContext;

import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.Optional;

public class WeatherCommand extends AbstractSlashCommand {

    private final WeatherService weatherService;

    public WeatherCommand(WeatherService weatherService){
        super("weather","get weather information", CommandContext.GLOBAL);

        //todo: add weather alerts and forecast weather sub commands
        SubcommandData currentWeatherData = new SubcommandData("current","get current weather");
        currentWeatherData.addOption(OptionType.STRING,"city","city to get weather information for",true);

        getData()
                .addSubcommands(currentWeatherData);

        this.weatherService = Objects.requireNonNull(weatherService);
    }

    @Override
    public void onSlashCommand(SlashCommandInteractionEvent event) {

        event.deferReply().queue();
        InteractionHook hook = event.getHook();

        String city = event.getOption("city").getAsString();

        String subCommandName = event.getSubcommandName();
        if("current".equals(subCommandName)){
           handleCurrentWeatherEvent(city,hook);
        }

    }

    private void handleCurrentWeatherEvent(String  city, InteractionHook hook){

        Optional<WeatherData> currentWeather = weatherService.getCurrentWeatherData(city);

        if(currentWeather.isEmpty()){
            hook.sendMessage("could not get weather information").queue();
            return;
        }

        WeatherData weatherData = currentWeather.get();

        EmbedBuilder messageEmbed = new EmbedBuilder();

        messageEmbed.setTitle("Weather Report");
        messageEmbed.setDescription("Weather Information for " + weatherData.getCity());
        messageEmbed.addField("Country: ",weatherData.getCountry(),true);
        messageEmbed.addField("City: ",weatherData.getCity(),true);
        messageEmbed.addField("Date and Time:",weatherData.getCurrentDateTime().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")),false);
        messageEmbed.addField("Day:",weatherData.getDayOfWeek().name(),false);
        messageEmbed.addField("Condition:",weatherData.getDescription(),false);
        messageEmbed.addField("Temperature:",weatherData.getTemperature() + "°C",true);
        messageEmbed.addField("Humidity:",weatherData.getHumidity() + "%",true);
        messageEmbed.addField("Precipitation:",weatherData.getPrecipitation() + "mm",false);
        messageEmbed.addField("Wind speed:",weatherData.getWindSpeed() + "kph",true);
        messageEmbed.addField("Wind direction",weatherData.getWindDirection(),true);
        messageEmbed.setFooter("Weather Powered by weatherapi.com");
        messageEmbed.setThumbnail(weatherData.getIconUrl());

        MessageCreateData messageData = MessageCreateData.fromEmbeds(messageEmbed.build());
        hook.sendMessage(messageData).queue();
    }
}
