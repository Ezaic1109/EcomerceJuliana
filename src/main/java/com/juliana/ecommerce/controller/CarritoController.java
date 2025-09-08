package com.juliana.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.juliana.ecommerce.model.Carrito;
import com.juliana.ecommerce.model.Usuario;
import com.juliana.ecommerce.service.CarritoService;

@RestController
@RequestMapping("/api/carrito")
public class CarritoController {

    @Autowired
    private CarritoService carritoService;

    @GetMapping
    public Carrito verCarrito(@AuthenticationPrincipal Usuario usuario) {
        return carritoService.obtenerOCrear(usuario);
    }

    @PostMapping("/agregar/{productoId}")
    public Carrito agregar(@AuthenticationPrincipal Usuario usuario,
                           @PathVariable Long productoId,
                           @RequestParam(defaultValue = "1") int cantidad) {
        return carritoService.agregarProducto(usuario, productoId, cantidad);
    }

    @PostMapping("/actualizar/{itemId}")
    public Carrito actualizar(@AuthenticationPrincipal Usuario usuario,
                              @PathVariable Long itemId,
                              @RequestParam int cantidad) {
        return carritoService.actualizarCantidad(usuario, itemId, cantidad);
    }

    @DeleteMapping("/eliminar/{itemId}")
    public Carrito eliminar(@AuthenticationPrincipal Usuario usuario,
                            @PathVariable Long itemId) {
        return carritoService.eliminarItem(usuario, itemId);
    }

    @DeleteMapping("/limpiar")
    public void limpiar(@AuthenticationPrincipal Usuario usuario) {
        carritoService.limpiar(usuario);
    }
}