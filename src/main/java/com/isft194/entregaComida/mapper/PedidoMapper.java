package com.isft194.entregaComida.mapper;

import com.isft194.entregaComida.dto.PedidoDTO;
import com.isft194.entregaComida.model.Pedido;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class PedidoMapper {


    @Autowired
    private ModelMapper modelMapper;

    public PedidoDTO toDTO(Pedido pedido) {
        return modelMapper.map(pedido, PedidoDTO.class);
    }

    public Pedido toEntity(PedidoDTO pedidoDTO) {
        return modelMapper.map(pedidoDTO, Pedido.class);
    }

}