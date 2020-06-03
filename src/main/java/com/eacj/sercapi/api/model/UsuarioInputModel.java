
package com.eacj.sercapi.api.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


public class UsuarioInputModel {
    
    @NotBlank
    @Size(max = 20)
    private String uid;
    
    @NotBlank
    @Size(max = 200)
    private String nome;
    
    @NotBlank
    @Size(max = 100)
    private String username;
    
    @NotBlank
    @Size(max = 255)
    private String password;
    

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
}
