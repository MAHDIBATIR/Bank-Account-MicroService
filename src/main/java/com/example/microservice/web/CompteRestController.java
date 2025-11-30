package com.example.microservice.web;

import com.example.microservice.entities.Compte;
import com.example.microservice.enums.TypeCompte;
import com.example.microservice.repositories.CompteRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api")
@Tag(name = "Gestion des Comptes", description = "API pour gérer les comptes bancaires")
public class CompteRestController {

    private CompteRepository compteRepository;

    public CompteRestController(CompteRepository compteRepository) {
        this.compteRepository = compteRepository;
    }

    @Operation(summary = "Récupérer tous les comptes", description = "Retourne la liste de tous les comptes bancaires")
    @GetMapping("/comptes")
    public List<Compte> getAllComptes() {
        return compteRepository.findAll();
    }

    @Operation(summary = "Récupérer un compte par ID", description = "Retourne un compte bancaire spécifique par son identifiant")
    @GetMapping("/comptes/{id}")
    public Compte getCompteById(
            @Parameter(description = "ID du compte à récupérer") @PathVariable String id) {
        return compteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Compte avec ID " + id + " introuvable"));
    }

    @Operation(summary = "Créer un nouveau compte", description = "Crée un nouveau compte bancaire")
    @PostMapping("/comptes")
    public Compte createCompte(@RequestBody Compte compte) {
        if (compte.getId() == null || compte.getId().isEmpty()) {
            compte.setId(UUID.randomUUID().toString());
        }
        if (compte.getDateCreation() == null) {
            compte.setDateCreation(new Date());
        }
        return compteRepository.save(compte);
    }

    @Operation(summary = "Mettre à jour un compte", description = "Met à jour les informations d'un compte existant")
    @PutMapping("/comptes/{id}")
    public Compte updateCompte(
            @Parameter(description = "ID du compte à mettre à jour") @PathVariable String id,
            @RequestBody Compte compte) {
        Compte existingCompte = compteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Compte avec ID " + id + " introuvable"));

        if (compte.getSolde() != null) {
            existingCompte.setSolde(compte.getSolde());
        }
        if (compte.getDevise() != null) {
            existingCompte.setDevise(compte.getDevise());
        }
        if (compte.getType() != null) {
            existingCompte.setType(compte.getType());
        }

        return compteRepository.save(existingCompte);
    }

    @Operation(summary = "Supprimer un compte", description = "Supprime un compte bancaire par son ID")
    @DeleteMapping("/comptes/{id}")
    public void deleteCompte(
            @Parameter(description = "ID du compte à supprimer") @PathVariable String id) {
        compteRepository.deleteById(id);
    }

    @Operation(summary = "Rechercher par type", description = "Retourne tous les comptes d'un type spécifique")
    @GetMapping("/comptes/type/{type}")
    public List<Compte> getComptesByType(
            @Parameter(description = "Type de compte (COURANT ou EPARGNE)") @PathVariable TypeCompte type) {
        return compteRepository.findByType(type);
    }

    @Operation(summary = "Rechercher par solde minimum", description = "Retourne tous les comptes avec un solde supérieur au montant spécifié")
    @GetMapping("/comptes/solde/{montant}")
    public List<Compte> getComptesBySolde(
            @Parameter(description = "Montant minimum du solde") @PathVariable Double montant) {
        return compteRepository.findBySoldeGreaterThan(montant);
    }
}

