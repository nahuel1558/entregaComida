package com.isft194.entregaComida.controller;


import com.isft194.entregaComida.dto.request.RestauranteRequest;
import com.isft194.entregaComida.service.RestauranteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/restaurantes")
public class RestauranteController {

    @Autowired
    private RestauranteService restauranteService;

    @PostMapping(value = "/crearRestaurante", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createRestaurante(@RequestBody RestauranteRequest restauranteRequest) {
        return restauranteService.createRestaurante(restauranteRequest);
    }
    @PutMapping(value = "/actualizarRestaurante/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updatePedido(@PathVariable("id") Long id, @RequestBody RestauranteRequest restauranteRequest) {
        return restauranteService.updateRestaurante(restauranteRequest, id);
    }
    @GetMapping(value = "/listarRestaurantes", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllRestaurantes() {
        return restauranteService.getAllRestaurantes();
    }

    @GetMapping(value = "/mostrarRestaurante/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getRestauranteById(@PathVariable Long id) {
        return restauranteService.getRestauranteById(id);
    }


    @DeleteMapping(value = "/borrarRestaurante/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteRestaurante(@PathVariable Long id) {
        return  restauranteService.deleteRestaurante(id);
    }


}