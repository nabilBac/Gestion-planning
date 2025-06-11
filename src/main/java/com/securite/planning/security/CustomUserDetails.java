package com.securite.planning.security;

import com.securite.planning.models.Agent;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class CustomUserDetails implements UserDetails {

    private final Agent agent;

    public CustomUserDetails(Agent agent) {
        this.agent = agent;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(agent.getRole().name()));

    }

    @Override
    public String getPassword() {
        return agent.getPassword(); // il faut ajouter un champ password dans Agent
    }

    @Override
    public String getUsername() {
        return agent.getEmail(); // ou login
    }

    @Override public boolean isAccountNonExpired() { return true; }
    @Override public boolean isAccountNonLocked() { return true; }
    @Override public boolean isCredentialsNonExpired() { return true; }
    @Override public boolean isEnabled() { return true; }
}
