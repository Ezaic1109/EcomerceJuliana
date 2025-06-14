package com.juliana.ecommerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.juliana.ecommerce.model.Producto;
import com.juliana.ecommerce.repository.ProductoRepository;

@Service
public class ProductoService {
        @Autowired
    private ProductoRepository productoRepository;

    public List<Producto> obtenerTodosLosProductos() {
        return productoRepository.findAll(); // Aquí se extraen de la base
    }
}
 