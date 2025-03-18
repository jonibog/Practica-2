package com.escuelacentral.managerchool.controllers.restApi;

import com.escuelacentral.managerchool.models.Producto;
import com.escuelacentral.managerchool.services.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/productos")
public class apiProductos {
    @Autowired
    ProductoService pS;

    @GetMapping()
    public ResponseEntity<?>devolverTodosLosProductos(){
        List<Producto> prod=pS.devolverTodosProductos();
        return ResponseEntity.ok(prod);
    }
    @PutMapping()
    public ResponseEntity<?>editarProducto(@RequestBody Producto prod){
        return ResponseEntity.ok(pS.modificarProdcuto(prod));
    }
}
