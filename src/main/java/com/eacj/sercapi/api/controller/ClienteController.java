package com.eacj.sercapi.api.controller;

import com.eacj.sercapi.api.model.ClienteInputModel;
import com.eacj.sercapi.api.model.ClienteModel;
import com.eacj.sercapi.api.security.service.AuthenticatedUserService;
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
    
    @Autowired
    private AuthenticatedUserService authenticatedUserService;

    
    @GetMapping("/all")
    public List<ClienteModel> listar(){
        List<Cliente> list = clienteRepository.findByUsuarioId(
                authenticatedUserService.getAuthenticatedUsuario().getId());
        return toCollectionModel(list);
    }
    
    @GetMapping
    public ResponseEntity<List<ClienteModel>> listar(@RequestParam int page) {

        HttpHeaders responseHeaders = new HttpHeaders();
        
        if (page < 0) 
            return ResponseEntity.badRequest().build();
        
        Usuario usuario = authenticatedUserService.getAuthenticatedUsuario();
        
        Pageable sortByName = PageRequest.of(page, DEFAULT_PAGE_SIZE, Sort.by("nome"));
        Page<Cliente> pages = clienteRepository.findByUsuarioId(usuario.getId(), sortByName);

        responseHeaders.add("X-Total-Count", String.valueOf(pages.getTotalElements()));

        return ResponseEntity.ok()
                .headers(responseHeaders)
                .body(toCollectionModel(pages.toList()));

    }
    
    @GetMapping("/nome")
    public List<ClienteModel> listarNome(@RequestParam String nome){
        
        List<Cliente> list = clienteRepository
                .findByUsuarioIdAndNomeContainingIgnoreCaseOrderByNomeAsc(
                        authenticatedUserService.getAuthenticatedUsuario().getId(),
                        nome);
        
        return toCollectionModel(list);
        
    }
    
    @GetMapping("/numeroCalcado")
    public List<ClienteModel> listarNumeroCalcado(@RequestParam String numeroCalcado){
        
        List<Cliente> list = clienteRepository.findByUsuarioIdAndNumeroCalcadoOrderByNomeAsc(
                        authenticatedUserService.getAuthenticatedUsuario().getId(), numeroCalcado);
        
        return toCollectionModel(list);
        
    }
    
    @GetMapping("/numeroJeans")
    public List<ClienteModel> listarNumeroJeans(@RequestParam String numeroJeans){
        
        List<Cliente> list = clienteRepository.findByUsuarioIdAndNumeroJeansOrderByNomeAsc(
                        authenticatedUserService.getAuthenticatedUsuario().getId(), numeroJeans);
        
        return toCollectionModel(list);
        
    }
    
    @GetMapping("/numeroCalcado/numeroJeans")
    public List<ClienteModel> listarNumeroCalcadoNumeroJeans(
            @RequestParam String numeroCalcado,
            @RequestParam String numeroJeans){
        
        List<Cliente> list = clienteRepository.findByUsuarioIdAndNumeroCalcadoAndNumeroJeansOrderByNomeAsc(
                        authenticatedUserService.getAuthenticatedUsuario().getId(), numeroCalcado, numeroJeans);
        
        return toCollectionModel(list);
        
    }
    
    @GetMapping("/numeroJeans/numeroCalcado")
    public List<ClienteModel> listarNumeroJeansNumeroCalcado(
            @RequestParam String numeroCalcado,
            @RequestParam String numeroJeans){
        
        List<Cliente> list = clienteRepository.findByUsuarioIdAndNumeroCalcadoAndNumeroJeansOrderByNomeAsc(
                        authenticatedUserService.getAuthenticatedUsuario().getId(), numeroCalcado, numeroJeans);
        
        return toCollectionModel(list);
        
    }
    
    @GetMapping("/id/{id}")
    public ResponseEntity<ClienteModel> buscarPorId(@PathVariable Long id) {
        
        Usuario usuario = authenticatedUserService.getAuthenticatedUsuario();
        Optional<Cliente> opCliente = clienteRepository.findByIdAndUsuarioId(id,
                usuario.getId());
        
        if (!opCliente.isPresent()) 
            return ResponseEntity.notFound().build();
        
        return ResponseEntity.ok(toModel(opCliente.get()));
        
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClienteModel salvar(@Valid @RequestBody ClienteInputModel clienteInputModel) {
        Cliente cliente = toEntity(clienteInputModel);
        cliente.setUsuario(authenticatedUserService.getAuthenticatedUsuario());
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
        cliente.setUsuario(authenticatedUserService.getAuthenticatedUsuario());
        cliente = cadastroClienteService.alterar(cliente);

        return ResponseEntity.ok(toModel(cliente));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        
        if (!clienteRepository.existsById(id)) 
            return ResponseEntity.notFound().build();
        
        cadastroClienteService.remover(id);
        return ResponseEntity.noContent().build();

    }

    
    //Entity parsers
    
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
