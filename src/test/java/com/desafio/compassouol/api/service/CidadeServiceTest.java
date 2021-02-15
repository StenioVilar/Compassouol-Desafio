package com.desafio.compassouol.api.service;

import com.desafio.compassouol.api.dao.CidadeRepository;
import com.desafio.compassouol.api.dto.CidadeDTO;
import com.desafio.compassouol.api.entity.Cidade;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CidadeServiceTest {

    @MockBean
    private CidadeRepository cidadeRepository;
    private static final ObjectMapper om = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testarBuscarCidadesPorNome_200() throws Exception {

        List<Cidade> cidades = Arrays.asList(
                new Cidade(1, "Nome Cidade", "DF"),
                new Cidade(2, "Nome Cidade", "RJ"));

        when(cidadeRepository.findByNomeContaining("Nome Cidade")).thenReturn(cidades);

        mockMvc.perform(get("/cidade/nome/Nome Cidade"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].idCidade", is(1)))
                .andExpect(jsonPath("$[0].nome", is("Nome Cidade")))
                .andExpect(jsonPath("$[0].siglaEstado", is("DF")))
                .andExpect(jsonPath("$[1].idCidade", is(2)))
                .andExpect(jsonPath("$[1].nome", is("Nome Cidade")))
                .andExpect(jsonPath("$[1].siglaEstado", is("RJ")));

        verify(cidadeRepository, times(1)).findByNomeContaining("Nome Cidade");
    }

    @Test
    public void testarCidadePorNome_404() throws Exception {
        mockMvc.perform(get("/cidade/nome?nome=Rio")).andExpect(status().isNotFound());
    }

    @Test
    public void testarCidadePorEstado_404() throws Exception {
        mockMvc.perform(get("/cidade/estado?siglaEstado=AM")).andExpect(status().isNotFound());
    }

    @Test
    public void salvarCidade_201() throws Exception {

        Cidade cidade = new Cidade(1, "Nome Cidade", "DF");
        CidadeDTO cidadeDTO = new CidadeDTO("Nome Cidade", "DF");
        when(cidadeRepository.save(any(Cidade.class))).thenReturn(cidade);

        mockMvc.perform(post("/cidade")
                .content(om.writeValueAsString(cidadeDTO))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.idCidade", is(1)))
                .andExpect(jsonPath("$.nome", is("Nome Cidade")))
                .andExpect(jsonPath("$.siglaEstado", is("DF")));
        verify(cidadeRepository, times(1)).save(any(Cidade.class));

    }

}
