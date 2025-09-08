package com.juliana.ecommerce.service.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.juliana.ecommerce.model.Producto;
import com.juliana.ecommerce.repository.ProductoRepository;
import com.juliana.ecommerce.service.ProductoService;

@Service
public class ProductoServiceImpl implements ProductoService {

ProductoRepository productoRepository;


    public ProductoServiceImpl(ProductoRepository productoRepository) {
    this.productoRepository = productoRepository;
}

    @Override
    public List<Producto> findAll() {
        return productoRepository.findAll();
    }

    @Override
    public Optional<Producto> findById(Long id) {
       return productoRepository.findById(id);
    }

    @Override
    public List<Producto> findByNombre(String nom) {
      return productoRepository.findByNombre(nom);
    }
    
}
