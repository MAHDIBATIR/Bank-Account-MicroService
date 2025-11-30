package com.example.microservice.web;

import com.example.microservice.entities.Compte;
import com.example.microservice.enums.TypeCompte;
import com.example.microservice.repositories.CompteRepository;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class CompteRestController {

    private CompteRepository compteRepository;

    public CompteRestController(CompteRepository compteRepository) {
        this.compteRepository = compteRepository;
    }

    // GET - Récupérer tous les comptes
    @GetMapping("/comptes")
    public List<Compte> getAllComptes() {
        return compteRepository.findAll();
    }

    // GET - Récupérer un compte par ID
    @GetMapping("/comptes/{id}")
    public Compte getCompteById(@PathVariable String id) {
        return compteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Compte avec ID " + id + " introuvable"));
    }

    // POST - Créer un nouveau compte
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

    // PUT - Mettre à jour un compte
    @PutMapping("/comptes/{id}")
    public Compte updateCompte(@PathVariable String id, @RequestBody Compte compte) {
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

    // DELETE - Supprimer un compte
    @DeleteMapping("/comptes/{id}")
    public void deleteCompte(@PathVariable String id) {
        compteRepository.deleteById(id);
    }

    // GET - Rechercher les comptes par type
    @GetMapping("/comptes/type/{type}")
    public List<Compte> getComptesByType(@PathVariable TypeCompte type) {
        return compteRepository.findByType(type);
    }

    // GET - Rechercher les comptes avec solde supérieur à un montant
    @GetMapping("/comptes/solde/{montant}")
    public List<Compte> getComptesBySolde(@PathVariable Double montant) {
        return compteRepository.findBySoldeGreaterThan(montant);
    }
}

