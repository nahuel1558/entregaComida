package com.isft194.entregaComida.mapper;


import com.isft194.entregaComida.dto.ProductoDTO;
import com.isft194.entregaComida.model.Producto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductoMapper {

    @Autowired
    private ModelMapper modelMapper;

    public ProductoDTO toDTO(Producto producto) {
        return modelMapper.map(producto, ProductoDTO.class);
    }

    public Producto toEntity(ProductoDTO productoDTO) {
        return modelMapper.map(productoDTO, Producto.class);
    }
}