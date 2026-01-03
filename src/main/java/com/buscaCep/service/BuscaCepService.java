package com.buscaCep.service;

import java.time.Duration;

import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.buscaCep.DTO.BuscaCepResponse;
import com.buscaCep.entity.BuscaCep;

import reactor.core.publisher.Mono;

@Service
public class BuscaCepService {
	private final WebClient webClient;
	
	public BuscaCepService(WebClient.Builder builder) {
        this.webClient = builder
            .baseUrl("https://brasilapi.com.br")
            .build();
	}
	
	public Mono <BuscaCepResponse> getCep(String cep) {
		return webClient.get()
				.uri("/api/cep/v2/{cep}", cep)
				.retrieve()
				 .onStatus(HttpStatusCode::is4xxClientError,
			                response -> Mono.error(new RuntimeException("CEP não encontrado")))
			            .onStatus(HttpStatusCode::is5xxServerError,
			                response -> Mono.error(new RuntimeException("Erro na API externa")))
				  .bodyToMono(BuscaCep.class)
				  .timeout(Duration.ofSeconds(5)) // controla o timeout
			        .map(buscaCep -> new BuscaCepResponse(
			            buscaCep.getCep(),
			            buscaCep.getState(),
			            buscaCep.getCity(),
			            buscaCep.getNeighborhood(),
			            buscaCep.getStreet()
			        ));

			    }
			}
