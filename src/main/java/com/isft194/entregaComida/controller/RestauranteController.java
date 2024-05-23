package com.isft194.entregaComida.controller;


import com.isft194.entregaComida.dto.RestauranteDTO;
import com.isft194.entregaComida.service.RestauranteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/restaurantes")
public class RestauranteController {

    @Autowired
    private RestauranteService restauranteService;

    @GetMapping("/listarRestaurantes")
    public List<RestauranteDTO> getAllRestaurantes() {
        return restauranteService.findAll();
    }

    @GetMapping("/mostrarRestaurante/{id}")
    public ResponseEntity<RestauranteDTO> getRestauranteById(@PathVariable Long id) {
        return ResponseEntity.ok(restauranteService.findById(id));
    }

    @PostMapping("/crearRestaurante")
    public ResponseEntity<RestauranteDTO> createRestaurante(@RequestBody RestauranteDTO restauranteDTO) {
        return ResponseEntity.ok(restauranteService.save(restauranteDTO));
    }

    @DeleteMapping("/borrarRestaurante/{id}")
    public ResponseEntity<Void> deleteRestaurante(@PathVariable Long id) {
        restauranteService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}