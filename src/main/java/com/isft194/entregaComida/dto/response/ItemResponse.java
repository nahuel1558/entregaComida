package com.isft194.entregaComida.dto.response;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ItemResponse {
    private Long id;
    private ProductoResponse producto;
    private int cantidad;
    private Float precio;
}
