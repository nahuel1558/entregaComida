package com.isft194.entregaComida.mapper;


import com.isft194.entregaComida.dto.request.ProductoRequest;
import com.isft194.entregaComida.dto.response.ProductoResponse;
import com.isft194.entregaComida.dto.response.ProductosResponse;
import com.isft194.entregaComida.model.Producto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductoMapper {

    public Producto productoRequestToProducto(ProductoRequest productoRequest){
        return Producto.builder()
                .nombre(productoRequest.getNombre())
                .descripcion(productoRequest.getDescripcion())
                .precio(productoRequest.getPrecio())
                .build();
    }
public ProductoResponse productoToProductoResponse(Producto producto){
        return ProductoResponse.builder()
                .id(producto.getId())
                .nombre(producto.getNombre())
                .descripcion(producto.getDescripcion())
                .precio(producto.getPrecio())
                .build();
}

    public ProductosResponse productoListToResponse(List<Producto> productos) {
        List<ProductoResponse> productoResponseList = new ArrayList<>();

        for (Producto producto : productos) {
            ProductoResponse productoResponse = ProductoResponse.builder()
                    .id(producto.getId())
                    .nombre(producto.getNombre())
                    .descripcion(producto.getDescripcion())
                    .precio(producto.getPrecio())
                    .build();
            productoResponseList.add(productoResponse);
        }
        ProductosResponse productosResponse = ProductosResponse.builder()
                .productos(productoResponseList)
                .build();

        return productosResponse;
    }
}