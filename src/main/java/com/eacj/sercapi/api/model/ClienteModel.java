
package com.eacj.sercapi.api.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.time.OffsetDateTime;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ClienteModel {
    
    private Long id;

    private String nome;
    private String cpf;
    private String whatsapp;
    private String email;
    private String numeroCalcado;
    private String numeroJeans;
    private OffsetDateTime nascimento;
    
    private OffsetDateTime dataRegistro;
    
    private List<EnderecoModel> enderecos;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public OffsetDateTime getDataRegistro() {
        return dataRegistro;
    }

    public void setDataRegistro(OffsetDateTime dataRegistro) {
        this.dataRegistro = dataRegistro;
    }

    public List<EnderecoModel> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(List<EnderecoModel> enderecos) {
        this.enderecos = enderecos;
    }

}
