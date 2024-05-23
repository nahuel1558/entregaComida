package com.isft194.entregaComida.mapper;

import com.isft194.entregaComida.dto.ItemDTO;
import com.isft194.entregaComida.model.Item;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemMapper {

    @Autowired
    private ModelMapper modelMapper;

    public ItemDTO toDTO(Item item) {
        return modelMapper.map(item, ItemDTO.class);
    }

    public Item toEntity(ItemDTO itemDTO) {
        return modelMapper.map(itemDTO, Item.class);
    }

}