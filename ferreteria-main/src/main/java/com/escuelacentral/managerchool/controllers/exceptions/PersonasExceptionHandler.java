package com.escuelacentral.managerchool.controllers.exceptions;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class PersonasExceptionHandler {

    //Cuando sucede un error por DNI duplicado
    @ExceptionHandler(DuplicateDniException.class)
    public ResponseEntity<String> handleDuplicateDniException(DuplicateDniException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
    }
    @ExceptionHandler(DuplicateCargoException.class)
    public ResponseEntity<String> handleDuplicateCargoException(DuplicateCargoException e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleRuntimeException(RuntimeException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(registroNoExistenteExcepion.class)
    public ResponseEntity<String> handleRegistroNoExistenteExcepion(registroNoExistenteExcepion e){
        return  new ResponseEntity<>(e.getMessage()+". Desde el Exception", HttpStatus.NO_CONTENT);
    }

    @ExceptionHandler(PersonaSaveException.class)
    public String handlePersonaSaveException(PersonaSaveException ex, Model model) {
        model.addAttribute("error", ex.getMessage()); // Aquí se agrega el mensaje de error de la excepción al modelo
        return "registrar"; // Vuelve a la vista de registro con el mensaje de error
    }


}
