package br.edu.ibmec.bigdata.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import br.edu.ibmec.bigdata.model.Endereco;
import br.edu.ibmec.bigdata.service.EnderecoService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(EnderecoController.class)
public class EnderecoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private EnderecoService enderecoService;

    private Endereco endereco;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        endereco = new Endereco();
        endereco.setRua("Rua Teste");
        endereco.setNumero("123");
        endereco.setBairro("Bairro Teste");
        endereco.setCidade("Cidade Teste");
        endereco.setEstado("SP");
        endereco.setCep("12345-678");
    }

    @Test
    public void adicionarEndereco_quandoEnderecoValido_entaoRetornarCreated() throws Exception {
        when(enderecoService.adicionarEndereco(endereco)).thenReturn(endereco);

        mockMvc.perform(post("/enderecos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(endereco)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.rua").value("Rua Teste"));
    }

    @Test
    public void adicionarEndereco_quandoRuaEmBranco_entaoRetornarBadRequest() throws Exception {
        endereco.setRua(""); // Simula rua em branco
        when(enderecoService.adicionarEndereco(endereco)).thenThrow(new IllegalArgumentException("Campo rua é obrigatório!"));

        mockMvc.perform(post("/enderecos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(endereco)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Há erros na sua requisição, verique"));
    }

}
