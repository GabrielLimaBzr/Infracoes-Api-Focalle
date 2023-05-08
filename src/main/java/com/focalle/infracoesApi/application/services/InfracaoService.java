package com.focalle.infracoesApi.application.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.focalle.infracoesApi.application.dtos.InfracaoSaveRequest;
import com.focalle.infracoesApi.domain.Infracao;
import com.focalle.infracoesApi.infra.repositories.InfracaoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InfracaoService {

	private final InfracaoRepository repository;
	
	private final ModelMapper model;
	
	
	@Transactional
	public InfracaoSaveRequest save(InfracaoSaveRequest infracao) {
		var obj = repository.save(model.map(infracao, Infracao.class));
		return model.map(obj, InfracaoSaveRequest.class);
	}


	public InfracaoSaveRequest getByPlaca(String placa) {
		var obj = repository.getByPlaca(placa);
		return model.map(obj, InfracaoSaveRequest.class);
	}
	
	public List<InfracaoSaveRequest> findAll() {
		var obj = repository.findAll();
		return obj.stream().map(x -> model.map(x, InfracaoSaveRequest.class)).collect(Collectors.toList());
	}


	public List<InfracaoSaveRequest>  filtro(String placa, Long velocidade, String classeVeiculo) {
		var obj = repository.filtro(placa, velocidade, classeVeiculo);
		return obj.stream().map(x -> model.map(x, InfracaoSaveRequest.class)).collect(Collectors.toList());
	}
}
