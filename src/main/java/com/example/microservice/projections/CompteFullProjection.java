package com.example.microservice.projections;

import com.example.microservice.entities.Compte;
import com.example.microservice.enums.TypeCompte;
import org.springframework.data.rest.core.config.Projection;

import java.util.Date;

/**
 * Projection 2 : Afficher toutes les informations du compte
 */
@Projection(name = "fullCompte", types = Compte.class)
public interface CompteFullProjection {
    String getId();
    Date getDateCreation();
    Double getSolde();
    String getDevise();
    TypeCompte getType();
}

