package com.isft194.entregaComida.dto.response;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Builder
@Getter
@Data
public class ProductoResponse {
    private Long id;
    private String nombre;
    private String descripcion;
    private Float precio;
}
