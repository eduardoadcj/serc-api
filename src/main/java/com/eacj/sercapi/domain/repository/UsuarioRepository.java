
package com.eacj.sercapi.domain.repository;

import com.eacj.sercapi.domain.model.Usuario;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
    List<Usuario> findByUid(String uid);
    List<Usuario> findByUsername(String username);
    List<Usuario> findByRoles(String roles);
}
