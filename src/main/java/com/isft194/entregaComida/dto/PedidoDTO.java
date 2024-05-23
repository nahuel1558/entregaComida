package com.isft194.entregaComida.dto;


import lombok.*;
import java.util.List;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString @EqualsAndHashCode
public class PedidoDTO {

    private Long id;
    private Long clienteId;
    private List<Long> itemsIds;
}