package com.escuelacentral.managerchool.controllers.restApi;

import com.escuelacentral.managerchool.models.PersonasModels;
import com.escuelacentral.managerchool.services.PersonasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = {"/api/v1/personas"})
public class apiPersonas {

    @Autowired
    PersonasService perService;

    public apiPersonas() {
    }

    @GetMapping
    public ResponseEntity<?> devolverTodosLasPersonas() {
        try {
            List<PersonasModels> personas = perService.getPersonas();
            return ResponseEntity.ok(personas);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al obtener las personas: " + e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> nuevaPersona(@RequestBody PersonasModels pM) {
        try {
            // Verificar si los roles están presentes
            if (pM.getRoles() == null || pM.getRoles().isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Los roles son obligatorios.");
            }

            // Verificar que los campos obligatorios no estén vacíos
            if (pM.getNombre() == null || pM.getApellido() == null || pM.getEmail() == null || pM.getPassword() == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Todos los campos son obligatorios.");
            }

            // Verificar si ya existe una persona con el mismo email
            if (perService.existePorEmail(pM.getEmail())) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ya existe una persona con ese email.");
            }

            // Guardar la nueva persona
            PersonasModels personaGuardada = perService.savePersona(pM);

            // Responder con el objeto guardado
            return ResponseEntity.status(HttpStatus.CREATED).body(personaGuardada);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al guardar la persona: " + e.getMessage());
        }
    }
}

