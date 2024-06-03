package com.isft194.entregaComida.service;

import com.isft194.entregaComida.dto.request.ClienteRequest;
import com.isft194.entregaComida.dto.response.ClienteResponse;
import com.isft194.entregaComida.dto.response.ClientesResponse;
import com.isft194.entregaComida.mapper.ClienteMapper;
import com.isft194.entregaComida.model.Cliente;
import com.isft194.entregaComida.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ClienteMapper clienteMapper;

    public ResponseEntity<?> newCliente(ClienteRequest clienteRequest) {
        try {
            if (camposRequeridosValidos(clienteRequest)) {
                if (!clienteRepository.existsByCorreoElectronico(clienteRequest.getCorreoElectronico())) {
                    Cliente cliente = clienteRequestToNewCliente(clienteRequest);
                    saveCliente(cliente);
                    ClienteResponse response = clienteMapper.clienteToClienteResponse(cliente);
                    return ResponseEntity.ok(response);
                } else {
                    return ResponseEntity.badRequest().body("El cliente con ese correo electronico ya existe");
                }
            } else {
                return ResponseEntity.badRequest().body("Nombre, Direccion y Correo Electronico son campos obligatorios.");
            }
        } catch (Exception exception){
            return ResponseEntity.status(500).body("Error al crear el cliente: " + exception.getMessage());
        }
    }

    public ResponseEntity<?> updateCliente(ClienteRequest clienteRequest, Long id) {
        try {
            Optional<Cliente> productoOptional = clienteRepository.findById(id);
            if (productoOptional.isPresent()){
                Cliente cliente = clienteRequestToExistedCliente(clienteRequest);
                cliente.setId(id);
                clienteRepository.save(cliente);
                return ResponseEntity.ok("Cliente actualizado:\n " + cliente.getNombre() + "\n " + cliente.getDireccion() + "\n " + cliente.getCorreoElectronico());
            } else{
                return ResponseEntity.badRequest().body("No existen clientes con el id: " + id);
            }
        } catch (Exception exception){
            return ResponseEntity.status(500).body("Error al actualizar el cliente: " + exception.getMessage());
        }
    }


    public void saveCliente(Cliente cliente){
        clienteRepository.save(cliente);
    }
    public Cliente clienteRequestToNewCliente(ClienteRequest clienteRequest) {
        return clienteMapper.clienteRequestToCliente(clienteRequest);
    }
    public Cliente clienteRequestToExistedCliente(ClienteRequest clienteRequest) {
        return clienteMapper.clienteRequestToCliente(clienteRequest);
    }

    public ClientesResponse listarClientes() {
        List<Cliente> listaClientes = clienteRepository.findAll();
        return clienteMapper.clienteListToResponse(listaClientes);
    }

    public Optional<Cliente> findClienteById(Long id) {
        return clienteRepository.findById(id);
    }

    public Cliente save(Cliente producto) {
        return clienteRepository.save(producto);
    }

    public ResponseEntity<?> deleteCliente(Long id) {
        try {
            if (clienteRepository.existsById(id)) {
                clienteRepository.deleteById(id);
                return ResponseEntity.ok("Cliente eliminado con id: " + id);
            } else {
                return ResponseEntity.badRequest().body("No existen clientes con el id: " + id);
            }
        }catch(Exception exception){
            return ResponseEntity.status(500).body("Error al eliminar el cliente: " + exception.getMessage());
        }
    }
    private boolean camposRequeridosValidos(ClienteRequest clienteRequest) {
        return clienteRequest.getNombre() != null && !clienteRequest.getNombre().isEmpty() &&
                clienteRequest.getDireccion() != null && !clienteRequest.getDireccion().isEmpty() &&
                clienteRequest.getCorreoElectronico() != null&& !clienteRequest.getCorreoElectronico().isEmpty();
    }

}