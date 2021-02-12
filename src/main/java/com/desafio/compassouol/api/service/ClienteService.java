package com.desafio.compassouol.api.service;

import com.desafio.compassouol.api.dao.ClienteRepository;
import com.desafio.compassouol.api.entity.Cliente;
import com.desafio.compassouol.api.error.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repository;

    private static final String MSG_CLIENTE_NAO_ENCONTRADO_NOME = "Nenhum cliente encontrado para o nome: ";
    private static final String MSG_CLIENTE_NAO_ENCONTRADO_ID = "Nenhum cliente encontrado para o id: ";

    public Cliente salvarCliente(Cliente cliente) {
        return repository.save(cliente);
    }


    public Object obterClientePorNome(String nome) {
        List<Cliente> clientes = repository.findByNomeContaining(nome);
        if(clientes.isEmpty()){
            throw new ResourceNotFoundException(MSG_CLIENTE_NAO_ENCONTRADO_NOME + nome);
        }
        return clientes;
    }

    public Object obterClientePorId(Integer id) {
        Optional<Cliente> cliente = repository.findById(id);
        if(!cliente.isPresent()){
            throw new ResourceNotFoundException(MSG_CLIENTE_NAO_ENCONTRADO_ID + id);
        }
        return cliente;
    }


    public void excluirClientePorId(Integer id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException ex) {
            throw new ResourceNotFoundException(MSG_CLIENTE_NAO_ENCONTRADO_ID + id);
        }
    }

    public Cliente alterarNomeClientePorId(Integer id, String nome) {
        Optional<Cliente> cliente = repository.findById(id);;
        cliente.get().setNome(nome);
        return repository.save(cliente.get());
    }
}
