package com.isft194.entregaComida.service;

import com.isft194.entregaComida.dto.request.MenuRequest;
import com.isft194.entregaComida.dto.response.MenuResponse;
import com.isft194.entregaComida.mapper.MenuMapper;
import com.isft194.entregaComida.model.Menu;
import com.isft194.entregaComida.repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MenuService {

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private MenuMapper menuMapper;

    public ResponseEntity<?> createMenu(MenuRequest menuRequest) {
        try {
            Menu menu = menuMapper.menuRequestToMenu(menuRequest);
            menu = menuRepository.save(menu);
            MenuResponse response = menuMapper.menuToMenuResponse(menu);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error al crear el menú: " + e.getMessage());
        }
    }

    public ResponseEntity<?> updateMenu(MenuRequest menuRequest, Long id) {
        try {
            Optional<Menu> menuOptional = menuRepository.findById(id);
            if (menuOptional.isPresent()) {
                Menu menu = menuMapper.menuRequestToMenu(menuRequest);
                menu.setId(id);
                menuRepository.save(menu);
                MenuResponse response = menuMapper.menuToMenuResponse(menu);
                return ResponseEntity.ok(response);
            } else {
                return ResponseEntity.badRequest().body("No existen menús con el id: " + id);
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error al actualizar el menú: " + e.getMessage());
        }
    }

    public ResponseEntity<?> getAllMenus() {
        try {
            List<Menu> menus = menuRepository.findAll();
            List<MenuResponse> response = menus.stream()
                    .map(menuMapper::menuToMenuResponse)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error al listar los menús: " + e.getMessage());
        }
    }

    public ResponseEntity<?> getMenuById(Long id) {
        try {
            Optional<Menu> menuOptional = menuRepository.findById(id);
            if (menuOptional.isPresent()) {
                MenuResponse response = menuMapper.menuToMenuResponse(menuOptional.get());
                return ResponseEntity.ok(response);
            } else {
                return ResponseEntity.badRequest().body("No existen menús con el id: " + id);
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error al obtener el menú: " + e.getMessage());
        }
    }

    public ResponseEntity<?> deleteMenu(Long id) {
        try {
            if (menuRepository.existsById(id)) {
                menuRepository.deleteById(id);
                return ResponseEntity.ok("Menú eliminado con id: " + id);
            } else {
                return ResponseEntity.badRequest().body("No existen menús con el id: " + id);
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error al eliminar el menú: " + e.getMessage());
        }
    }
}
