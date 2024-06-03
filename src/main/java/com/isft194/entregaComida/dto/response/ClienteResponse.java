package com.isft194.entregaComida.dto.response;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ClienteResponse {
    private Long id;
    private String nombre;
    private String direccion;
    private String correoElectronico;
}
