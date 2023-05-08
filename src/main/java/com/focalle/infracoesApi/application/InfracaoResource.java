package com.focalle.infracoesApi.application;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.focalle.infracoesApi.application.dtos.InfracaoSaveRequest;
import com.focalle.infracoesApi.application.services.InfracaoService;
import com.focalle.infracoesApi.domain.Infracao;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/infracao")
@RequiredArgsConstructor
public class InfracaoResource {
	
	private final InfracaoService service;
	
	@GetMapping
	public String status() {
		return "ok";
	}
	
	@PostMapping
	public ResponseEntity<Infracao> infracaoSave (@RequestBody InfracaoSaveRequest save) {
		service.save(save);
		URI headerLocation = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.query("placa={placa}")
				.buildAndExpand(save.getPlaca())
				.toUri();
		return ResponseEntity.created(headerLocation).build();
	}
	
	@GetMapping(params = "placa")
	public ResponseEntity<InfracaoSaveRequest> retornaInfracoesPlaca(@RequestParam("placa") String placa){
		var infracoes = service.getByPlaca(placa);
		if(infracoes == null) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(infracoes);
	}
	
	@GetMapping(value = "/retornarInfracoes")
	public ResponseEntity<List<InfracaoSaveRequest>> retornaTodasInfracoes(){
		var infracoes = service.findAll();
		if(infracoes.isEmpty() || infracoes.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(infracoes);
	}
	
	@GetMapping("/filtrar")
	public ResponseEntity<List<InfracaoSaveRequest>> retornaInfracoes(
			@RequestParam(required = false, name = "placa") String placa,
			@RequestParam(required = false, name = "velocidade") Long velocidade,
			@RequestParam(required = false, name = "classeVeiculo") String classeVeiculo){
		var infracoes = service.filtro(placa, velocidade, classeVeiculo);
		if(infracoes == null || infracoes.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(infracoes);
	}

}
