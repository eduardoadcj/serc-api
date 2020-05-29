
package com.eacj.sercapi.api.controller;

import com.eacj.sercapi.api.model.EnderecoInputModel;
import com.eacj.sercapi.api.model.EnderecoModel;
import com.eacj.sercapi.domain.model.Endereco;
import com.eacj.sercapi.domain.repository.EnderecoRepository;
import com.eacj.sercapi.domain.service.CadastroEnderecoService;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/enderecos")
public class EnderecoController {
    
    @Autowired
    private CadastroEnderecoService cadastroEnderecoService;
    
    @Autowired
    private EnderecoRepository enderecoRepository;
    
    @Autowired
    private ModelMapper modelMapper;
    
    @GetMapping
    public List<EnderecoModel> listar(){
        return toCollectionModel(enderecoRepository.findAll());
    }
    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EnderecoModel salvar(@Valid @RequestBody EnderecoInputModel enderecoInputModel){
        Endereco endereco = toEntity(enderecoInputModel);
        return toModel(cadastroEnderecoService.salvar(endereco));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<EnderecoModel> alterar(@Valid @PathVariable Long id,
            @RequestBody EnderecoInputModel enderecoInputModel){
        
        if(!enderecoRepository.existsById(id))
            return ResponseEntity.notFound().build();
        
        Endereco endereco = toEntity(enderecoInputModel);
        endereco.setId(id);
        
        Endereco newEndereco = cadastroEnderecoService.alterar(endereco);
        
        return ResponseEntity.ok(toModel(newEndereco));
        
    }
    
    private EnderecoModel toModel(Endereco endereco){
        return modelMapper.map(endereco, EnderecoModel.class);
    }
    
    private List<EnderecoModel> toCollectionModel(List<Endereco> enderecos){
        return enderecos.stream()
                .map(endereco -> toModel(endereco))
                .collect(Collectors.toList());
    }
    
    private Endereco toEntity(EnderecoInputModel enderecoInputModel){
        return modelMapper.map(enderecoInputModel, Endereco.class);
    }
    
}
