package com.eacj.sercapi.api.controller;

import com.eacj.sercapi.api.model.ClienteInputModel;
import com.eacj.sercapi.api.model.ClienteModel;
import com.eacj.sercapi.domain.model.Cliente;
import com.eacj.sercapi.domain.model.Usuario;
import com.eacj.sercapi.domain.repository.ClienteRepository;
import com.eacj.sercapi.domain.service.CadastroClienteService;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final static int DEFAULT_PAGE_SIZE = 25;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private CadastroClienteService cadastroClienteService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<ClienteModel>> listar(@RequestParam int page) {

        HttpHeaders responseHeaders = new HttpHeaders();
        
        if (page < 0) {
            List<Cliente> list = clienteRepository.findByUsuarioId(1l);
            responseHeaders.add("X-Total-Count", String.valueOf(list.size()));
            return ResponseEntity.ok()
                    .headers(responseHeaders)
                    .body(toCollectionModel(list));
        }

        Pageable sortByName = PageRequest.of(page, DEFAULT_PAGE_SIZE, Sort.by("nome"));
        Page<Cliente> pages = clienteRepository.findByUsuarioId(1l, sortByName);

        responseHeaders.add("X-Total-Count", String.valueOf(pages.getTotalElements()));

        return ResponseEntity.ok()
                .headers(responseHeaders)
                .body(toCollectionModel(pages.toList()));

    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteModel> buscarPorId(@PathVariable Long id) {
        Optional<Cliente> opCliente = clienteRepository.findByIdAndUsuarioId(id, 1l);
        if (!opCliente.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(toModel(opCliente.get()));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClienteModel salvar(@Valid @RequestBody ClienteInputModel clienteInputModel) {
        Cliente cliente = toEntity(clienteInputModel);
        Usuario u = new Usuario();
        u.setId(1l);
        cliente.setUsuario(u);
        return toModel(cadastroClienteService.salvar(cliente));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteModel> alterar(@Valid @PathVariable Long id,
            @RequestBody ClienteInputModel clienteInputModel) {

        if (!clienteRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        Cliente cliente = toEntity(clienteInputModel);
        cliente.setId(id);
        Usuario u = new Usuario();
        u.setId(1l);
        cliente.setUsuario(u);
        cliente = cadastroClienteService.alterar(cliente);

        return ResponseEntity.ok(toModel(cliente));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        if (!clienteRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        cadastroClienteService.remover(id);
        return ResponseEntity.noContent().build();

    }

    private ClienteModel toModel(Cliente cliente) {
        return modelMapper.map(cliente, ClienteModel.class);
    }

    private List<ClienteModel> toCollectionModel(List<Cliente> clientes) {
        return clientes.stream()
                .map(cliente -> toModel(cliente))
                .collect(Collectors.toList());
    }

    private Cliente toEntity(ClienteInputModel clienteModel) {
        return modelMapper.map(clienteModel, Cliente.class);
    }

}
