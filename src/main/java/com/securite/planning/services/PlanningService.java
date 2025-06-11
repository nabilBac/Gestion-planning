package com.securite.planning.services;



import com.securite.planning.models.Agent;
import com.securite.planning.models.Shift;
import com.securite.planning.models.Site;
import com.securite.planning.models.StatutShift;
import com.securite.planning.repositories.ShiftRepository;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.*;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PlanningService {

    private final ShiftRepository shiftRepository;
    private final AgentService agentService;

    public PlanningService(ShiftRepository shiftRepository, AgentService agentService) {
        this.shiftRepository = shiftRepository;
        this.agentService = agentService;
    }
    @Transactional
    public void genererPlanningSemaine(LocalDate lundi, Site site) {
        List<Agent> agents = agentService.getAllAgents();

        // On nettoie le planning de la semaine (optionnel, à adapter)
        shiftRepository.deleteAllByDateBetween(lundi, lundi.plusDays(6));

        // On génère pour chaque jour de la semaine
        for (int i = 0; i < 7; i++) {
            LocalDate date = lundi.plusDays(i);
            DayOfWeek jour = date.getDayOfWeek();

            // Vendredi et samedi : tous présents
            if (jour == DayOfWeek.FRIDAY || jour == DayOfWeek.SATURDAY) {
                for (Agent agent : agents) {
                    creerShift(agent, site, date);
                }
            } else if (jour.getValue() >= DayOfWeek.MONDAY.getValue() && jour.getValue() <= DayOfWeek.THURSDAY.getValue()) {
                // Lundi à jeudi : au moins 2 agents présents, chacun a 2 jours de repos
                // Simplification : rotation des repos par agent
                List<Agent> agentsTravaillant = agentsDisponiblesPourJour(agents, date);

                for (Agent agent : agentsTravaillant) {
                    creerShift(agent, site, date);
                }
            }
            // Dimanche : repos pour tout le monde (ou selon besoin)
        }
    }

    private void creerShift(Agent agent, Site site, LocalDate date) {
        Shift shift = new Shift();
        shift.setAgent(agent);
        shift.setSite(site);
        shift.setDate(date);
        // Exemple d'heures fixes, tu peux adapter ou rendre dynamique
        shift.setHeureDebut(java.time.LocalTime.of(8, 0));
        shift.setHeureFin(java.time.LocalTime.of(18, 0));
        shift.setStatut(StatutShift.PREVISIONNEL);
        shiftRepository.save(shift);
    }

    private List<Agent> agentsDisponiblesPourJour(List<Agent> agents, LocalDate date) {
        // Exemple très simple : chaque agent a deux jours consécutifs de repos
        // Ici on définit la logique pour exclure certains agents par jour

        List<Agent> disponibles = new ArrayList<>();

        for (int i = 0; i < agents.size(); i++) {
            Agent agent = agents.get(i);

            // Exemple : agent i repose le lundi+mardi, agent i+1 repose mercredi+jeudi, etc
            int reposStartDay = (i * 2) % 4; // 0 ou 2 (lundi=0, mardi=1...)

            int dayIndex = date.getDayOfWeek().getValue() - 1; // lundi=0 ... dimanche=6

            if (!(dayIndex == reposStartDay || dayIndex == reposStartDay + 1)) {
                disponibles.add(agent);
            }
        }

        // Assurer au moins 2 agents, si moins, on rajoute le premier agent disponible
        if (disponibles.size() < 2 && !agents.isEmpty()) {
            disponibles.add(agents.get(0));
        }

        return disponibles;
    }
}
