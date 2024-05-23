package com.isft194.entregaComida.mapper;


import com.isft194.entregaComida.dto.RestauranteDTO;
import com.isft194.entregaComida.model.Producto;
import com.isft194.entregaComida.model.Restaurante;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class RestauranteMapper {


    @Autowired
    private ModelMapper modelMapper;

    public RestauranteDTO toDTO(Restaurante restaurante) {
        return modelMapper.map(restaurante, RestauranteDTO.class);
    }

    public Restaurante toEntity(RestauranteDTO restauranteDTO) {
        return modelMapper.map(restauranteDTO, Restaurante.class);
    }
}
