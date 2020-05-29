
package com.eacj.sercapi.api.model;

import java.time.OffsetDateTime;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


public class ClienteInputModel {
    
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

    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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
    
}
