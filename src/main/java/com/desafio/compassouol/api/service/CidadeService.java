package com.desafio.compassouol.api.service;

import com.desafio.compassouol.api.dao.CidadeRepository;
import com.desafio.compassouol.api.entity.Cidade;
import com.desafio.compassouol.api.error.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CidadeService {

    @Autowired
    private CidadeRepository repository;

    private static final String MSG_CIDADE_NAO_ENCONTRADA_NOME = "Nenhuma cidade encontrada para o nome: ";
    private static final String MSG_CIDADE_NAO_ENCONTRADA_ESTADO = "Nenhum cidade encontrada para o estado: ";

    public Cidade salvarCidade(Cidade cidade) {
        return repository.save(cidade);
    }

    public List<Cidade> obterCidadePorNome(String nome) {
        List<Cidade> cidades = repository.findByNomeContaining(nome);
        if(cidades.isEmpty()){
            throw new ResourceNotFoundException(MSG_CIDADE_NAO_ENCONTRADA_NOME + nome);
        }
        return cidades;
    }

    public Object obterCidadePorEstado(String sgEstado) {
        List<Cidade> cidades = repository.findBySiglaEstado(sgEstado);
        if(cidades.isEmpty()){
            throw new ResourceNotFoundException(MSG_CIDADE_NAO_ENCONTRADA_ESTADO + sgEstado);
        }
        return cidades;
    }

}
