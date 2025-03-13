package Controlador;


import Modelo.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import repositorio.ProductoRepository;

import java.util.List;

@RestController
@RequestMapping("/productos")
@CrossOrigin(origins = "*") // Habilita CORS si el front está en otro dominio
public class ProductoController {

    @Autowired
    private ProductoRepository productoRepository;

    // Listar productos
    @GetMapping
    public List<Producto> listarProductos() {
        return productoRepository.findAll();
    }

    // Crear producto
    @PostMapping
    public Producto crearProducto(@RequestBody Producto producto) {
        return productoRepository.save(producto);
    }

    // Obtener producto por id
    @GetMapping("/{id}")
    public Producto obtenerProducto(@PathVariable Long id) {
        return productoRepository.findById(id).orElse(null);
    }

    // Eliminar producto
    @DeleteMapping("/{id}")
    public void eliminarProducto(@PathVariable Long id) {
        productoRepository.deleteById(id);
    }

}
