package com.juliana.ecommerce.repository;

import com.juliana.ecommerce.model.Carrito;
import com.juliana.ecommerce.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;


public interface CarritoRepository extends JpaRepository<Carrito, Long> {
Optional<Carrito> findByUsuario(Usuario usuario);
}