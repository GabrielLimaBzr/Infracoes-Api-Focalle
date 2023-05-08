package com.focalle.infracoesApi.application.dtos;


import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class InfracaoSaveRequest {

	private String placa;
	private Long velocidade;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date dtRegistro;
	private String classeVeiculo;
}
