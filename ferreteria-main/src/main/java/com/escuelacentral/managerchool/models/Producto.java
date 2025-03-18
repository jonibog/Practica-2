package com.escuelacentral.managerchool.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;;
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer categoriaId;
    private String nombre;
    private Integer stock;
    private Double precio;
}
