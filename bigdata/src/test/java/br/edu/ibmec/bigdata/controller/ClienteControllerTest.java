package br.edu.ibmec.bigdata.controller;

import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


import br.edu.ibmec.bigdata.model.Cliente;
import br.edu.ibmec.bigdata.service.ClienteService;

@WebMvcTest(ClienteController.class)
public class ClienteControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Mock
    private ClienteService clienteService;

    @InjectMocks
    private ClienteController clienteController;

    private Cliente cliente;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        cliente = new Cliente();
        cliente.setNome("Teste Cliente");
        cliente.setEmail("teste@cliente.com");
        cliente.setCpf("123.456.789-00");
    }

    @Test
    public void getAllClientes_quandoNaoExistemClientes_entaoRetornarNoContent() throws Exception {
        when(clienteService.getAllClientes()).thenReturn(Collections.emptyList());

        mockMvc.perform(MockMvcRequestBuilders.get("/cliente"))
                .andExpect(status().isNoContent());
    }

    @Test
    public void getAllClientes_quandoExistemClientes_entaoRetornarOk() throws Exception {
        when(clienteService.getAllClientes()).thenReturn(List.of(cliente));

        mockMvc.perform(MockMvcRequestBuilders.get("/cliente"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nome").value("Teste Cliente"));
    }
}
