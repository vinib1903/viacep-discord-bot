package com.teamcubation.viacepbot.bot.command;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.utils.FileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.File;

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

            String helpMessage = """
                    :sos: LISTA DE COMANDOS :sos:
                    
                    - `!cep <CEP>`: Busca informações de um CEP :1234:
                    - `!find <estado>, <cidade>, <rua>`: Busca o CEP de um endereço :mag_right:
                    
                    """;

            File gifFile = new File("src/main/resources/images/help.gif");

            if (gifFile.exists()) {
                event.getChannel().sendMessage(helpMessage)
                        .addFiles(FileUpload.fromData(gifFile))
                        .queue();
            } else {
                event.getChannel().sendMessage(helpMessage + "\n(GIF não encontrado!)").queue();
            }
        }
    }
}