package com.isft194.entregaComida.service;

import com.isft194.entregaComida.dto.request.ItemRequest;
import com.isft194.entregaComida.dto.request.PedidoRequest;
import com.isft194.entregaComida.dto.response.PedidoResponse;
import com.isft194.entregaComida.dto.response.PedidosResponse;
import com.isft194.entregaComida.mapper.ItemMapper;
import com.isft194.entregaComida.mapper.PedidoMapper;
import com.isft194.entregaComida.model.Item;
import com.isft194.entregaComida.model.Pedido;
import com.isft194.entregaComida.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private PedidoMapper pedidoMapper;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ItemMapper itemMapper;

    public ResponseEntity<?> createPedido(PedidoRequest pedidoRequest) {
        try {
            Pedido pedido = pedidoMapper.pedidoRequestToPedido(pedidoRequest);
            pedido = pedidoRepository.save(pedido);
            PedidoResponse response = pedidoMapper.pedidoToPedidoResponse(pedido);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error al crear el pedido: " + e.getMessage());
        }
    }
   public ResponseEntity<?> updatePedido(PedidoRequest pedidoRequest, Long id) {
        try {
            Optional<Pedido> pedidoOptional = pedidoRepository.findById(id);
            if (pedidoOptional.isPresent()) {
                Pedido pedido = pedidoOptional.get();

                pedido.setCliente(clienteService.findClienteById(pedidoRequest.getClienteId())
                        .orElseThrow(() -> new RuntimeException("Cliente no encontrado")));

                pedido.getItems().clear();

                for (ItemRequest itemRequest : pedidoRequest.getItems()) {
                    Item item = itemMapper.itemRequestToItem(itemRequest);
                    pedido.getItems().add(item);
                }

                pedidoRepository.save(pedido);

                PedidoResponse response = pedidoMapper.pedidoToPedidoResponse(pedido);
                return ResponseEntity.ok(response);
            } else {
                return ResponseEntity.badRequest().body("No existen pedidos con el id: " + id);
            }
        } catch (Exception exception) {
            return ResponseEntity.status(500).body("Error al actualizar el pedido: " + exception.getMessage());
        }
    }

    public ResponseEntity<?> getAllPedidos() {
        try {
            List<Pedido> pedidos = pedidoRepository.findAll();
            PedidosResponse response = pedidoMapper.pedidoListToResponse(pedidos);
            return ResponseEntity.ok(response);
        } catch (Exception exception) {
            return ResponseEntity.status(500).body("Error al listar los pedidos: " + exception.getMessage());
        }
    }
    public ResponseEntity<?> getPedidoById(Long id) {
        try {
            Optional<Pedido> pedidoOptional = pedidoRepository.findById(id);
            if (pedidoOptional.isPresent()) {
                PedidoResponse response = pedidoMapper.pedidoToPedidoResponse(pedidoOptional.get());
                return ResponseEntity.ok(response);
            } else {
                return ResponseEntity.badRequest().body("No existen pedidos con el id: " + id);
            }
        } catch (Exception exception) {
            return ResponseEntity.status(500).body("Error al obtener el pedido: " + exception.getMessage());
        }
    }
    public ResponseEntity<?> deletePedido(Long id) {
        try {
            if (pedidoRepository.existsById(id)) {
                pedidoRepository.deleteById(id);
                return ResponseEntity.ok("Pedido eliminado con id: " + id);
            } else {
                return ResponseEntity.badRequest().body("No existen pedidos con el id: " + id);
            }
        } catch (Exception exception) {
            return ResponseEntity.status(500).body("Error al eliminar el pedido: " + exception.getMessage());
        }
    }

}