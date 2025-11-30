package com.example.microservice.dto;

import com.example.microservice.enums.TypeCompte;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * DTO pour les réponses contenant les informations du compte
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CompteResponseDTO {
    private String id;
    private Date dateCreation;
    private Double solde;
    private String devise;
    private TypeCompte type;
}

