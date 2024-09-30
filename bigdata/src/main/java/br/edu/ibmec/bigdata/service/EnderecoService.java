package br.edu.ibmec.bigdata.service;

import org.springframework.stereotype.Service;

import br.edu.ibmec.bigdata.model.Endereco;
import br.edu.ibmec.bigdata.repository.EnderecoRepository;
import jakarta.validation.Valid;

@Service
public class EnderecoService {
    private final EnderecoRepository enderecoRepository;

    public EnderecoService(EnderecoRepository enderecoRepository){
        this.enderecoRepository = enderecoRepository;
    }
    // Método para adicionar endereço
    public Endereco adicionarEndereco(@Valid Endereco endereco){
        return enderecoRepository.save(endereco);
    }
    // Método para atualizar endereço
    public Endereco atualizarEndereco(@Valid Endereco endereco){
        return enderecoRepository.save(endereco);
    }
    // Método para remover endereço por ID
    public void removerEndereco(int id){
        enderecoRepository.deleteById(id);
    }
}
