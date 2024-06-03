package com.isft194.entregaComida.dto.request;

import java.util.List;
import lombok.Data;

@Data
public class MenuRequest {
    private String nombre;
    private Long restauranteId;
    private List<Long> productosIds;
}
