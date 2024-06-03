package com.isft194.entregaComida.mapper;


import com.isft194.entregaComida.dto.request.RestauranteRequest;
import com.isft194.entregaComida.dto.response.RestauranteResponse;
import com.isft194.entregaComida.dto.response.RestaurantesResponse;
import com.isft194.entregaComida.model.Restaurante;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RestauranteMapper {

    public Restaurante restauranteRequestToRestaurante(RestauranteRequest restauranteRequest){
        return Restaurante.builder()
                .nombre(restauranteRequest.getNombre())
                .build();
    }
    public RestauranteResponse restauranteToRestauranteResponse(Restaurante restaurante){
        return RestauranteResponse.builder()
                .id(restaurante.getId())
                .nombre(restaurante.getNombre())
                .build();
    }

    public RestaurantesResponse restauranteListToResponse(List<Restaurante> restaurantes) {
        List<RestauranteResponse> restauranteResponseList = new ArrayList<>();

        for (Restaurante restaurante : restaurantes) {
            RestauranteResponse restauranteResponse = RestauranteResponse.builder()
                    .id(restaurante.getId())
                    .nombre(restaurante.getNombre())
                    .build();
            restauranteResponseList.add(restauranteResponse);
        }
        RestaurantesResponse restaurantesResponse = RestaurantesResponse.builder()
                .restaurantes(restauranteResponseList)
                .build();

        return restaurantesResponse;
    }
}
