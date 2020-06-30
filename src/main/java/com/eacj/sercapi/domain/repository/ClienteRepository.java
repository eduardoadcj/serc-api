
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
    Optional<Cliente> findByUsuarioIdAndCpf(Long id, String cpf);
    List<Cliente> findByUsuarioId(Long id);
    
    Page<Cliente> findByUsuarioId(Long id, Pageable pageable);
    
    
    Page<Cliente> findByUsuarioIdAndNomeContainingIgnoreCase(Long id, String nome, Pageable pageable);
    
    Page<Cliente> findByUsuarioIdAndNomeContainingIgnoreCaseAndNumeroCalcadoAndNumeroJeans(Long id,
            String nome, String numeroCalcado, String numeroJeans, Pageable pageable);
    
    Page<Cliente> findByUsuarioIdAndNomeContainingIgnoreCaseAndNumeroCalcado(Long id,
            String nome, String numeroCalcado, Pageable pageable);
    
    Page<Cliente> findByUsuarioIdAndNomeContainingIgnoreCaseAndNumeroJeans(Long id,
            String nome, String numeroJeans, Pageable pageable);
    
   
    Page<Cliente> findByUsuarioIdAndNumeroCalcadoAndNumeroJeans(Long id,
            String numeroCalcado, String numeroJeans, Pageable pageable);
    
    Page<Cliente> findByUsuarioIdAndNumeroCalcado(Long id,
            String numeroJeans, Pageable pageable);
    
    Page<Cliente> findByUsuarioIdAndNumeroJeans(Long id,
            String numeroJeans, Pageable pageable);
    
    List<Cliente> findByUsuarioIdAndNumeroCalcadoOrderByNomeAsc(Long id, String numeroCalcado);
    List<Cliente> findByUsuarioIdAndNumeroJeansOrderByNomeAsc(Long id, String numeroJeans);
    List<Cliente> findByUsuarioIdAndNumeroCalcadoAndNumeroJeansOrderByNomeAsc(Long id, String numeroCalcado, String numeroJeans);
    
    Optional<Cliente> findByIdAndUsuarioId(Long id, Long uid);
    
}
