package com.isft194.entregaComida.service;


import com.isft194.entregaComida.dto.PedidoDTO;
import com.isft194.entregaComida.mapper.PedidoMapper;
import com.isft194.entregaComida.model.Pedido;
import com.isft194.entregaComida.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private PedidoMapper pedidoMapper;

    public List<PedidoDTO> findAll() {
        return pedidoRepository.findAll().stream()
                .map(pedidoMapper::toDTO)
                .collect(Collectors.toList());
    }

    public PedidoDTO findById(Long id) {
        Pedido pedido = pedidoRepository.findById(id).orElseThrow(() -> new RuntimeException("Pedido no encontrado"));
        return pedidoMapper.toDTO(pedido);
    }

    public PedidoDTO save(PedidoDTO pedidoDTO) {
        Pedido pedido = pedidoMapper.toEntity(pedidoDTO);
        pedido = pedidoRepository.save(pedido);
        return pedidoMapper.toDTO(pedido);
    }

    public void deleteById(Long id) {
        pedidoRepository.deleteById(id);
    }
}