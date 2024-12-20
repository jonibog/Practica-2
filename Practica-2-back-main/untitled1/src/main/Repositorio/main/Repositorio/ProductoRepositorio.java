package main.Repositorio;

import main.modelo.Producto;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProductoRepositorio extends JpaRepository<Producto, Long> {
}
