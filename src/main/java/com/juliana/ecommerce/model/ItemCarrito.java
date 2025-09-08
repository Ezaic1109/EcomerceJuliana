package com.juliana.ecommerce.model;

import jakarta.persistence.*;


@Entity
@Table(name = "items_carrito")
public class ItemCarrito {
@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;


@ManyToOne(optional = false)
private Producto producto;


private int cantidad;
private int precioUnitario; // congelamos el precio al momento de agregar


@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "carrito_id")
private Carrito carrito;


// Relación opcional con Orden si se reutiliza como detalle
@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "orden_id")
private Orden orden;


public Long getId() {
    return id;
}


public void setId(Long id) {
    this.id = id;
}


public Producto getProducto() {
    return producto;
}


public void setProducto(Producto producto) {
    this.producto = producto;
}


public int getCantidad() {
    return cantidad;
}


public void setCantidad(int cantidad) {
    this.cantidad = cantidad;
}


public int getPrecioUnitario() {
    return precioUnitario;
}


public void setPrecioUnitario(int precioUnitario) {
    this.precioUnitario = precioUnitario;
}


public Carrito getCarrito() {
    return carrito;
}


public void setCarrito(Carrito carrito) {
    this.carrito = carrito;
}


public Orden getOrden() {
    return orden;
}


public void setOrden(Orden orden) {
    this.orden = orden;
}



}