package com.juliana.ecommerce.service;

import com.juliana.ecommerce.model.Usuario;
import java.util.Optional;


public interface UsuarioService {
Usuario registrar(Usuario u, String rawPassword);
Optional<Usuario> findByEmail(String email);
}