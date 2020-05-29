
package com.eacj.sercapi.api.model;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


public class EnderecoInputModel {
    
    @NotBlank
    @Size(max = 200)
    private String titulo;
    
    @NotBlank
    @Size(max = 20)
    private String numero;
    
    @NotBlank
    @Size(max = 255)
    private String rua;
    
    @NotBlank
    @Size(max = 255)
    private String bairro;
    
    @NotBlank
    @Size(max = 8, min = 8)
    private String cep;
    
    @NotBlank
    @Size(max = 255)
    private String cidade;
    
    @NotBlank
    @Size(max = 255)
    private String estado;
    
    @Size(max = 2, min = 2)
    private String estadoUf;
    
    @Valid
    @NotNull
    private ClienteIdInputModel cliente;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getEstadoUf() {
        return estadoUf;
    }

    public void setEstadoUf(String estadoUf) {
        this.estadoUf = estadoUf;
    }

    public ClienteIdInputModel getCliente() {
        return cliente;
    }

    public void setCliente(ClienteIdInputModel cliente) {
        this.cliente = cliente;
    }
    
}
