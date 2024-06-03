package com.isft194.entregaComida.controller;


import com.isft194.entregaComida.dto.request.ClienteRequest;
import com.isft194.entregaComida.dto.response.ClientesResponse;
import com.isft194.entregaComida.model.Cliente;
import com.isft194.entregaComida.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path = "/api/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @PostMapping(value = "/crearCliente", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createCliente(@RequestBody ClienteRequest clienteRequest) {
        return clienteService.newCliente(clienteRequest);
    }

    @PutMapping(value = "/actualizarCliente/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateCliente(@PathVariable("id") Long id, @RequestBody ClienteRequest clienteRequest) {
        return clienteService.updateCliente(clienteRequest, id);
    }
    @GetMapping(value = "/listarClientes", produces = MediaType.APPLICATION_JSON_VALUE)
    public ClientesResponse getAllClientes() {
        return clienteService.listarClientes();
    }

    @GetMapping(value = "/mostrarCliente/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Optional<Cliente> findClienteById(@PathVariable Long id) {
        return clienteService.findClienteById(id);
    }


    @DeleteMapping(value = "/borrarCliente/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteCliente(@PathVariable Long id) {
        return clienteService.deleteCliente(id);
    }
}