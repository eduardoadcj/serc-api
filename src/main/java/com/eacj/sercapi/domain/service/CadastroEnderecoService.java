
package com.eacj.sercapi.domain.service;

import com.eacj.sercapi.domain.exception.BusinessException;
import com.eacj.sercapi.domain.model.Endereco;
import com.eacj.sercapi.domain.repository.EnderecoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CadastroEnderecoService {
    
    @Autowired
    private EnderecoRepository enderecoRepository;
    
    public Endereco salvar(Endereco endereco){
        
        List<Endereco> enderecos = enderecoRepository.findByClienteId(
                endereco.getCliente().getId());
        
        if(!enderecos.isEmpty()){
            for(Endereco e : enderecos){
                if(e.getTitulo().equalsIgnoreCase(endereco.getTitulo()))
                    throw new BusinessException("O cliente informado já possui um endereço com este titulo");
            }
        }
        
        return enderecoRepository.save(endereco);
        
    }
    
    public Endereco alterar(Endereco endereco){
        return enderecoRepository.save(endereco);
    }
    
    public void remover(Long id){
        enderecoRepository.deleteById(id);
    }
    
}
