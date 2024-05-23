package com.isft194.entregaComida.service;

import com.isft194.entregaComida.dto.MenuDTO;
import com.isft194.entregaComida.mapper.MenuMapper;
import com.isft194.entregaComida.model.Menu;
import com.isft194.entregaComida.repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MenuService {

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private MenuMapper menuMapper;

    public List<MenuDTO> findAll() {
        return menuRepository.findAll().stream()
                .map(menuMapper::toDTO)
                .collect(Collectors.toList());
    }

    public MenuDTO findById(Long id) {
        Menu menu = menuRepository.findById(id).orElseThrow(() -> new RuntimeException("Menu no encontrado"));
        return menuMapper.toDTO(menu);
    }

    public MenuDTO save(MenuDTO menuDTO) {
        Menu menu = menuMapper.toEntity(menuDTO);
        menu = menuRepository.save(menu);
        return menuMapper.toDTO(menu);
    }

    public void deleteById(Long id) {
        menuRepository.deleteById(id);
    }
}
