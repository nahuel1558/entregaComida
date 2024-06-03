package com.isft194.entregaComida.model;

import lombok.*;
import jakarta.persistence.*;

@Entity
@Table(name = "producto")
@Builder @Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Producto {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "descripcion", nullable = false)
    private String descripcion;

    @Column(name = "precio", nullable = false)
    private Float precio;
}
