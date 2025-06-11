package com.securite.planning.controllers;



import com.securite.planning.models.Shift;
import com.securite.planning.models.Agent;
import com.securite.planning.models.Site;
import com.securite.planning.models.StatutShift;
import com.securite.planning.services.ShiftService;
import com.securite.planning.services.AgentService;
import com.securite.planning.services.SiteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/shifts")

public class ShiftController {

    private final ShiftService shiftService;
    private final AgentService agentService;
    private final SiteService siteService;

    public ShiftController(ShiftService shiftService, AgentService agentService, SiteService siteService) {
        this.shiftService = shiftService;
        this.agentService = agentService;
        this.siteService = siteService;
    }

    @GetMapping
    public String listShifts(@RequestParam(required = false) StatutShift statut, Model model) {
        List<Shift> shifts;

        if (statut != null) {
            shifts = shiftService.getShiftsByStatut(statut);
        } else {
            shifts = shiftService.getAllShifts();
        }

        model.addAttribute("shifts", shifts);
        model.addAttribute("statutFilter", statut); // si tu veux afficher le filtre sélectionné
        return "admin/shifts/list";
    }


    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("shift", new Shift());
        model.addAttribute("agents", agentService.getAllAgents());
        model.addAttribute("sites", siteService.getAllSites());
        return "admin/shifts/create";
    }

    @PostMapping("/create")
    public String createShift(@ModelAttribute Shift shift) {
        shiftService.saveShift(shift);
        return "redirect:/admin/shifts";

    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        shiftService.getShiftById(id).ifPresent(shift -> {
            model.addAttribute("shift", shift);
            model.addAttribute("agents", agentService.getAllAgents());
            model.addAttribute("sites", siteService.getAllSites());
        });
        return "admin/shifts/edit";
    }

    @PostMapping("/edit/{id}")
    public String updateShift(@PathVariable Long id, @ModelAttribute Shift shift) {
        shift.setId(id);
        shiftService.saveShift(shift);
        return "redirect:/admin/shifts";

    }

    @GetMapping("/delete/{id}")
    public String deleteShift(@PathVariable Long id) {
        shiftService.deleteShift(id);
        return "redirect:/admin/shifts";

    }

    @PostMapping("/valider/{id}")
    public String validerShift(@PathVariable Long id) {
        shiftService.getShiftById(id).ifPresent(shift -> {
            shift.setStatut(StatutShift.VALIDE);
            shiftService.saveShift(shift);
        });
        return "redirect:/admin/shifts";
    }




}
