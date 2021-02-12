package com.desafio.compassouol.api.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ClienteDTO {

    @ApiModelProperty(example = "Stenio Ramalho Vilar")
    private String nome;

    @ApiModelProperty(example = "H")
    private char sexo;

    @ApiModelProperty(example = "1995-12-06")
    private String dtNascimento;

    @ApiModelProperty(example = "25")
    private int idade;

    @ApiModelProperty(example = "1")
    private int idCidadeAtual;

}
