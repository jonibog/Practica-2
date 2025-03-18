package com.escuelacentral.managerchool.controllers.htmls;

import com.escuelacentral.managerchool.services.PersonasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class HomeController {
    @Autowired
    PersonasService personasService;
    /*List<String> task= Arrays.asList("Uno","Dos","Tres","Cuatro");*/
    @GetMapping("/home")
    public String home(Model model, Principal principal) {
        String email = principal.getName(); // Obtener el email del usuario autenticado


        Map<String, List<String>> rolesPermisos = personasService.obtenerRolesYPermisos(email);
        model.addAttribute("rolesPermisos", rolesPermisos);
        return "home";
    }
}
