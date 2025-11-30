package com.example.microservice.service;

import com.example.microservice.dto.CompteRequestDTO;
import com.example.microservice.dto.CompteResponseDTO;

import java.util.List;

/**
 * Interface du service métier pour la gestion des comptes
 */
public interface CompteService {
    
    /**
     * Créer un nouveau compte
     */
    CompteResponseDTO createCompte(CompteRequestDTO requestDTO);
    
    /**
     * Récupérer tous les comptes
     */
    List<CompteResponseDTO> getAllComptes();
    
    /**
     * Récupérer un compte par son ID
     */
    CompteResponseDTO getCompteById(String id);
    
    /**
     * Mettre à jour un compte existant
     */
    CompteResponseDTO updateCompte(String id, CompteRequestDTO requestDTO);
    
    /**
     * Supprimer un compte
     */
    void deleteCompte(String id);
    
    /**
     * Effectuer un dépôt sur un compte
     */
    CompteResponseDTO deposer(String id, Double montant);
    
    /**
     * Effectuer un retrait sur un compte
     */
    CompteResponseDTO retirer(String id, Double montant);
    
    /**
     * Effectuer un virement entre deux comptes
     */
    void virement(String idSource, String idDestination, Double montant);
}

