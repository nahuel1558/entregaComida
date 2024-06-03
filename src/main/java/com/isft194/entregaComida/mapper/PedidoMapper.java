package com.isft194.entregaComida.mapper;

import com.isft194.entregaComida.dto.request.ItemRequest;
import com.isft194.entregaComida.dto.request.PedidoRequest;
import com.isft194.entregaComida.dto.response.PedidoResponse;
import com.isft194.entregaComida.dto.response.PedidosResponse;
import com.isft194.entregaComida.model.Cliente;
import com.isft194.entregaComida.model.Item;
import com.isft194.entregaComida.model.Pedido;
import com.isft194.entregaComida.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PedidoMapper {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ClienteMapper clienteMapper;

    @Autowired
    private ItemMapper itemMapper;

    public Pedido pedidoRequestToPedido(PedidoRequest pedidoRequest) {
        Cliente cliente = clienteService.findClienteById(pedidoRequest.getClienteId())
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

        Pedido pedido = Pedido.builder()
                .cliente(cliente)
                .items(new ArrayList<>())
                .build();

        for (ItemRequest itemRequest : pedidoRequest.getItems()) {
            Item item = itemMapper.itemRequestToItem(itemRequest);
            pedido.getItems().add(item);
        }

        return pedido;
    }

    public PedidoResponse pedidoToPedidoResponse(Pedido pedido) {
        return PedidoResponse.builder()
                .id(pedido.getId())
                .cliente(clienteMapper.clienteToClienteResponse(pedido.getCliente()))
                .items(pedido.getItems().stream()
                        .map(itemMapper::itemToItemResponse)
                        .collect(Collectors.toList()))
                .build();
    }
    public PedidosResponse pedidoListToResponse(List<Pedido> pedidos) {
        List<PedidoResponse> pedidoResponseList = pedidos.stream()
                .map(this::pedidoToPedidoResponse)
                .collect(Collectors.toList());

        return PedidosResponse.builder()
                .pedidos(pedidoResponseList)
                .build();
    }
}