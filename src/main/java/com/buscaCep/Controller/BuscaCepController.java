package com.buscaCep.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.buscaCep.DTO.BuscaCepResponse;
import com.buscaCep.service.BuscaCepService;

import reactor.core.publisher.Mono;


@RestController
@RequestMapping("/api/cep")
public class BuscaCepController {
	private final BuscaCepService buscaCepService;
	
	public BuscaCepController (BuscaCepService buscaCepService) {
		this.buscaCepService = buscaCepService;
	}
	
	@GetMapping("/{cep}")
	public Mono <BuscaCepResponse> get(@PathVariable String cep) {
		return buscaCepService.getCep(cep);
	}
}
