package com.desafio.compassouol.api.controller;

import com.desafio.compassouol.api.dto.CidadeDTO;
import com.desafio.compassouol.api.entity.Cidade;
import com.desafio.compassouol.api.service.CidadeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(value = "Cidade")
@RequestMapping(value = "/cidade")
public class CidadeController {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CidadeService service;

    @ApiOperation(value = "Cadastrar uma cidade",notes = "Endpoint responsável por cadastrar uma cidade")
    @PostMapping
    public ResponseEntity<?> salvarCidade(@RequestBody CidadeDTO cidadeDTO) {
        Cidade cidade = convertToEntity(cidadeDTO);
        return new ResponseEntity<>(service.salvarCidade(cidade), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Obtem uma cidade pelo nome",notes = "Endpoint responsável por buscar uma cidade por parte do nome")
    @GetMapping(value = "/nome/{nome}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> obterCidadePorNome(@PathVariable(value="nome")
                                                    @ApiParam(value = "Nome da cidade") String nome) {
        return new ResponseEntity<>(service.obterCidadePorNome(nome), HttpStatus.OK);
    }

    @ApiOperation(value = "Obtem uma cidade pelo estado",notes = "Endpoint responsável por buscar uma cidade pelo estado")
    @GetMapping(value = "/estado/{sgEstado}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> obterCidadePorEstado(@PathVariable(value="sgEstado")
                                                @ApiParam(value = "Sigla do Estado") String sgEstado) {
        return new ResponseEntity<>(service.obterCidadePorEstado(sgEstado), HttpStatus.OK);
    }

    private Cidade convertToEntity(CidadeDTO cidadeDTO) {
        return modelMapper.map(cidadeDTO, Cidade.class);
    }

}
