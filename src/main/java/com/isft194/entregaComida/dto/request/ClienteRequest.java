package com.isft194.entregaComida.dto.request;

import lombok.Data;

@Data
public class ClienteRequest {
    private String nombre;
    private String direccion;
    private String correoElectronico;
}
