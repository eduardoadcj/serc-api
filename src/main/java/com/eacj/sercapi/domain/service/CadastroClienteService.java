package com.eacj.sercapi.domain.service;

import com.eacj.sercapi.domain.exception.BusinessException;
import com.eacj.sercapi.domain.exception.EntityNotFoundException;
import com.eacj.sercapi.domain.model.Cliente;
import com.eacj.sercapi.domain.repository.ClienteRepository;
import com.eacj.sercapi.domain.repository.EnderecoRepository;
import com.eacj.sercapi.domain.repository.UsuarioRepository;
import java.time.OffsetDateTime;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CadastroClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;
    
    @Autowired
    private UsuarioRepository usuarioRepository;

    
    public Cliente salvar(Cliente cliente) {
        
        if(!usuarioRepository.existsById(cliente.getUsuario().getId()))
            throw new EntityNotFoundException("Usuário não encontrado");

        Optional<Cliente> clienteExistente = clienteRepository.findByUsuarioIdAndCpf(
                cliente.getUsuario().getId(),
                cliente.getCpf());

        if (clienteExistente.isPresent()) 
            throw new BusinessException("cpf_already_registered", "Já existe um cliente com este CPF");

        cliente.setDataRegistro(OffsetDateTime.now());

        return clienteRepository.save(cliente);

    }
    
    public Cliente alterar(Cliente cliente){
        
        if(!usuarioRepository.existsById(cliente.getUsuario().getId()))
            throw new EntityNotFoundException("Usuário não encontrado");
        
        Cliente oldCliente = clienteRepository.findById(cliente.getId()).get();
        cliente.setDataRegistro(oldCliente.getDataRegistro());
        
        return clienteRepository.save(cliente);
        
    }
    
    @Transactional
    public void remover(Long id) {
        enderecoRepository.deleteByClienteId(id);
        clienteRepository.deleteById(id);
    }

}
