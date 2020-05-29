
package com.eacj.sercapi.domain.repository;

import com.eacj.sercapi.domain.model.Endereco;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long>{
    List<Endereco> findByClienteId(Long id);
    void deleteByClienteId(Long id);
}
