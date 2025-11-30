package com.example.microservice.web;

import com.example.microservice.dto.CompteRequestDTO;
import com.example.microservice.dto.CompteResponseDTO;
import com.example.microservice.entities.Compte;
import com.example.microservice.enums.TypeCompte;
import com.example.microservice.repositories.CompteRepository;
import com.example.microservice.service.CompteService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * Contrôleur GraphQL pour la gestion des comptes bancaires
 */
@Controller
public class CompteGraphQLController {

    private final CompteRepository compteRepository;
    private final CompteService compteService;

    public CompteGraphQLController(CompteRepository compteRepository, CompteService compteService) {
        this.compteRepository = compteRepository;
        this.compteService = compteService;
    }

    // ===== QUERIES =====

    @QueryMapping
    public List<Compte> allComptes() {
        return compteRepository.findAll();
    }

    @QueryMapping
    public Compte compteById(@Argument String id) {
        return compteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Compte avec ID " + id + " introuvable"));
    }

    @QueryMapping
    public List<Compte> comptesByType(@Argument TypeCompte type) {
        return compteRepository.findByType(type);
    }

    @QueryMapping
    public Double totalSoldes() {
        return compteRepository.findAll()
                .stream()
                .mapToDouble(Compte::getSolde)
                .sum();
    }

    // ===== MUTATIONS =====

    @MutationMapping
    public Compte createCompte(@Argument CompteRequestDTO input) {
        CompteResponseDTO responseDTO = compteService.createCompte(input);
        return compteRepository.findById(responseDTO.getId()).orElseThrow();
    }

    @MutationMapping
    public Compte updateCompte(@Argument String id, @Argument CompteRequestDTO input) {
        CompteResponseDTO responseDTO = compteService.updateCompte(id, input);
        return compteRepository.findById(responseDTO.getId()).orElseThrow();
    }

    @MutationMapping
    public Boolean deleteCompte(@Argument String id) {
        try {
            compteService.deleteCompte(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @MutationMapping
    public Compte deposer(@Argument String id, @Argument Double montant) {
        CompteResponseDTO responseDTO = compteService.deposer(id, montant);
        return compteRepository.findById(responseDTO.getId()).orElseThrow();
    }

    @MutationMapping
    public Compte retirer(@Argument String id, @Argument Double montant) {
        CompteResponseDTO responseDTO = compteService.retirer(id, montant);
        return compteRepository.findById(responseDTO.getId()).orElseThrow();
    }

    @MutationMapping
    public Boolean virement(@Argument String idSource, @Argument String idDestination, @Argument Double montant) {
        try {
            compteService.virement(idSource, idDestination, montant);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}

