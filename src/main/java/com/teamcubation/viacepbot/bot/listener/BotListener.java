package com.teamcubation.viacepbot.bot.listener;

import com.teamcubation.viacepbot.bot.command.CepCommand;
import com.teamcubation.viacepbot.bot.command.FindCommand;
import com.teamcubation.viacepbot.bot.command.HelpCommand;
import net.dv8tion.jda.api.JDA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class BotListener {

    @Autowired
    private JDA jda;

    @Autowired
    private CepCommand cepCommand;

    @Autowired
    private FindCommand findCommand;

    @Autowired
    private HelpCommand helpCommand;

    @EventListener(ContextRefreshedEvent.class)
    public void loadDiscord() {
        jda.addEventListener(helpCommand);
        jda.addEventListener(findCommand);
        jda.addEventListener(cepCommand);
    }
}
