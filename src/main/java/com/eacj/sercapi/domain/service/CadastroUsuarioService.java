
package com.eacj.sercapi.domain.service;

import com.eacj.sercapi.domain.exception.BusinessException;
import com.eacj.sercapi.domain.exception.EntityNotFoundException;
import com.eacj.sercapi.domain.model.Usuario;
import com.eacj.sercapi.domain.repository.UsuarioRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CadastroUsuarioService {
    
    @Autowired
    private UsuarioRepository usuarioRepository;
    
    public Usuario salvar(Usuario usuario){
        
        if(!usuarioRepository.findByUsername(usuario.getUsername()).isEmpty())
            throw new BusinessException("username_already_used", "O username informado já esta sendo utilizado.");
        
        if(!usuarioRepository.findByUid(usuario.getUid()).isEmpty())
            throw new BusinessException("uid_already_used", "O uid informado  já esta sendo utilizado.");
        
        usuario.setRoles("USER");
        
        return usuarioRepository.save(usuario);
        
    }
    
    public void desativar(Long id){
        
        Optional<Usuario> opUsuario = usuarioRepository.findById(id);
        
        if(!opUsuario.isPresent())
            throw new EntityNotFoundException("Usuário não encontrado");
        
        Usuario usuario = opUsuario.get();
        usuario.setEnable(false);
        
        usuarioRepository.save(usuario);
        
    }
    
}
