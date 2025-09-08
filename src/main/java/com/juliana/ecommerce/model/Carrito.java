package com.juliana.ecommerce.model;

import jakarta.persistence.*;
import java.util.*;


@Entity
@Table(name = "carritos")
public class Carrito {
@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;


@OneToOne
@JoinColumn(name = "usuario_id", unique = true)
private Usuario usuario;


@OneToMany(mappedBy = "carrito", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
private List<ItemCarrito> items = new ArrayList<>();


public int getTotal() {
return items.stream().mapToInt(i -> i.getPrecioUnitario() * i.getCantidad()).sum();
}
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


public List<ItemCarrito> getItems() {
    return items;
}


public void setItems(List<ItemCarrito> items) {
    this.items = items;
}




}