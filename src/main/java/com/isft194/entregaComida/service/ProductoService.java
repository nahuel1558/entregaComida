package com.isft194.entregaComida.service;

import com.isft194.entregaComida.dto.ProductoDTO;
import com.isft194.entregaComida.mapper.ProductoMapper;
import com.isft194.entregaComida.model.Producto;
import com.isft194.entregaComida.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private ProductoMapper productoMapper;

    public List<ProductoDTO> findAll() {
        return productoRepository.findAll().stream()
                .map(productoMapper::toDTO)
                .collect(Collectors.toList());
    }

    public ProductoDTO findById(Long id) {
        Producto producto = productoRepository.findById(id).orElseThrow(() -> new RuntimeException("Producto no encontrado"));
        return productoMapper.toDTO(producto);
    }

    public ProductoDTO save(ProductoDTO productoDTO) {
        Producto producto = productoMapper.toEntity(productoDTO);
        producto = productoRepository.save(producto);
        return productoMapper.toDTO(producto);
    }

    public void deleteById(Long id) {
        productoRepository.deleteById(id);
    }
}
