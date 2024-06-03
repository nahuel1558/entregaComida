package com.isft194.entregaComida.dto.response;

import java.util.List;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class MenuResponse {
    private Long id;
    private String nombre;
    private RestauranteResponse restaurante;
    private List<ProductoResponse> productos;
}
