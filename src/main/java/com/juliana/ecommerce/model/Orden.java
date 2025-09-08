package com.juliana.ecommerce.model;


import jakarta.persistence.*;
import java.time.*;
import java.util.*;

@Entity
@Table(name = "ordenes")
public class Orden {
@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;


@ManyToOne(optional = false)
private Usuario usuario;


private LocalDateTime fecha = LocalDateTime.now();


@Enumerated(EnumType.STRING)
private Estado estado = Estado.PENDIENTE_PAGO;


@OneToMany(mappedBy = "orden", cascade = CascadeType.ALL, orphanRemoval = true)
private List<ItemCarrito> items = new ArrayList<>();


public int getTotal() {
return items.stream().mapToInt(i -> i.getPrecioUnitario() * i.getCantidad()).sum();
}


public enum Estado { PENDIENTE_PAGO, PAGADA, CANCELADA }
// getters/setters


public Long getId() {
    return id;
}


public void setId(Long id) {
    this.id = id;
}


public Usuario getUsuario() {
    return usuario;
}


public void setUsuario(Usuario usuario) {
    this.usuario = usuario;
}


public LocalDateTime getFecha() {
    return fecha;
}


public void setFecha(LocalDateTime fecha) {
    this.fecha = fecha;
}


public Estado getEstado() {
    return estado;
}


public void setEstado(Estado estado) {
    this.estado = estado;
}


public List<ItemCarrito> getItems() {
    return items;
}


public void setItems(List<ItemCarrito> items) {
    this.items = items;
}




}