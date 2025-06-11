package com.securite.planning.services;


import com.securite.planning.models.Shift;
import com.securite.planning.models.StatutShift;
import com.securite.planning.repositories.ShiftRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ShiftService {

    private final ShiftRepository shiftRepository;

    public ShiftService(ShiftRepository shiftRepository) {
        this.shiftRepository = shiftRepository;
    }

    public List<Shift> getAllShifts() {
        return shiftRepository.findAll();
    }

    public Optional<Shift> getShiftById(Long id) {
        return shiftRepository.findById(id);
    }

    public Shift saveShift(Shift shift) {
        return shiftRepository.save(shift);
    }

    public void deleteShift(Long id) {
        shiftRepository.deleteById(id);
    }

    // Exemple d’une méthode personnalisée
    public List<Shift> getShiftsByAgentAndDate(Long agentId, LocalDate date) {
        return shiftRepository.findByAgentIdAndDate(agentId, date);
    }

    public List<Shift> getShiftsBySite(Long siteId) {
        return shiftRepository.findBySiteId(siteId);
    }


    public Shift validerShift(Long shiftId) {
        Optional<Shift> optShift = shiftRepository.findById(shiftId);
        if (optShift.isPresent()) {
            Shift shift = optShift.get();
            shift.setStatut(StatutShift.VALIDE);
            return shiftRepository.save(shift);
        }
        return null; // ou lancer exception si non trouvé
    }
    public List<Shift> getShiftsByStatut(StatutShift statut) {
        return shiftRepository.findByStatut(statut);
    }

}
