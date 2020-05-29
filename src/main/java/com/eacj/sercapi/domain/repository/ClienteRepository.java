
package com.eacj.sercapi.domain.repository;

import com.eacj.sercapi.domain.model.Cliente;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long>{
    
    Optional<Cliente> findByCpf(String cpf);
    
}
