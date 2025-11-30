package com.example.microservice.service;

import com.example.microservice.dto.CompteRequestDTO;
import com.example.microservice.dto.CompteResponseDTO;
import com.example.microservice.entities.Compte;
import com.example.microservice.mappers.CompteMapper;
import com.example.microservice.repositories.CompteRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Implémentation du service métier pour la gestion des comptes
 */
@Service
@Transactional
public class CompteServiceImpl implements CompteService {

    private final CompteRepository compteRepository;
    private final CompteMapper compteMapper;

    public CompteServiceImpl(CompteRepository compteRepository, CompteMapper compteMapper) {
        this.compteRepository = compteRepository;
        this.compteMapper = compteMapper;
    }

    @Override
    public CompteResponseDTO createCompte(CompteRequestDTO requestDTO) {
        Compte compte = compteMapper.toEntity(requestDTO);
        compte.setId(UUID.randomUUID().toString());
        compte.setDateCreation(new Date());

        Compte savedCompte = compteRepository.save(compte);
        return compteMapper.toResponseDTO(savedCompte);
    }

    @Override
    public List<CompteResponseDTO> getAllComptes() {
        return compteRepository.findAll()
                .stream()
                .map(compteMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CompteResponseDTO getCompteById(String id) {
        Compte compte = compteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Compte avec ID " + id + " introuvable"));
        return compteMapper.toResponseDTO(compte);
    }

    @Override
    public CompteResponseDTO updateCompte(String id, CompteRequestDTO requestDTO) {
        Compte compte = compteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Compte avec ID " + id + " introuvable"));

        compteMapper.updateEntityFromDTO(requestDTO, compte);
        Compte updatedCompte = compteRepository.save(compte);
        return compteMapper.toResponseDTO(updatedCompte);
    }

    @Override
    public void deleteCompte(String id) {
        if (!compteRepository.existsById(id)) {
            throw new RuntimeException("Compte avec ID " + id + " introuvable");
        }
        compteRepository.deleteById(id);
    }

    @Override
    public CompteResponseDTO deposer(String id, Double montant) {
        if (montant <= 0) {
            throw new RuntimeException("Le montant du dépôt doit être positif");
        }

        Compte compte = compteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Compte avec ID " + id + " introuvable"));

        compte.setSolde(compte.getSolde() + montant);
        Compte updatedCompte = compteRepository.save(compte);
        return compteMapper.toResponseDTO(updatedCompte);
    }

    @Override
    public CompteResponseDTO retirer(String id, Double montant) {
        if (montant <= 0) {
            throw new RuntimeException("Le montant du retrait doit être positif");
        }

        Compte compte = compteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Compte avec ID " + id + " introuvable"));

        if (compte.getSolde() < montant) {
            throw new RuntimeException("Solde insuffisant pour effectuer le retrait");
        }

        compte.setSolde(compte.getSolde() - montant);
        Compte updatedCompte = compteRepository.save(compte);
        return compteMapper.toResponseDTO(updatedCompte);
    }

    @Override
    public void virement(String idSource, String idDestination, Double montant) {
        if (montant <= 0) {
            throw new RuntimeException("Le montant du virement doit être positif");
        }

        Compte compteSource = compteRepository.findById(idSource)
                .orElseThrow(() -> new RuntimeException("Compte source avec ID " + idSource + " introuvable"));

        Compte compteDestination = compteRepository.findById(idDestination)
                .orElseThrow(() -> new RuntimeException("Compte destination avec ID " + idDestination + " introuvable"));

        if (compteSource.getSolde() < montant) {
            throw new RuntimeException("Solde insuffisant pour effectuer le virement");
        }

        // Débiter le compte source
        compteSource.setSolde(compteSource.getSolde() - montant);
        compteRepository.save(compteSource);

        // Créditer le compte destination
        compteDestination.setSolde(compteDestination.getSolde() + montant);
        compteRepository.save(compteDestination);
    }
}

