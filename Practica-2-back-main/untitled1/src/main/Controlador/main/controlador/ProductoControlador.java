package main.controlador;

import main.modelo.Producto;
import main.Repositorio.ProductoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
@CrossOrigin(origins = "*") // Permite solicitudes desde cualquier origen, puedes restringir esto según tus necesidades.
public class ProductoControlador {

    @Autowired
    private ProductoRepositorio productoRepositorio;

    // Obtener todos los productos
    @GetMapping
    public List<Producto> obtenerTodosLosProductos() {
        return productoRepositorio.findAll();
    }

    // Crear un nuevo producto
    @PostMapping
    public Producto crearProducto(@RequestBody Producto producto) {
        return productoRepositorio.save(producto);
    }

    // Obtener un producto por ID
    @GetMapping("/{id}")
    public ResponseEntity<Producto> obtenerProductoPorId(@PathVariable Long id) {
        Producto producto = productoRepositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con el ID: " + id));
        return ResponseEntity.ok(producto);
    }

    // Actualizar un producto
    @PutMapping("/{id}")
    public ResponseEntity<Producto> actualizarProducto(
            @PathVariable Long id,
            @RequestBody Producto detallesProducto
    ) {
        Producto producto = productoRepositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con el ID: " + id));

        producto.setNombre(detallesProducto.getNombre());
        producto.setPrecio(detallesProducto.getPrecio());
        producto.setStock(detallesProducto.getStock());

        Producto productoActualizado = productoRepositorio.save(producto);
        return ResponseEntity.ok(productoActualizado);
    }

    // Eliminar un producto
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable Long id) {
        Producto producto = productoRepositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con el ID: " + id));

        productoRepositorio.delete(producto);
        return ResponseEntity.noContent().build();
    }
}
