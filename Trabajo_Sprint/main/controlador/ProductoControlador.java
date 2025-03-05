package main.controlador;
import main.modelo.Producto;
import main.repositorio.ProductoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
@CrossOrigin(origins = "*")
public class ProductoControlador {

    @Autowired
    private ProductoRepositorio productoRepositorio;

    @GetMapping
    public List<Producto> getAllProductos() {
        return productoRepositorio.findAll();
    }

    @PostMapping
    public List<Producto> createProducto(@RequestBody Producto producto) {
        productoRepositorio.save(producto);
        return productoRepositorio.findAll();
    }
}