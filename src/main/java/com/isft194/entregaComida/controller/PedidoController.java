package com.isft194.entregaComida.controller;


import com.isft194.entregaComida.dto.PedidoDTO;
import com.isft194.entregaComida.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @GetMapping("/listarPedidos")
    public List<PedidoDTO> getAllPedidos() {
        return pedidoService.findAll();
    }

    @GetMapping("/mostrarPedido/{id}")
    public ResponseEntity<PedidoDTO> getPedidoById(@PathVariable Long id) {
        return ResponseEntity.ok(pedidoService.findById(id));
    }

    @PostMapping("/crearPedido")
    public ResponseEntity<PedidoDTO> createPedido(@RequestBody PedidoDTO pedidoDTO) {
        return ResponseEntity.ok(pedidoService.save(pedidoDTO));
    }

    @DeleteMapping("/borrarPedido/{id}")
    public ResponseEntity<Void> deletePedido(@PathVariable Long id) {
        pedidoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}