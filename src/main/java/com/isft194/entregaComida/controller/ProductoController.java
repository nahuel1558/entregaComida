package com.isft194.entregaComida.controller;


import com.isft194.entregaComida.dto.ProductoDTO;
import com.isft194.entregaComida.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @GetMapping("/listarProductos")
    public List<ProductoDTO> getAllProductos() {
        return productoService.findAll();
    }

    @GetMapping("/mostrarProducto/{id}")
    public ResponseEntity<ProductoDTO> getProductoById(@PathVariable Long id) {
        return ResponseEntity.ok(productoService.findById(id));
    }

    @PostMapping("/crearProducto")
    public ResponseEntity<ProductoDTO> createProducto(@RequestBody ProductoDTO productoDTO) {
        return ResponseEntity.ok(productoService.save(productoDTO));
    }

    @DeleteMapping("/borrarProducto/{id}")
    public ResponseEntity<Void> deleteProducto(@PathVariable Long id) {
        productoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}