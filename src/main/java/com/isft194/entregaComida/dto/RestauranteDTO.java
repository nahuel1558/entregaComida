package com.isft194.entregaComida.dto;


import lombok.*;
import java.util.List;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString @EqualsAndHashCode
public class RestauranteDTO {

    private Long id;
    private String nombre;
    private List<Long> menusIds;
}