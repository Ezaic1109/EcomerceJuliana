package com.juliana.ecommerce.repository;

import com.juliana.ecommerce.model.Orden;
import org.springframework.data.jpa.repository.JpaRepository;


public interface OrdenRepository extends JpaRepository<Orden, Long> { }