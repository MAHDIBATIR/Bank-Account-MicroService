package com.example.microservice.projections;

import com.example.microservice.entities.Compte;
import com.example.microservice.enums.TypeCompte;
import org.springframework.data.rest.core.config.Projection;

/**
 * Projection 1 : Afficher seulement les informations de base du compte
 */
@Projection(name = "soldeOnly", types = Compte.class)
public interface CompteSoldeProjection {
    String getId();
    Double getSolde();
    String getDevise();
}

