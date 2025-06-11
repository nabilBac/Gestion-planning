package com.securite.planning.converters;


import com.securite.planning.models.Agent;
import com.securite.planning.services.AgentService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class AgentConverter implements Converter<String, Agent> {

    private final AgentService agentService;

    public AgentConverter(AgentService agentService) {
        this.agentService = agentService;
    }

    @Override
    public Agent convert(String source) {
        if (source == null || source.isEmpty()) {
            return null;
        }
        try {
            Long id = Long.parseLong(source);
            return agentService.getAgentById(id).orElse(null);
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
