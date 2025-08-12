package com.teamcubation.viacepbot.bot.command;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class HelpCommand extends ListenerAdapter {

    private static final Logger logger = LoggerFactory.getLogger(HelpCommand.class);


    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        String message = event.getMessage().getContentRaw();
        String author = event.getAuthor().getName();
        String channel = event.getChannel().getName();

        if (message.startsWith("!help")) {
            logger.info("\nMensagem recebida: {}\nUsuário: {}\nCanal {}", message, author, channel);

            String helpMessage = ":sos:  Lista de Comandos:\n\n" +
                    "- :1234:  **!cep <** CEP** >** : Busca informações de um CEP.\n" +
                    "- :mag_right:  **!find <** state, city, street **>** : Busca o CEP de um endereço.\n";

            event.getChannel().sendMessage(helpMessage).queue();
        }
    }
}