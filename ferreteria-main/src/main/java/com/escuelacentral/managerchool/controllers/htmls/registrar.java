package com.escuelacentral.managerchool.controllers.htmls;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class registrar {

    @GetMapping("/registrar")
    public String getArmadoDeCarrera(Model model, Principal principal){
        model.addAttribute("tipo", "Usuario");
        return "registrar";
    }
}
