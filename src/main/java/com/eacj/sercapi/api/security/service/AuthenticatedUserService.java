
package com.eacj.sercapi.api.security.service;

import com.eacj.sercapi.api.exception.ApiException;
import com.eacj.sercapi.domain.model.Usuario;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticatedUserService {
    
    public CustomUserDetails getAuthenticatedCustomUserDetails(){
        
        Object obj = SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        
        if(obj instanceof CustomUserDetails)
            return (CustomUserDetails) obj;
        
        throw new ApiException("Erro ao retornar usuario logado.");
            
    }
    
    public Usuario getAuthenticatedUsuario(){
        return getAuthenticatedCustomUserDetails().getUsuario();
    }
    
}
