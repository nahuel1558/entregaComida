package com.isft194.entregaComida.service;

import com.isft194.entregaComida.dto.request.ProductoRequest;
import com.isft194.entregaComida.dto.response.ProductoResponse;
import com.isft194.entregaComida.dto.response.ProductosResponse;
import com.isft194.entregaComida.mapper.ProductoMapper;
import com.isft194.entregaComida.model.Producto;
import com.isft194.entregaComida.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private ProductoMapper productoMapper;

    public ResponseEntity<?> newProducto(ProductoRequest productoRequest) {
        try {
            if (camposRequeridosValidos(productoRequest)) {
                if (!productoRepository.existsByNombreAndDescripcion(productoRequest.getNombre(), productoRequest.getDescripcion())) {
                    Producto producto = productoRequestToNewProducto(productoRequest);
                    saveProducto(producto);
                    ProductoResponse response = productoMapper.productoToProductoResponse(producto);
                    return ResponseEntity.ok(response);
                } else {
                    return ResponseEntity.badRequest().body("El producto con ese nombre y descripción ya existe");
                }
            } else {
                return ResponseEntity.badRequest().body("Nombre, Descripción y Precio son campos obligatorios.");
            }
        } catch (Exception exception){
            return ResponseEntity.status(500).body("Error al crear el producto: " + exception.getMessage());
        }
    }

    public ResponseEntity<?> updateProducto(ProductoRequest productoRequest, Long id) {
        try {
            Optional<Producto> productoOptional = productoRepository.findById(id);
            if (productoOptional.isPresent()){
                Producto producto = productoRequestToExistedProducto(productoRequest);
                producto.setId(id);
                productoRepository.save(producto);
                return ResponseEntity.ok("Producto actualizado: \n" + producto.getNombre() + "\n " + producto.getDescripcion() + "\n " + producto.getPrecio());
            } else{
                return ResponseEntity.badRequest().body("No existen productos con el id: " + id);
            }
        } catch (Exception exception){
            return ResponseEntity.status(500).body("Error al actualizar el producto: " + exception.getMessage());
        }
    }


    public void saveProducto(Producto producto){
        productoRepository.save(producto);
    }
    public Producto productoRequestToNewProducto(ProductoRequest productoRequest) {
        return productoMapper.productoRequestToProducto(productoRequest);
    }
    public Producto productoRequestToExistedProducto(ProductoRequest productoRequest) {
        return productoMapper.productoRequestToProducto(productoRequest);
    }

    public ProductosResponse listarProductos() {
        List<Producto> listaProductos = productoRepository.findAll();
        return productoMapper.productoListToResponse(listaProductos);
    }

    public Optional<Producto> findProductoById(Long id) {
        return productoRepository.findById(id);
    }

    public Producto save(Producto producto) {
        return productoRepository.save(producto);
    }

    public ResponseEntity<?> deleteProducto(Long id) {
        try {
            if (productoRepository.existsById(id)) {
                productoRepository.deleteById(id);
                return ResponseEntity.ok("Producto eliminado con id: " + id);
            } else {
                return ResponseEntity.badRequest().body("No existen productos con el id: " + id);
            }
        }catch(Exception exception){
                return ResponseEntity.status(500).body("Error al eliminar el producto: " + exception.getMessage());
            }
        }
    private boolean camposRequeridosValidos(ProductoRequest productoRequest) {
        return productoRequest.getNombre() != null && !productoRequest.getNombre().isEmpty() &&
                productoRequest.getDescripcion() != null && !productoRequest.getDescripcion().isEmpty() &&
                productoRequest.getPrecio() != null && !productoRequest.getPrecio().isNaN();
    }
}
