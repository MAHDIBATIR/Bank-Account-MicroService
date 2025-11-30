package com.example.microservice.mappers;

import com.example.microservice.dto.CompteRequestDTO;
import com.example.microservice.dto.CompteResponseDTO;
import com.example.microservice.entities.Compte;
import org.springframework.stereotype.Component;

/**
 * Mapper pour convertir entre les entités Compte et les DTOs
 */
@Component
public class CompteMapper {

    /**
     * Convertit un Compte en CompteResponseDTO
     */
    public CompteResponseDTO toResponseDTO(Compte compte) {
        if (compte == null) {
            return null;
        }
        return CompteResponseDTO.builder()
                .id(compte.getId())
                .dateCreation(compte.getDateCreation())
                .solde(compte.getSolde())
                .devise(compte.getDevise())
                .type(compte.getType())
                .build();
    }

    /**
     * Convertit un CompteRequestDTO en Compte
     */
    public Compte toEntity(CompteRequestDTO requestDTO) {
        if (requestDTO == null) {
            return null;
        }
        return Compte.builder()
                .solde(requestDTO.getSolde())
                .devise(requestDTO.getDevise())
                .type(requestDTO.getType())
                .build();
    }

    /**
     * Met à jour un Compte existant avec les données d'un CompteRequestDTO
     */
    public void updateEntityFromDTO(CompteRequestDTO requestDTO, Compte compte) {
        if (requestDTO == null || compte == null) {
            return;
        }
        if (requestDTO.getSolde() != null) {
            compte.setSolde(requestDTO.getSolde());
        }
        if (requestDTO.getDevise() != null) {
            compte.setDevise(requestDTO.getDevise());
        }
        if (requestDTO.getType() != null) {
            compte.setType(requestDTO.getType());
        }
    }
}

