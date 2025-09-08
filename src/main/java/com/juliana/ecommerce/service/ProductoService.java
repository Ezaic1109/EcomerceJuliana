package com.juliana.ecommerce.service;

import java.util.List;

import com.juliana.ecommerce.model.Producto;
import java.util.Optional;

public interface ProductoService {
    
    

    List<Producto> findAll(); // Aquí se extraen de la base
    
    
    Optional<Producto> findById(Long id); //aqui se extraen los datos por identificador
    

   List<Producto>findByNombre(String nom); //aqui se extraen los datos por nombre




}
 