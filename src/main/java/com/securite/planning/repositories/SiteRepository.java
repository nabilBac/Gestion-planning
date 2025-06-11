package com.securite.planning.repositories;



import com.securite.planning.models.Site;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SiteRepository extends JpaRepository<Site, Long> {
    // Méthodes personnalisées possibles ici
}
