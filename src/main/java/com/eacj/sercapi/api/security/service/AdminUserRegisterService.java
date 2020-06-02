
package com.eacj.sercapi.api.security.service;

import com.eacj.sercapi.domain.model.Usuario;
import com.eacj.sercapi.domain.repository.UsuarioRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class AdminUserRegisterService {
    
    private final static String ADMIN_USERNAME = "eacj_admin";
    private final static String ADMIN_PASSWORD = "hUnr8nnx7W7J6GOg";
    
    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    public void start(){
        
        List<Usuario> adm = usuarioRepository.findByRoles("ADMIN");
        
        if(adm.isEmpty())
            createAdminRegister();
        
    }
    
    private void createAdminRegister(){
        
        Usuario adm = new Usuario();
        adm.setNome("Administrador");
        adm.setRoles("ADMIN");
        adm.setUid("eacj_admin_uid");
        adm.setUsername(ADMIN_USERNAME);
        adm.setPassword(passwordEncoder.encode(ADMIN_PASSWORD));
        
        usuarioRepository.save(adm);
        
    }
    
}