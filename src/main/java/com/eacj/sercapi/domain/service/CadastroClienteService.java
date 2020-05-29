package com.eacj.sercapi.domain.service;

import com.eacj.sercapi.domain.model.Cliente;
import com.eacj.sercapi.domain.repository.ClienteRepository;
import com.eacj.sercapi.domain.repository.EnderecoRepository;
import java.time.OffsetDateTime;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CadastroClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    public Cliente salvar(Cliente cliente) {

        Optional<Cliente> clienteExistente = clienteRepository.findByCpf(cliente.getCpf());

        if (clienteExistente.isPresent()) 
            throw new RuntimeException("Já existe um cliente com este CPF.");

        cliente.setDataRegistro(OffsetDateTime.now());

        return clienteRepository.save(cliente);

    }
    
    public Cliente alterar(Cliente cliente){
        Cliente oldCliente = clienteRepository.findById(cliente.getId()).get();
        cliente.setDataRegistro(oldCliente.getDataRegistro());
        return clienteRepository.save(cliente);
    }
        
    public void remover(Long id) {
        enderecoRepository.deleteByClienteId(id);
        clienteRepository.deleteById(id);
    }

}
