package com.desafio.compassouol.api.dao;

import com.desafio.compassouol.api.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

    List<Cliente> findByNomeContaining(String nome);

}
