package br.edu.ibmec.bigdata.service;

import br.edu.ibmec.bigdata.model.Cliente;
import br.edu.ibmec.bigdata.repository.ClienteRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import java.util.List;

import jakarta.validation.Valid;

@Service
@Validated
public class ClienteService {
    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository){
        this.clienteRepository = clienteRepository;
    }

    public Cliente adicionarCliente(@Valid Cliente cliente){
        if(!cliente.isMaiorDeIdade()){
            throw new IllegalArgumentException("O cliente deve ter pelo menos 18 anos");
        }
        if(clienteRepository.existsByEmail(cliente.getEmail())){
            throw new IllegalArgumentException("Email já cadastrado!");
        }
        if(clienteRepository.existsByCpf(cliente.getCpf())){
            throw new IllegalArgumentException("CPF já cadastrado!");
        }
            return clienteRepository.save(cliente);
        }

        public List<Cliente> getAllClientes(){
            return clienteRepository.findAll();
        }
    }

