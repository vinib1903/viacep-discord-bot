package com.teamcubation.viacepbot.bot.command;

import com.teamcubation.viacepbot.service.ViaCepService;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CepCommand extends ListenerAdapter {

    @Autowired
    private ViaCepService viaCepService;

    private static final Logger logger = LoggerFactory.getLogger(CepCommand.class);


    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        String message = event.getMessage().getContentRaw();
        String author = event.getAuthor().getName();
        String channel = event.getChannel().getName();

        if (message.startsWith("!cep")) {
            logger.info("\nMensagem recebida: {}\nUsuário: {}\nCanal {}", message, author, channel);

            String cep = message.substring(5).trim();
            if (cep.matches("\\d{8}")) {
                viaCepService.searchCep(cep)
                        .subscribe(
                                cepResponse -> {
                                    String response = String.format(
                                            "**:motorway:  Logradouro**: %s\n**:homes:  Bairro**: %s\n**:cityscape: Cidade**: %s - %s",
                                            cepResponse.getLogradouro(),
                                            cepResponse.getBairro(),
                                            cepResponse.getLocalidade(),
                                            cepResponse.getUf()
                                    );
                                    event.getChannel().sendMessage(response).queue();
                                },
                                error -> {
                                    if (error instanceof IllegalArgumentException) {
                                        event.getChannel().sendMessage(error.getMessage()).queue();
                                    } else {
                                        event.getChannel().sendMessage("Ocorreu um erro ao buscar o CEP.").queue();
                                    }
                                }
                        );
            } else {
                event.getChannel().sendMessage("Formato de CEP inválido. Use apenas números (8 dígitos).").queue();
            }
        }
    }
}