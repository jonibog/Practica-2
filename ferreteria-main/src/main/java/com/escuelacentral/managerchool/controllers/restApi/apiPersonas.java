package com.escuelacentral.managerchool.controllers.restApi;

import com.escuelacentral.managerchool.models.PersonasModels;
import com.escuelacentral.managerchool.models.Producto;
import com.escuelacentral.managerchool.services.PersonasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/personas")
public class apiPersonas {
    @Autowired
    PersonasService perService;

    @GetMapping()
    public  ResponseEntity<?>devolverTodosLasPersonas(){
        List<PersonasModels> personas=perService.getPersonas();
        return ResponseEntity.ok(personas);
    }

    @PostMapping()
    public ResponseEntity<?>nuevaPersona(@RequestBody PersonasModels pM){
        return ResponseEntity.ok(perService.savePersona(pM));
    }
}
