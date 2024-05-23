package com.isft194.entregaComida.dto;

import lombok.*;
import java.util.List;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString @EqualsAndHashCode @Data
public class ClienteDTO {

    private Long id;
    private String nombre;
    private String direccion;
    private String correoElectronico;
}