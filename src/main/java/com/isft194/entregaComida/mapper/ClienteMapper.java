package com.isft194.entregaComida.mapper;


import com.isft194.entregaComida.dto.ClienteDTO;
import com.isft194.entregaComida.model.Cliente;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteMapper {

    @Autowired
    private ModelMapper modelMapper;

    public ClienteDTO toDTO(Cliente cliente) {
        return modelMapper.map(cliente, ClienteDTO.class);
    }

    public Cliente toEntity(ClienteDTO clienteDTO) {
        return modelMapper.map(clienteDTO, Cliente.class);
    }

}