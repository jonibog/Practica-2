package com.escuelacentral.managerchool.controllers.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(registroNoExistenteExcepion.class)
    public ResponseEntity<String> handleResourceNotFound(registroNoExistenteExcepion ex) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(ex.getMessage());
    }
    @ExceptionHandler(PersonaSaveException.class)
    public String handlePersonaSaveException(PersonaSaveException ex, RedirectAttributes redirectAttributes) {
        // Aquí puedes agregar un mensaje personalizado según el tipo de error
        redirectAttributes.addFlashAttribute("error", "El email ya está registrado. Por favor, intenta con otro.");
        return "redirect:/registrar";  // Redirigir de nuevo a la página de registro
    }

    @ExceptionHandler(PersonasException.EmailAlreadyExistsException.class)
    public String handleEmailAlreadyExists(PersonasException.EmailAlreadyExistsException ex, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("error", ex.getMessage());
        return "redirect:/registrar";  // Redirigir a la página de registro
    }

    @ExceptionHandler(PersonasException.DniAlreadyExistsException.class)
    public String handleDniAlreadyExists(PersonasException.DniAlreadyExistsException ex, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("error", ex.getMessage());
        return "redirect:/registrar";  // Redirigir a la página de registro
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleNotFoundException(NoHandlerFoundException ex, Model model) {
        /*model.addAttribute("error", "404");
        model.addAttribute("message", "La página que buscas no existe.");*/
        return "page_404"; // Nombre de la vista personalizada
    }
}
