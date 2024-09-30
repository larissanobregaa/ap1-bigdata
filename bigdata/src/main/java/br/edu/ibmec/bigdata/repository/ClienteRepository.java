package br.edu.ibmec.bigdata.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.ibmec.bigdata.model.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer>{
    boolean existsByEmail(String email);
    boolean existsByCpf(String cpf);
    Cliente findByCpf(String cpf);
}
