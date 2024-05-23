package com.isft194.entregaComida.mapper;


import com.isft194.entregaComida.dto.MenuDTO;
import com.isft194.entregaComida.model.Menu;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class MenuMapper {


    @Autowired
    private ModelMapper modelMapper;

    public MenuDTO toDTO(Menu menu) {
        return modelMapper.map(menu, MenuDTO.class);
    }

    public Menu toEntity(MenuDTO menuDTO) {
        return modelMapper.map(menuDTO, Menu.class);
    }

}