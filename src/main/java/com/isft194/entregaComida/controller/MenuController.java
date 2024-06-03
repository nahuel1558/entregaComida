package com.isft194.entregaComida.controller;


import com.isft194.entregaComida.dto.request.MenuRequest;
import com.isft194.entregaComida.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/menus")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @PostMapping(value = "/crearMenu", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createMenu(@RequestBody MenuRequest menuRequest) {
        return menuService.createMenu(menuRequest);
    }

    @PutMapping(value = "/actualizarMenu/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateMenu(@PathVariable("id") Long id, @RequestBody MenuRequest menuRequest) {
        return menuService.updateMenu(menuRequest, id);
    }
    @GetMapping(value = "/listarMenus", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllMenus() {
        return menuService.getAllMenus();
    }

    @GetMapping(value = "/mostrarMenu/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getMenuById(@PathVariable Long id) {
        return menuService.getMenuById(id);
    }

    @DeleteMapping(value = "/borrarMenu/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteMenu(@PathVariable Long id) {
        return menuService.deleteMenu(id);

    }


}