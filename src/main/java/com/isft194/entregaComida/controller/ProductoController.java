package com.isft194.entregaComida.controller;


import com.isft194.entregaComida.dto.request.ProductoRequest;
import com.isft194.entregaComida.dto.response.ProductosResponse;
import com.isft194.entregaComida.model.Producto;
import com.isft194.entregaComida.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path = "/api/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @PostMapping(value = "/crearProducto", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createProducto(@RequestBody ProductoRequest productoRequest) {
        return productoService.newProducto(productoRequest);
    }
    @PutMapping(value = "/actualizarProducto/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateProducto(@PathVariable("id") Long id, @RequestBody ProductoRequest productoRequest) {
        return productoService.updateProducto(productoRequest, id);
    }

    @GetMapping(value = "/listarProductos", produces = MediaType.APPLICATION_JSON_VALUE)
    public ProductosResponse getAllProductos() {
        return productoService.listarProductos();
    }

    @GetMapping(value = "/mostrarProducto/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Optional<Producto> findProductoById(@PathVariable Long id) {
        return productoService.findProductoById(id);
    }


    @DeleteMapping(value = "/borrarProducto/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteProducto(@PathVariable Long id) {
       return productoService.deleteProducto(id);
    }

}