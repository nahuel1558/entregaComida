package com.isft194.entregaComida.service;


import com.isft194.entregaComida.dto.ItemDTO;
import com.isft194.entregaComida.mapper.ItemMapper;
import com.isft194.entregaComida.model.Item;
import com.isft194.entregaComida.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ItemMapper itemMapper;

    public List<ItemDTO> findAll() {
        return itemRepository.findAll().stream()
                .map(itemMapper::toDTO)
                .collect(Collectors.toList());
    }

    public ItemDTO findById(Long id) {
        Item item = itemRepository.findById(id).orElseThrow(() -> new RuntimeException("Item no encontrado"));
        return itemMapper.toDTO(item);
    }

    public ItemDTO save(ItemDTO itemDTO) {
        Item item = itemMapper.toEntity(itemDTO);
        item = itemRepository.save(item);
        return itemMapper.toDTO(item);
    }

    public void deleteById(Long id) {
        itemRepository.deleteById(id);
    }
}