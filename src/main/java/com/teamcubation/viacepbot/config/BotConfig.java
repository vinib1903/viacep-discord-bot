package com.teamcubation.viacepbot.config;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.EnumSet;


@Configuration
public class BotConfig {

    @Value("${discord.token}")
    private String discordToken;

    @Bean
    public JDA jda() throws InterruptedException {
        JDABuilder builder = JDABuilder.createDefault(discordToken);

        builder.enableIntents(EnumSet.allOf(GatewayIntent.class));


        JDA jda = builder.build();
        jda.awaitReady();
        return jda;
    }
}
