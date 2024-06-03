package com.isft194.entregaComida.controller;


import com.isft194.entregaComida.dto.request.PedidoRequest;
import com.isft194.entregaComida.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @PostMapping(value = "/crearPedido", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createPedido(@RequestBody PedidoRequest pedidoRequest) {
        return pedidoService.createPedido(pedidoRequest);
    }
    @PutMapping(value = "/actualizarPedido/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updatePedido(@PathVariable("id") Long id, @RequestBody PedidoRequest pedidoRequest) {
        return pedidoService.updatePedido(pedidoRequest, id);
    }

    @GetMapping(value = "/listarPedidos", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllPedidos() {
        return pedidoService.getAllPedidos();
    }

    @GetMapping(value = "/mostrarPedido/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findPedidoById(@PathVariable Long id) {
        return pedidoService.getPedidoById(id);
    }


    @DeleteMapping(value = "/borrarPedido/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deletePedido(@PathVariable Long id) {
        return pedidoService.deletePedido(id);
    }

}