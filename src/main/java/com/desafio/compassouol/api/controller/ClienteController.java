package com.desafio.compassouol.api.controller;

import com.desafio.compassouol.api.dto.CidadeDTO;
import com.desafio.compassouol.api.dto.ClienteDTO;
import com.desafio.compassouol.api.dto.ClienteMudancaNomeDTO;
import com.desafio.compassouol.api.entity.Cidade;
import com.desafio.compassouol.api.entity.Cliente;
import com.desafio.compassouol.api.service.CidadeService;
import com.desafio.compassouol.api.service.ClienteService;
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
@Api(value = "Cliente")
@RequestMapping(value = "/cliente")
public class ClienteController {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ClienteService service;


    @ApiOperation(value = "Cadastrar um cliente",notes = "Endpoint responsável por cadastrar um cliente")
    @PostMapping
    public ResponseEntity<?> salvarCLiente(@RequestBody ClienteDTO clienteDTO) {
        Cliente cliente = convertToEntity(clienteDTO);
        return new ResponseEntity<>(service.salvarCliente(cliente), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Obtem um cliente pelo nome",notes = "Endpoint responsável buscar um cliente por uma parte do nome")
    @GetMapping(value = "/nome/{nome}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> obterClientePorNome(@PathVariable(value="nome")
                                                @ApiParam(value = "Nome do Cliente") String nome) {
        return new ResponseEntity<>(service.obterClientePorNome(nome), HttpStatus.OK);
    }

    @ApiOperation(value = "Obtem um cliente pelo id",notes = "Endpoint responsável por buscar um cliente único pelo ID")
    @GetMapping(value = "/id/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Cliente> obterClientePorId(@PathVariable(value="id")
                                                 @ApiParam(value = "Id do Cliente") Integer id) {
        return new ResponseEntity<Cliente>(service.obterClientePorId(id), HttpStatus.OK);
    }

    @ApiOperation(value = "Excluir um cliente pelo id",notes = "Endpoint responsável por excluir um cliente pelo ID")
    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> excluirClientePorId(@PathVariable(value="id")
                                               @ApiParam(value = "Id do Cliente") Integer id) {
        service.excluirClientePorId(id);
        return ResponseEntity.noContent().build();
    }

    @ApiOperation(value = "Alterar o nome de um cliente pelo id",notes = "Endpoint responsável por alterar o nome de um cliente pelo id")
    @PatchMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> alterarNomeClientePorId(@PathVariable(value="id")
                                                         @ApiParam(value = "Id do Cliente") Integer id,
                                                         @RequestBody ClienteMudancaNomeDTO clienteDTO) {
        return new ResponseEntity<>(service.alterarNomeClientePorId(id,clienteDTO.getNome()), HttpStatus.OK);
    }

    private Cliente convertToEntity(ClienteDTO clienteDTO) {
        return modelMapper.map(clienteDTO, Cliente.class);
    }

}
