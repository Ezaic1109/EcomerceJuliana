package com.juliana.ecommerce.model;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.*;



@Entity
@Table(name = "usuarios")
public class Usuario implements UserDetails {
   @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(unique = true, nullable = false)
    private String email;

    private boolean verificado = false;

    @Column(name = "token_confirmacion")
    private String tokenConfirmacion;

    @Column(name = "token_expiracion")
    private LocalDateTime tokenExpiracion;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "usuarios_roles",
        joinColumns = @JoinColumn(name = "usuario_id"),
        inverseJoinColumns = @JoinColumn(name = "rol_id")
    )
    private Set<Rol> roles;

    // ----------------- Getters y Setters -----------------
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public boolean isVerificado() { return verificado; }
    public void setVerificado(boolean verificado) { this.verificado = verificado; }

    public String getTokenConfirmacion() { return tokenConfirmacion; }
    public void setTokenConfirmacion(String tokenConfirmacion) { this.tokenConfirmacion = tokenConfirmacion; }

    public LocalDateTime getTokenExpiracion() { return tokenExpiracion; }
    public void setTokenExpiracion(LocalDateTime tokenExpiracion) { this.tokenExpiracion = tokenExpiracion; }

    public Set<Rol> getRoles() { return roles; }
    public void setRoles(Set<Rol> roles) { this.roles = roles; }

    // ----------------- Métodos de utilidad -----------------
    public void generarToken() {
        this.tokenConfirmacion = UUID.randomUUID().toString();
        this.tokenExpiracion = LocalDateTime.now().plusHours(24);
        this.verificado = false;
    }

    public boolean tokenValido() {
        return tokenConfirmacion != null && tokenExpiracion != null
               && LocalDateTime.now().isBefore(tokenExpiracion);
    }

    public void activar() {
        this.verificado = true;
        this.tokenConfirmacion = null;
        this.tokenExpiracion = null;
    }

    // ----------------- Métodos de UserDetails -----------------
    @Override
    public Set<? extends GrantedAuthority> getAuthorities() {
        return roles; // Rol debe implementar GrantedAuthority
    }

    @Override
    public boolean isAccountNonExpired() { return true; }

    @Override
    public boolean isAccountNonLocked() { return true; }

    @Override
    public boolean isCredentialsNonExpired() { return true; }

    @Override
    public boolean isEnabled() { return verificado; }
}