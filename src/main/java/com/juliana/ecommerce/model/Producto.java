package com.juliana.ecommerce.model;

import jakarta.persistence.*;


@Entity
@Table(name = "producto")
public class Producto {
@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;
private String nombre;
@Column(length = 1000)
private String descripcion;
private int precio; // en MXN (centavos si prefieres exactitud)
private Integer stock = 0;
private String imagenUrl;
// getters/setters
public Long getId() {
    return id;
}
public void setId(Long id) {
    this.id = id;
}
public String getNombre() {
    return nombre;
}
public void setNombre(String nombre) {
    this.nombre = nombre;
}
public String getDescripcion() {
    return descripcion;
}
public void setDescripcion(String descripcion) {
    this.descripcion = descripcion;
}
public int getPrecio() {
    return precio;
}
public void setPrecio(int precio) {
    this.precio = precio;
}
public Integer getStock() {
    return stock;
}
public void setStock(Integer stock) {
    this.stock = stock;
}
public String getImagenUrl() {
    return imagenUrl;
}
public void setImagenUrl(String imagenUrl) {
    this.imagenUrl = imagenUrl;
}

}