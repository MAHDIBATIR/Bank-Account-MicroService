package com.example.microservice.repositories;

import com.example.microservice.entities.Compte;
import com.example.microservice.enums.TypeCompte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompteRepository extends JpaRepository<Compte, String> {
    // Méthodes de recherche personnalisées
    List<Compte> findByType(TypeCompte type);
    List<Compte> findBySoldeGreaterThan(Double solde);
}

