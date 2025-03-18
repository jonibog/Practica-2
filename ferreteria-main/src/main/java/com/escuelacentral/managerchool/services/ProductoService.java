package com.escuelacentral.managerchool.services;

import com.escuelacentral.managerchool.models.Producto;
import com.escuelacentral.managerchool.repositories.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductoService {
    @Autowired
    ProductoRepository productoRepository;

    //Devolver
    //Uno en espesifico

    //Devovler Todos los Productos
    @Transactional(readOnly = true)
    public List<Producto> devolverTodosProductos(){
        return productoRepository.findAll();
    }
    //Editar un Producto
    public Producto modificarProdcuto (Producto pbase){
        return productoRepository.save(pbase);
    }
    //Crear nuevo prodcutor

    //Eliminar un producto

}
