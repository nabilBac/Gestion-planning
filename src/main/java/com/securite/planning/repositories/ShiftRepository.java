package com.securite.planning.repositories;



import com.securite.planning.models.Shift;
import com.securite.planning.models.StatutShift;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ShiftRepository extends JpaRepository<Shift, Long> {

    // Exemple : récupérer les shifts d’un agent pour une date donnée
    List<Shift> findByAgentIdAndDate(Long agentId, LocalDate date);

    // Exemple : récupérer tous les shifts d’un site
    List<Shift> findBySiteId(Long siteId);
    List<Shift> findAllByDateBetween(LocalDate startDate, LocalDate endDate);

    void deleteAllByDateBetween(LocalDate startDate, LocalDate endDate);

    List<Shift> findByStatut(StatutShift statut);

}
