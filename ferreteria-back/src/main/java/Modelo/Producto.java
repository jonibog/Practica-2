package Modelo;

import jakarta.persistence.*;

@Entity
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer categoriaId;
    private String nombre;
    private Integer stock;
    private Double precio;

    public Producto() {}

    public Producto(Integer categoriaId, String nombre, Integer stock, Double precio) {
        this.categoriaId = categoriaId;
        this.nombre = nombre;
        this.stock = stock;
        this.precio = precio;
    }

    public Long getId() { return id; }

    public Integer getCategoriaId() { return categoriaId; }
    public void setCategoriaId(Integer categoriaId) { this.categoriaId = categoriaId; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public Integer getStock() { return stock; }
    public void setStock(Integer stock) { this.stock = stock; }

    public Double getPrecio() { return precio; }
    public void setPrecio(Double precio) { this.precio = precio; }
}
