package com.securite.planning.services;



import com.securite.planning.models.Agent;
import com.securite.planning.repositories.AgentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AgentService {

    private final AgentRepository agentRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AgentService(AgentRepository agentRepository, PasswordEncoder passwordEncoder) {
        this.agentRepository = agentRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<Agent> getAllAgents() {
        return agentRepository.findAll();
    }

    public Optional<Agent> getAgentById(Long id) {
        return agentRepository.findById(id);
    }

    public Agent saveAgent(Agent agent) {
        if (agent.getPassword() != null && !agent.getPassword().isEmpty()) {
            // Encoder le mot de passe uniquement si un nouveau est fourni
            agent.setPassword(passwordEncoder.encode(agent.getPassword()));
        } else if (agent.getId() != null) {
            // Sinon conserver le mot de passe existant lors d’une modification sans nouveau mdp
            Agent existingAgent = agentRepository.findById(agent.getId()).orElse(null);
            if (existingAgent != null) {
                agent.setPassword(existingAgent.getPassword());
            }
        }
        return agentRepository.save(agent);
    }

    public void deleteAgent(Long id) {
        agentRepository.deleteById(id);
    }
}

