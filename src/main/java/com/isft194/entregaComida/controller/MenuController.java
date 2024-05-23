package com.isft194.entregaComida.controller;


import com.isft194.entregaComida.dto.MenuDTO;
import com.isft194.entregaComida.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/menus")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @GetMapping("/listarMenus")
    public List<MenuDTO> getAllMenus() {
        return menuService.findAll();
    }

    @GetMapping("/mostrarMenu/{id}")
    public ResponseEntity<MenuDTO> getMenuById(@PathVariable Long id) {
        return ResponseEntity.ok(menuService.findById(id));
    }

    @PostMapping("/crearMenu")
    public ResponseEntity<MenuDTO> createMenu(@RequestBody MenuDTO menuDTO) {
        return ResponseEntity.ok(menuService.save(menuDTO));
    }

    @DeleteMapping("/borrarMenu/{id}")
    public ResponseEntity<Void> deleteMenu(@PathVariable Long id) {
        menuService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}