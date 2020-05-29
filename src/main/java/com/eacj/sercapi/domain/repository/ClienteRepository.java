
package com.eacj.sercapi.domain.repository;

import com.eacj.sercapi.domain.model.Cliente;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long>{
    
    Optional<Cliente> findByCpf(String cpf);
    List<Cliente> findByUsuarioId(Long id);
    Page<Cliente> findByUsuarioId(Long id, Pageable pageable);
    Optional<Cliente> findByIdAndUsuarioId(Long id, Long uid);
    
}
