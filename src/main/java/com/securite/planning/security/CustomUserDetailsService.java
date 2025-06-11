package com.securite.planning.security;



import com.securite.planning.models.Agent;
import com.securite.planning.repositories.AgentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import org.slf4j.Logger; // Import nécessaire pour la classe Logger
import org.slf4j.LoggerFactory; // Import nécessaire pour la classe LoggerFactory

@Service
public class CustomUserDetailsService implements UserDetailsService {

    // --- C'EST CETTE LIGNE QUI DÉCLARE ET INITIALISE VOTRE LOGGER ---
    // Elle utilise les imports Logger et LoggerFactory, ce qui les rendra non-grisés.
    private static final Logger logger = LoggerFactory.getLogger(CustomUserDetailsService.class);

    @Autowired
    private AgentRepository agentRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // --- UTILISATION DES LOGS POUR LE DÉBOGAGE : Ces lignes font que les imports sont utilisés ---
        logger.info("Tentative de chargement de l'agent par email : {}", email);

        Agent agent = agentRepository.findByEmail(email)
                .orElseThrow(() -> {
                    logger.warn("Agent non trouvé dans la BDD pour l'email : {}", email);
                    return new UsernameNotFoundException("Agent non trouvé: " + email);
                });

        logger.info("Agent trouvé : {}. Rôle : {}", agent.getEmail(), agent.getRole().name());
        // --- LOG CRUCIAL : Affiche le mot de passe haché lu depuis la base de données ---
        // TRÈS IMPORTANT POUR LE DÉBOGAGE, MAIS NE LAISSEZ PAS EN PRODUCTION !
        logger.info("Mot de passe haché de l'agent {} lu depuis la BDD : {}", agent.getEmail(), agent.getPassword());

        return new CustomUserDetails(agent);
    }
}