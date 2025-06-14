package com.juliana.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.juliana.ecommerce.model.Producto;
import com.juliana.ecommerce.service.ProductoService;

@RestController
@RequestMapping("/api/producto")
public class ProductoController {
 @Autowired
    private ProductoService productoService;

    @GetMapping
    public List<Producto> obtenerProductos() {
        return productoService.obtenerTodosLosProductos();
    }
    
}
