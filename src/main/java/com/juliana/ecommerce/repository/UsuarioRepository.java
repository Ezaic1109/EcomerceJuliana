package com.juliana.ecommerce.repository;

import com.juliana.ecommerce.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;


public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
 Optional<Usuario> findByUsername(String username);
    Optional<Usuario> findByEmail(String email);
    Optional<Usuario> findByTokenConfirmacion(String token);

}