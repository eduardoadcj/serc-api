
package com.eacj.sercapi.api.security.service;

import com.eacj.sercapi.domain.model.Usuario;
import com.eacj.sercapi.domain.repository.UsuarioRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class CustomUserDetailsService implements UserDetailsService{

    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        
        List<Usuario> usuarios = usuarioRepository.findByUsername(username);
        
        if(usuarios.isEmpty())
            throw new UsernameNotFoundException("user not found");
        
        return new CustomUserDetails(usuarios.get(0));
        
    }
    
}
