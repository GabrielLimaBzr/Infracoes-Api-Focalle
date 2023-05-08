package com.focalle.infracoesApi.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table(schema = "public", name = "records")
public class Infracao {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_registro")
	private Long id;
	
	@Column(name = "placa")
	private String placa;
	
	@Column(name = "velocidade")
	private Long velocidade;
	
	@Column(name = "dt_registro")
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	private Date dtRegistro;
	
	@Column(name = "classe_veiculo")
	private String classeVeiculo;

}
