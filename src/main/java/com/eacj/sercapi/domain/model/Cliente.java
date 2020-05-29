package com.eacj.sercapi.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
public class Cliente {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank
    @Size(max = 200)
    private String nome;
    
    @NotBlank
    @Size(max = 11, min = 11)
    private String cpf;
    
    @NotBlank
    @Size(max = 11)
    private String whatsapp;
    
    @NotBlank
    @Email
    @Size(max = 255)
    private String email;
    
    private String numeroCalcado;
    private String numeroJeans;
    private OffsetDateTime nascimento;
    
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private OffsetDateTime dataRegistro;
    
    @OneToMany(mappedBy = "cliente")
    private List<Endereco> enderecos = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getWhatsapp() {
        return whatsapp;
    }

    public void setWhatsapp(String whatsapp) {
        this.whatsapp = whatsapp;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumeroCalcado() {
        return numeroCalcado;
    }

    public void setNumeroCalcado(String numeroCalcado) {
        this.numeroCalcado = numeroCalcado;
    }

    public String getNumeroJeans() {
        return numeroJeans;
    }

    public void setNumeroJeans(String numeroJeans) {
        this.numeroJeans = numeroJeans;
    }

    public OffsetDateTime getNascimento() {
        return nascimento;
    }

    public void setNascimento(OffsetDateTime nascimento) {
        this.nascimento = nascimento;
    }

    public OffsetDateTime getDataRegistro() {
        return dataRegistro;
    }

    public void setDataRegistro(OffsetDateTime dataRegistro) {
        this.dataRegistro = dataRegistro;
    }

    public List<Endereco> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(List<Endereco> enderecos) {
        this.enderecos = enderecos;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Cliente other = (Cliente) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
}
