package com.isft194.entregaComida.mapper;

import com.isft194.entregaComida.dto.request.ClienteRequest;
import com.isft194.entregaComida.dto.response.ClienteResponse;
import com.isft194.entregaComida.dto.response.ClientesResponse;
import com.isft194.entregaComida.model.Cliente;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClienteMapper {
    public Cliente clienteRequestToCliente(ClienteRequest clienteRequest){
        return Cliente.builder()
                .nombre(clienteRequest.getNombre())
                .direccion(clienteRequest.getDireccion())
                .correoElectronico(clienteRequest.getCorreoElectronico())
                .build();
    }
    public ClienteResponse clienteToClienteResponse(Cliente cliente){
        return ClienteResponse.builder()
                .id(cliente.getId())
                .nombre(cliente.getNombre())
                .direccion(cliente.getDireccion())
                .correoElectronico(cliente.getCorreoElectronico())
                .build();
    }

    public ClientesResponse clienteListToResponse(List<Cliente> clientes) {
        List<ClienteResponse> clienteResponseList = new ArrayList<>();

        for (Cliente cliente : clientes) {
            ClienteResponse clienteResponse = ClienteResponse.builder()
                    .id(cliente.getId())
                    .nombre(cliente.getNombre())
                    .direccion(cliente.getDireccion())
                    .correoElectronico(cliente.getCorreoElectronico())
                    .build();
            clienteResponseList.add(clienteResponse);
        }
        ClientesResponse clientesResponse = ClientesResponse.builder()
                .clientes(clienteResponseList)
                .build();

        return clientesResponse;
    }
}