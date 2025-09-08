package com.juliana.ecommerce.service.serviceimpl;

import com.juliana.ecommerce.model.Usuario;
import com.juliana.ecommerce.repository.UsuarioRepository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;



@Service
public class UsuarioDetailsService implements UserDetailsService {

   @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // Buscar usuario por email
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado con email: " + email));

        // Verificar si está confirmado
        if (!usuario.isVerificado()) {
            throw new UsernameNotFoundException("Usuario no ha confirmado su cuenta");
        }

        return usuario; // Spring usará getPassword(), getAuthorities(), isEnabled(), etc.
    }
}