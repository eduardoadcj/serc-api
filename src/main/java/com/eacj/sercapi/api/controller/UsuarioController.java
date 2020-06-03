
package com.eacj.sercapi.api.controller;

import com.eacj.sercapi.api.model.UsuarioInputModel;
import com.eacj.sercapi.api.model.UsuarioModel;
import com.eacj.sercapi.domain.model.Usuario;
import com.eacj.sercapi.domain.repository.UsuarioRepository;
import com.eacj.sercapi.domain.service.CadastroUsuarioService;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    
    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @Autowired
    private CadastroUsuarioService cadastroUsuarioService;
    
    @Autowired
    private ModelMapper modelMapper;
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    
    @Secured({"ROLE_ADMIN"})
    @GetMapping
    public List<UsuarioModel> listar(){
        return toCollectionModel(usuarioRepository.findAll());
    }
    
    @Secured({"ROLE_ADMIN"})
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UsuarioModel salvar(@Valid @RequestBody UsuarioInputModel usuarioInputModel){
        Usuario usuario = toEntity(usuarioInputModel);
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        return toModel(cadastroUsuarioService.salvar(usuario));
    }
    
    @Secured({"ROLE_ADMIN"})
    @PutMapping("/desativar/id/{id}")
    public ResponseEntity<Void> desativarPorId(@PathVariable Long id){
        
        if(!usuarioRepository.existsById(id))
            return ResponseEntity.notFound().build();
        
        cadastroUsuarioService.desativar(id);
        return ResponseEntity.noContent().build();
        
    }
    
    @Secured({"ROLE_ADMIN"})
    @PutMapping("/desativar/uid/{uid}")
    public ResponseEntity<Void> desativarPorId(@PathVariable String uid){
        
        List<Usuario> usuarios = usuarioRepository.findByUid(uid);
        
        if(usuarios.isEmpty())
            return ResponseEntity.notFound().build();
        
        cadastroUsuarioService.desativar(usuarios.get(0).getId());
        return ResponseEntity.noContent().build();
        
    }
    
    private UsuarioModel toModel(Usuario usuario){
        return modelMapper.map(usuario, UsuarioModel.class);
    }
    
    private List<UsuarioModel> toCollectionModel(List<Usuario> usuarios){
        return usuarios.stream()
                .map((usuario) -> toModel(usuario))
                .collect(Collectors.toList());
    }
    
    private Usuario toEntity(UsuarioInputModel usuarioInputModel){
        return modelMapper.map(usuarioInputModel, Usuario.class);
    }
    
}
