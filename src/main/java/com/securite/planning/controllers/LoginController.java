package com.securite.planning.controllers;



import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login(Principal principal) {
        if (principal != null) {
            // Si déjà connecté, on redirige vers /home pour éviter boucle
            return "redirect:/home";
        }
        // Sinon, on affiche la page de login
        return "login";
    }
}
