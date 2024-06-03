package com.isft194.entregaComida.repository;

import com.isft194.entregaComida.model.Restaurante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestauranteRepository extends JpaRepository<Restaurante, Long> {
    boolean existsByNombre(String nombre);
}