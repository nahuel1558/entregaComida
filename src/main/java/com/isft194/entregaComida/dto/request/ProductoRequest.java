package com.isft194.entregaComida.dto.request;
import lombok.Data;

@Data
public class ProductoRequest {
    private String nombre;
    private String descripcion;
    private Float precio;
}
