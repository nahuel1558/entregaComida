package com.isft194.entregaComida.service;


import com.isft194.entregaComida.dto.request.RestauranteRequest;
import com.isft194.entregaComida.dto.response.PedidoResponse;
import com.isft194.entregaComida.dto.response.RestauranteResponse;
import com.isft194.entregaComida.dto.response.RestaurantesResponse;
import com.isft194.entregaComida.mapper.RestauranteMapper;
import com.isft194.entregaComida.model.Restaurante;
import com.isft194.entregaComida.repository.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RestauranteService {

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Autowired
    private RestauranteMapper restauranteMapper;
    public ResponseEntity<?> createRestaurante(RestauranteRequest restauranteRequest) {
        try {
            Restaurante restaurante = restauranteMapper.restauranteRequestToRestaurante(restauranteRequest);
            restaurante = restauranteRepository.save(restaurante);
            RestauranteResponse response = restauranteMapper.restauranteToRestauranteResponse(restaurante);
            return ResponseEntity.ok(response);
        } catch (Exception exception){
            return ResponseEntity.status(500).body("Error al crear el restaurante: " + exception.getMessage());
        }
    }

    public ResponseEntity<?> updateRestaurante(RestauranteRequest restauranteRequest, Long id) {
        try {
            Optional<Restaurante> productoOptional = restauranteRepository.findById(id);
            if (productoOptional.isPresent()){
                Restaurante restaurante = productoRequestToExistedProducto(restauranteRequest);
                restaurante.setId(id);
                restauranteRepository.save(restaurante);
                return ResponseEntity.ok("Restaurante actualizado:\n " + restaurante.getNombre());
            } else{
                return ResponseEntity.badRequest().body("No existen restaurantes con el id: " + id);
            }
        } catch (Exception exception){
            return ResponseEntity.status(500).body("Error al actualizar el restaurante: " + exception.getMessage());
        }
    }
    public ResponseEntity<?> getAllRestaurantes(){
        try{
            List<Restaurante> restaurantes = restauranteRepository.findAll();
            RestaurantesResponse response = restauranteMapper.restauranteListToResponse(restaurantes);
            return ResponseEntity.ok(response);
        }catch (Exception exception){
            return ResponseEntity.status(500).body("Error al listar los restaurantes: " + exception.getMessage());
        }
    }

    public ResponseEntity<?> getRestauranteById(Long id){
        try{
            Optional<Restaurante> restauranteOptional = restauranteRepository.findById(id);
            if(restauranteOptional.isPresent()){
                RestauranteResponse response = restauranteMapper.restauranteToRestauranteResponse(restauranteOptional.get());
                return ResponseEntity.ok(response);
            } else {
                return ResponseEntity.badRequest().body("no existen restaurantes con el id: " + id);
            }
        } catch (Exception exception){
            return ResponseEntity.status(500).body("Error al obtener el restaurante: " + exception.getMessage());
        }
    }

    public ResponseEntity<?> deleteRestaurante(Long id) {
        try {
            if (restauranteRepository.existsById(id)) {
                restauranteRepository.deleteById(id);
                return ResponseEntity.ok("Restaurante eliminado con id: " + id);
            } else {
                return ResponseEntity.badRequest().body("No existen restaurantes con el id: " + id);
            }
        }catch(Exception exception){
            return ResponseEntity.status(500).body("Error al eliminar el restaurante: " + exception.getMessage());
        }
    }

    public Restaurante productoRequestToExistedProducto(RestauranteRequest restauranteRequest) {
        return restauranteMapper.restauranteRequestToRestaurante(restauranteRequest);
    }



    public Optional<Restaurante> findRestauranteById(Long id) {
        return restauranteRepository.findById(id);
    }

    public Restaurante save(Restaurante producto) {
        return restauranteRepository.save(producto);
    }

    private boolean camposRequeridosValidos(RestauranteRequest restauranteRequest) {
        return restauranteRequest.getNombre() != null && !restauranteRequest.getNombre().isEmpty();
    }
}