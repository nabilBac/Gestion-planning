package com.securite.planning.controllers;



import com.securite.planning.models.Agent;
import com.securite.planning.services.AgentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/agents")
public class AgentController {

    private final AgentService agentService;

    public AgentController(AgentService agentService) {
        this.agentService = agentService;
    }

    @GetMapping
    public String listAgents(Model model) {
        List<Agent> agents = agentService.getAllAgents();
        model.addAttribute("agents", agents);
        return "admin/agents/list"; // vue Thymeleaf : agents/list.html
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("agent", new Agent());
        return "admin/agents/create"; // vue pour formulaire création agent
    }

    @PostMapping("/create")
    public String createAgent(@ModelAttribute Agent agent) {
        agentService.saveAgent(agent);
        return "redirect:/agents";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        agentService.getAgentById(id).ifPresent(agent -> model.addAttribute("agent", agent));
        return "admin/agents/edit"; // vue pour formulaire édition agent
    }

    @PostMapping("/edit/{id}")
    public String updateAgent(@PathVariable Long id, @ModelAttribute Agent agent) {
        agent.setId(id);
        agentService.saveAgent(agent);
        return "redirect:/agents";
    }

    @GetMapping("/delete/{id}")
    public String deleteAgent(@PathVariable Long id) {
        agentService.deleteAgent(id);
        return "redirect:/agents";
    }
}
