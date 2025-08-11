package com.teamcubation.viacepbot.bot.command;

import com.teamcubation.viacepbot.service.ViaCepService;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class FindCommand extends ListenerAdapter {

    @Autowired
    private ViaCepService viaCepService;

    private static final Logger logger = LoggerFactory.getLogger(FindCommand.class);


    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        String message = event.getMessage().getContentRaw();
        String author = event.getAuthor().getName();
        String channel = event.getChannel().getName();

        if (message.startsWith("!find")) {
            logger.info("\nMensagem recebida: {}\nUsuário: {}\nCanal {}", message, author, channel);

            String[] parts = message.substring(5).trim().split(",");
            if (parts.length == 3) {
                String state = parts[0].trim();
                String city = parts[1].trim();
                String street = parts[2].trim();

                viaCepService.serarchEndereco(state, city, street)
                        .collectList()
                        .subscribe(
                                addresses -> {
                                    if (addresses.isEmpty()) {
                                        event.getChannel().sendMessage("Nenhum CEP encontrado para esse endereço.").queue();
                                    } else {
                                        String response = addresses.stream()
                                                .map(adress -> String.format("**:1234:  CEP**: %s\n**:motorway:  Logradouro**: %s\n**:homes:  Bairro**: %s\n**:cityscape:  Cidade**: %s - %s",
                                                        adress.getCep(), adress.getLogradouro(), adress.getBairro(),
                                                        adress.getLocalidade(), adress.getUf()))
                                                .limit(10)
                                                .collect(Collectors.joining("\n"));

                                        if (addresses.size() > 5) {
                                            response += "\n(Exibindo apenas os 10 primeiros resultados)";
                                        }

                                        event.getChannel().sendMessage(response).queue();
                                    }
                                },
                                error -> {
                                    event.getChannel().sendMessage("Erro na busca: " + error.getMessage()).queue();
                                }
                        );
            } else {
                event.getChannel().sendMessage("Uso correto: !find <sigla-estado>, <cidade>, <rua>/n/nExemplo: !find rs, novo hamburgo, rua sarandi").queue();
            }
        }
    }
}
