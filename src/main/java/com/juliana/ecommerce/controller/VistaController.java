package com.juliana.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.juliana.ecommerce.service.ProductoService;

@Controller
public class VistaController {
       @Autowired
    private ProductoService productoService;

    @GetMapping({"/", "/index", "/home", "/index.html"})
    public String mostrarIndex(Model model) {
        model.addAttribute("productos", productoService.findAll());
        return "index";  // Nombre del archivo Thymeleaf sin extensión .html
    }
}
