package com.securite.planning.controllers;



import com.securite.planning.models.Site;
import com.securite.planning.models.Shift;
import com.securite.planning.repositories.ShiftRepository;
import com.securite.planning.repositories.SiteRepository;
import com.securite.planning.services.PlanningService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/admin/planning")
public class PlanningController {

    private final PlanningService planningService;
    private final ShiftRepository shiftRepository;
    private final SiteRepository siteRepository;

    public PlanningController(PlanningService planningService, ShiftRepository shiftRepository, SiteRepository siteRepository) {
        this.planningService = planningService;
        this.shiftRepository = shiftRepository;
        this.siteRepository = siteRepository;
    }

    // Affiche le formulaire pour choisir la date et le site
    @GetMapping
    public String planningForm(Model model) {
        model.addAttribute("sites", siteRepository.findAll());
        return "admin/planning_form"; // à créer : formulaire Thymeleaf
    }

    // Traite la génération du planning à partir de la date et du site sélectionnés
    @PostMapping("/generer")
    public String genererPlanning(@RequestParam("dateDebut") String dateDebutStr,
                                  @RequestParam("siteId") Long siteId,
                                  Model model) {
        LocalDate lundi = LocalDate.parse(dateDebutStr);
        Site site = siteRepository.findById(siteId).orElseThrow(() -> new IllegalArgumentException("Site invalide"));

        // Génère le planning
        planningService.genererPlanningSemaine(lundi, site);

        // Récupère la liste des shifts générés pour affichage
        List<Shift> shifts = shiftRepository.findAllByDateBetween(lundi, lundi.plusDays(6));

        model.addAttribute("shifts", shifts);
        model.addAttribute("lundi", lundi);
        model.addAttribute("site", site);
        return "admin/planning_result"; // à créer : affichage du planning
    }
}
