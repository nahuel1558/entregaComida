package com.isft194.entregaComida.dto;


import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString @EqualsAndHashCode
public class ItemDTO {

    private Long id;
    private Long productoId;
    private int cantidad;
    private Float precio;
    private Long pedidoId;
}