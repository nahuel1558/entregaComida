package com.isft194.entregaComida.model;

import lombok.*;
import jakarta.persistence.*;

@Entity
@Table(name = "item")
@Builder @Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "producto_id", nullable = false)
    private Producto producto;

    @Column(name = "cantidad")
    private Integer cantidad;

    @Column(name = "precio")
    private Float precio;
}
