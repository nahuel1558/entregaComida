package com.isft194.entregaComida.model;

import lombok.*;
import jakarta.persistence.*;

@Entity
@Table(name = "producto")
@Builder @Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString @EqualsAndHashCode
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "precio")
    private Float precio;
}
