package com.isft194.entregaComida.dto.response;

import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ClientesResponse {
    private List<ClienteResponse> clientes;
}
