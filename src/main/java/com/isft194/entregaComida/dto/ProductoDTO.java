package com.isft194.entregaComida.dto;

import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString @EqualsAndHashCode @Data
public class ProductoDTO {

    private Long id;
    private String nombre;
    private String descripcion;
    private Float precio;
    private Long menuId;
}