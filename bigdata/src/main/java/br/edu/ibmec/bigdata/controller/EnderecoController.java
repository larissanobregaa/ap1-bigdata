package br.edu.ibmec.bigdata.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ibmec.bigdata.model.Endereco;
import br.edu.ibmec.bigdata.service.EnderecoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/enderecos")
public class EnderecoController {
    private final EnderecoService enderecoService;
    
    public EnderecoController(EnderecoService enderecoService) {
        this.enderecoService = enderecoService;
    }

    @PostMapping
    public ResponseEntity<Endereco> adicionarEndereco(@Valid @RequestBody Endereco endereco) {
        Endereco novoEndereco = enderecoService.adicionarEndereco(endereco);
        return ResponseEntity.ok(novoEndereco);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Endereco> atualizarEndereco(@PathVariable int id, @Valid @RequestBody Endereco endereco){
        endereco.setId(id);
        Endereco enderecoAtualizado = enderecoService.atualizarEndereco(endereco);
        return ResponseEntity.ok(enderecoAtualizado);
    }

    @DeleteMapping("/id")
    public ResponseEntity<Void> removerEndereco(@PathVariable int id){
        enderecoService.removerEndereco(id);
        return ResponseEntity.noContent().build();
    }
}
