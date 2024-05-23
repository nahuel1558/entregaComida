package com.isft194.entregaComida.service;


import com.isft194.entregaComida.dto.RestauranteDTO;
import com.isft194.entregaComida.mapper.RestauranteMapper;
import com.isft194.entregaComida.model.Restaurante;
import com.isft194.entregaComida.repository.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RestauranteService {

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Autowired
    private RestauranteMapper restauranteMapper;

    public List<RestauranteDTO> findAll() {
        return restauranteRepository.findAll().stream()
                .map(restauranteMapper::toDTO)
                .collect(Collectors.toList());
    }

    public RestauranteDTO findById(Long id) {
        Restaurante restaurante = restauranteRepository.findById(id).orElseThrow(() -> new RuntimeException("Restaurante no encontrado"));
        return restauranteMapper.toDTO(restaurante);
    }

    public RestauranteDTO save(RestauranteDTO restauranteDTO) {
        Restaurante restaurante = restauranteMapper.toEntity(restauranteDTO);
        restaurante = restauranteRepository.save(restaurante);
        return restauranteMapper.toDTO(restaurante);
    }

    public void deleteById(Long id) {
        restauranteRepository.deleteById(id);
    }
}