package com.isft194.entregaComida.model;


import lombok.*;
import jakarta.persistence.*;

@Entity
@Table(name = "restaurante")
@Builder @Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Restaurante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre")
    private String nombre;
}
