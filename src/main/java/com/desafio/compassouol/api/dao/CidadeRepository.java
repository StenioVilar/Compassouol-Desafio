package com.desafio.compassouol.api.dao;

import com.desafio.compassouol.api.entity.Cidade;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CidadeRepository extends JpaRepository<Cidade, Integer> {

	List<Cidade> findByNomeContaining(String nome);

	List<Cidade> findBySiglaEstado(String sgEstado);

}
