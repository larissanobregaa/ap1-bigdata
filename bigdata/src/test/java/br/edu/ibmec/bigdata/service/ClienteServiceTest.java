package br.edu.ibmec.bigdata.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.edu.ibmec.bigdata.model.Cliente;
import br.edu.ibmec.bigdata.repository.ClienteRepository;

public class ClienteServiceTest {
    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private ClienteService clienteService;

    private Cliente cliente;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        cliente = new Cliente();
        cliente.setNome("Teste Cliente");
        cliente.setEmail("teste@cliente.com");
        cliente.setCpf("123.456.789-00");
        cliente.setDataNascimento(LocalDate.of(2000, 1, 1));
    }

    @Test
    public void adicionarCliente_quandoClienteValido_entaoSalvar() {
        when(clienteRepository.existsByEmail(cliente.getEmail())).thenReturn(false);
        when(clienteRepository.existsByCpf(cliente.getCpf())).thenReturn(false);

        Cliente result = clienteService.adicionarCliente(cliente);

        assertNotNull(result);
        verify(clienteRepository, times(1)).save(cliente);
    }

    @Test
    public void adicionarCliente_quandoEmailDuplicado_entaoLancarExcecao() {
        when(clienteRepository.existsByEmail(cliente.getEmail())).thenReturn(true);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            clienteService.adicionarCliente(cliente);
        });

        assertEquals("Email já cadastrado!", exception.getMessage());
    }


    @Test
    public void adicionarCliente_quandoDataNascimentoNula_entaoLancarExcecao() {
        cliente.setDataNascimento(null); // Simula cliente sem data de nascimento
    
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            clienteService.adicionarCliente(cliente);
        });
    
        assertEquals("Data de nascimento não pode ser nula", exception.getMessage());
    }
}
