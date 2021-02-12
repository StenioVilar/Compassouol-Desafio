package com.desafio.compassouol.api.dao;

import com.desafio.compassouol.api.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

    Optional<List<Cliente>> findByNomeContaining(String nome);

}
