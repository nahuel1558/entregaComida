package com.isft194.entregaComida.dto.request;

import java.util.List;
import lombok.Data;

@Data
public class PedidoRequest {
    private Long clienteId;
    private List<ItemRequest> items;
}
