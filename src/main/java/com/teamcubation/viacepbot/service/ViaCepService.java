package com.teamcubation.viacepbot.service;

import com.teamcubation.viacepbot.model.ViaCepResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ViaCepService {

    private final WebClient webClient;

    @Autowired
    public ViaCepService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://viacep.com.br/ws").build();
    }

    public Mono<ViaCepResponse> searchCep(String cep) {
        return webClient.get()
                .uri("/{cep}/json", cep)
                .retrieve()
                .bodyToMono(ViaCepResponse.class)
                .flatMap(response -> {
                    if (response.getErro() != null && response.getErro().equals("true")) {
                        return Mono.error(new IllegalArgumentException("CEP não encontrado"));
                    }
                    return Mono.just(response);
                });
    }

    public Flux<ViaCepResponse> serarchEndereco(String state, String city, String street) {
        if (city.length() < 3 || street.length() < 3) {
            return Flux.error(new IllegalArgumentException("Cidade e logradouro devem ter pelo menos 3 caracteres."));
        }

        return webClient.get()
                .uri("/{uf}/{cidade}/{logradouro}/json/", state, city, street)
                .retrieve()
                .bodyToFlux(ViaCepResponse.class);
    }
}
