package com.juliana.ecommerce.service;

import com.juliana.ecommerce.model.*;


public interface CarritoService {
Carrito obtenerOCrear(Usuario usuario);
Carrito agregarProducto(Usuario usuario, Long productoId, int cantidad);
Carrito actualizarCantidad(Usuario usuario, Long itemId, int cantidad);
Carrito eliminarItem(Usuario usuario, Long itemId);
void limpiar(Usuario usuario);

}