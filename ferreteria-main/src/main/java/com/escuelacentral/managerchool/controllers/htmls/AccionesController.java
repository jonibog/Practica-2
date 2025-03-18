package com.escuelacentral.managerchool.controllers.htmls;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class AccionesController {
    @GetMapping("/acciones/{permiso}")
    public String realizarAccion(@PathVariable String permiso, Model model) {
        switch (permiso) {
            case "Registrar Usuario":
                return "redirect:/registrar";

            default:
                // Manejo de permisos desconocidos
                model.addAttribute("error", "Permiso no válido");
                return "redirect:/home";
        }
    }
}
