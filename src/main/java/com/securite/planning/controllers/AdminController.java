package com.securite.planning.controllers;



import com.securite.planning.services.AgentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {


    @Autowired
    private AgentService agentService;

    @GetMapping("/admin")
    public String adminDashboard() {
        return "admin/dashboard"; // On créera ce template Thymeleaf
    }

    @GetMapping("/admin/agents")
    public String listeAgents(Model model) {
        model.addAttribute("agents", agentService.getAllAgents());

        return "admin/agents"; // à créer
    }

}
