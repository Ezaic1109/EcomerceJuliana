package com.juliana.ecommerce.controller;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.juliana.ecommerce.model.Producto;
import com.juliana.ecommerce.repository.ProductoRepository;



@RestController
@RequestMapping("/api/producto")
public class ProductoController {
 
    private final ProductoRepository productoRepository;

    public ProductoController(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    @GetMapping("/api/producto")
    public List<Producto> getProductos() {
        return productoRepository.findAll();
    }
    
    
}
