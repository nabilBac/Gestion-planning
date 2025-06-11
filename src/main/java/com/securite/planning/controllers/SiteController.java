package com.securite.planning.controllers;



import com.securite.planning.models.Site;
import com.securite.planning.services.SiteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/sites")

public class SiteController {

    private final SiteService siteService;

    public SiteController(SiteService siteService) {
        this.siteService = siteService;
    }

    @GetMapping
    public String listSites(Model model) {
        List<Site> sites = siteService.getAllSites();
        model.addAttribute("sites", sites);
        return "admin/sites/list"; // vue Thymeleaf sites/list.html
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("site", new Site());
        return "admin/sites/create";
    }

    @PostMapping("/create")
    public String createSite(@ModelAttribute Site site) {
        siteService.saveSite(site);
        return "redirect:/admin/sites";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        siteService.getSiteById(id).ifPresent(site -> model.addAttribute("site", site));
        return "admin/sites/edit";

    }

    @PostMapping("/edit/{id}")
    public String updateSite(@PathVariable Long id, @ModelAttribute Site site) {
        site.setId(id);
        siteService.saveSite(site);
        return "redirect:/admin/sites";
    }

    @GetMapping("/delete/{id}")
    public String deleteSite(@PathVariable Long id) {
        siteService.deleteSite(id);
        return "redirect:/admin/sites";
    }
}

