package com.example.microservice.repositories;

import com.example.microservice.entities.Compte;
import com.example.microservice.enums.TypeCompte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;

@RepositoryRestResource(path = "comptes")
public interface CompteRepository extends JpaRepository<Compte, String> {
    // Méthodes de recherche personnalisées avec Spring Data Rest
    @RestResource(path = "byType")
    List<Compte> findByType(@Param("type") TypeCompte type);

    @RestResource(path = "bySolde")
    List<Compte> findBySoldeGreaterThan(@Param("solde") Double solde);
}

