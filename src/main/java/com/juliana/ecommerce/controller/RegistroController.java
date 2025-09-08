package com.juliana.ecommerce.controller;

import com.juliana.ecommerce.model.Usuario;
import com.juliana.ecommerce.repository.UsuarioRepository;
import com.juliana.ecommerce.service.EmailService;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class RegistroController {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private EmailService emailService;

    @GetMapping("/registro")
    public String mostrarRegistro(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "registro";
    }

  @PostMapping("/registro")
public String registrarUsuario(@ModelAttribute Usuario usuario, Model model,
                               @RequestParam("confirmPassword") String confirmPassword) {
    if (!usuario.getPassword().equals(confirmPassword)) {
        model.addAttribute("error", "Las contraseñas no coinciden.");
        return "registro";
    }
    if (usuarioRepository.findByEmail(usuario.getEmail()).isPresent()) {
        model.addAttribute("error", "El correo ya está registrado.");
        return "registro";
    }

    usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
    usuario.generarToken(); // genera token y pone verificado = false
    usuarioRepository.save(usuario);

    String link = "http://localhost:8080/confirmar?token=" + usuario.getTokenConfirmacion();
    String mensaje = "Gracias por registrarte. Confirma tu cuenta haciendo clic en el siguiente enlace: " + link;

    emailService.enviarEmailVerificacion(usuario.getEmail(), "Confirma tu cuenta", mensaje);
    model.addAttribute("success", "Registro exitoso. Revisa tu correo para confirmar tu cuenta.");
    return "registro";
}

    @GetMapping("/confirmar")
    public String confirmarCuenta(@RequestParam("token") String token, Model model) {
        usuarioRepository.findByTokenConfirmacion(token).ifPresentOrElse(usuario -> {
            usuario.setVerificado(true);
            usuario.setTokenConfirmacion(null);
            usuarioRepository.save(usuario);
            model.addAttribute("success", "Cuenta confirmada. Ahora puedes iniciar sesión.");
        }, () -> model.addAttribute("error", "Token inválido o expirado."));
        return "login";
    }

    // Recuperar contraseña
    @GetMapping("/recuperar")
    public String mostrarRecuperarPassword() {
        return "recuperar";
    }

    @PostMapping("/recuperar")
    public String recuperarPassword(@RequestParam String email, Model model) {
        usuarioRepository.findByEmail(email).ifPresentOrElse(usuario -> {
            String token = UUID.randomUUID().toString();
            usuario.setTokenConfirmacion(token);
            usuarioRepository.save(usuario);

            String link = "http://localhost:8080/reset-password?token=" + token;
            String mensaje = "Para restablecer tu contraseña haz clic en este enlace: " + link;
            emailService.enviarEmailVerificacion(usuario.getEmail(), "Restablecer contraseña", mensaje);

            model.addAttribute("success", "Se envió un correo para restablecer tu contraseña.");
        }, () -> model.addAttribute("error", "No existe un usuario con ese correo."));
        return "recuperar";
    }

    @GetMapping("/reset-password")
    public String mostrarResetPassword(@RequestParam String token, Model model) {
        model.addAttribute("token", token);
        return "reset-password";
    }

    @PostMapping("/reset-password")
    public String resetPassword(@RequestParam String token,
                                @RequestParam String password,
                                @RequestParam String confirmPassword,
                                Model model) {
        if (!password.equals(confirmPassword)) {
            model.addAttribute("error", "Las contraseñas no coinciden.");
            model.addAttribute("token", token);
            return "reset-password";
        }

        usuarioRepository.findByTokenConfirmacion(token).ifPresentOrElse(usuario -> {
            usuario.setPassword(passwordEncoder.encode(password));
            usuario.setTokenConfirmacion(null);
            usuarioRepository.save(usuario);
            model.addAttribute("success", "Contraseña actualizada. Ahora puedes iniciar sesión.");
        }, () -> model.addAttribute("error", "Token inválido o expirado."));

        return "login";
    }
}