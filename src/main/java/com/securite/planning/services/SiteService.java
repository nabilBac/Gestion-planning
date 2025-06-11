package com.securite.planning.services;



import com.securite.planning.models.Site;
import com.securite.planning.repositories.SiteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SiteService {

    private final SiteRepository siteRepository;

    public SiteService(SiteRepository siteRepository) {
        this.siteRepository = siteRepository;
    }

    public List<Site> getAllSites() {
        return siteRepository.findAll();
    }

    public Optional<Site> getSiteById(Long id) {
        return siteRepository.findById(id);
    }

    public Site saveSite(Site site) {
        return siteRepository.save(site);
    }

    public void deleteSite(Long id) {
        siteRepository.deleteById(id);
    }
}
