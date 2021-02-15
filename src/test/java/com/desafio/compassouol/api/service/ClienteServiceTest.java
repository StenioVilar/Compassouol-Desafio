package com.desafio.compassouol.api.service;

import com.desafio.compassouol.api.dao.ClienteRepository;
import com.desafio.compassouol.api.dto.ClienteDTO;
import com.desafio.compassouol.api.entity.Cidade;
import com.desafio.compassouol.api.entity.Cliente;
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

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ClienteServiceTest {

    @MockBean
    private ClienteRepository clienteRepository;
    private static final ObjectMapper om = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void salvarCliente_201() throws Exception {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String dataStr = "2021-02-12";
        Date dataTeste = new Date(format.parse(dataStr).getTime());
        Cidade cidadeTeste = new Cidade(1, "Nome Cidade", "DF");

        Cliente cliente = new Cliente(1, "Stenio", 'H', dataTeste, 25, cidadeTeste );
        ClienteDTO clienteDTO = new ClienteDTO("Stenio", 'H', "2021-02-12", 25, 1 );

        when(clienteRepository.save(any(Cliente.class))).thenReturn(cliente);

        mockMvc.perform(post("/cliente")
                .content(om.writeValueAsString(clienteDTO))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.idCliente", is(1)))
                .andExpect(jsonPath("$.nome", is("Stenio")))
                .andExpect(jsonPath("$.sexo", is("H")))
                .andExpect(jsonPath("$.dtNascimento", is("2021-02-12")))
                .andExpect(jsonPath("$.idade", is(25)))
                .andExpect(jsonPath("$.cidadeAtual.idCidade", is(cidadeTeste.getIdCidade())))
                .andExpect(jsonPath("$.cidadeAtual.nome", is(cidadeTeste.getNome())))
                .andExpect(jsonPath("$.cidadeAtual.siglaEstado", is(cidadeTeste.getSiglaEstado())));

        verify(clienteRepository, times(1)).save(any(Cliente.class));

    }

    @Test
    public void testarClientePorNome_404() throws Exception {
        mockMvc.perform(get("/cliente/nome/Rio")).andExpect(status().isNotFound());
    }

    @Test
    public void testarClientePorId_404() throws Exception {
        mockMvc.perform(get("/cliente/id/99")).andExpect(status().isNotFound());
    }

    @Test
    public void testarExcluirClientePorId_404() throws Exception {
        mockMvc.perform(delete("/cliente/99")).andExpect(status().isNotFound());
    }

}
