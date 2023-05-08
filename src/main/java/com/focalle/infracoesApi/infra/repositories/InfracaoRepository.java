package com.focalle.infracoesApi.infra.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.focalle.infracoesApi.domain.Infracao;

public interface InfracaoRepository extends JpaRepository<Infracao, Long>{

	Infracao getByPlaca(String placa);

	
	@Query("SELECT v FROM Infracao v WHERE (:placa IS NULL OR v.placa = :placa) AND (:velocidade IS NULL OR v.velocidade >= :velocidade) AND (:classeVeiculo IS NULL OR v.classeVeiculo = :classeVeiculo)")
    List<Infracao> filtro(String placa, Long velocidade, String classeVeiculo);


}
