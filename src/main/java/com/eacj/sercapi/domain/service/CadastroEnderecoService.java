
package com.eacj.sercapi.domain.service;

import com.eacj.sercapi.domain.exception.BusinessException;
import com.eacj.sercapi.domain.exception.EntityNotFoundException;
import com.eacj.sercapi.domain.model.Endereco;
import com.eacj.sercapi.domain.repository.ClienteRepository;
import com.eacj.sercapi.domain.repository.EnderecoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CadastroEnderecoService {
    
    @Autowired
    private EnderecoRepository enderecoRepository;
    
    @Autowired
    private ClienteRepository clienteRepository;
    
    public Endereco salvar(Endereco endereco){
        
        if(!clienteRepository.existsById(endereco.getCliente().getId()))
            throw new EntityNotFoundException("Cliente não encontrado");
        
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
    
        if(!clienteRepository.existsById(endereco.getCliente().getId()))
            throw new EntityNotFoundException("Cliente não encontrado");
        
        return enderecoRepository.save(endereco);
    
    }
    
    public void remover(Long id){
        enderecoRepository.deleteById(id);
    }
    
}
