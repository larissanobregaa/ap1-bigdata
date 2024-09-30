package br.edu.ibmec.bigdata.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
public class Endereco {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    @NotBlank(message = "Campo rua é obrigatório!")
    @Size(min = 3, max = 255, message = "A rua deve ter entre 3 e 255 caracteres")
    private String rua;

    @Column
    @NotBlank(message = "Campo número é obrigatório!")
    private String numero;

    @Column
    @NotBlank(message = "Campo bairro é obrigatório!")
    @Size(min = 3, max = 100, message = "O bairro deve ter entre 3 e 100 caracteres")
    private String bairro;

    @Column
    @NotBlank(message = "Campo cidade é obrigatório!")
    @Size(min = 2, max = 100, message = "A cidade deve ter entre 2 e 100 caracteres")
    private String cidade;

    @Column
    @NotBlank(message = "Campo estado é obrigatório!")
    @Pattern(regexp = "^(AC|AL|AP|AM|BA|CE|DF|ES|GO|MA|MT|MS|MG|PA|PB|PR|PE|PI|RJ|RN|RS|RO|RR|SC|SP|SE|TO)$", 
    message = "Estado deve ser um valor válido (ex.: SP, RJ)")
    private String estado;

    @Column
    @NotBlank(message = "Campo CEP é obrigatório!")
    @Pattern(regexp = "\\d{5}-\\d{3}", message = "CEP deve seguir o formato XXXXX-XXX")
    private String cep;

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

}
