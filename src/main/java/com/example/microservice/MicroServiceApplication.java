package com.example.microservice;

import com.example.microservice.entities.Compte;
import com.example.microservice.enums.TypeCompte;
import com.example.microservice.repositories.CompteRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.UUID;

@SpringBootApplication
public class MicroServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner start(CompteRepository compteRepository) {
        return args -> {
            // Création de comptes de test
            for (int i = 0; i < 5; i++) {
                Compte compte = Compte.builder()
                        .id(UUID.randomUUID().toString())
                        .dateCreation(new Date())
                        .solde(1000 + Math.random() * 9000)
                        .devise("MAD")
                        .type(Math.random() > 0.5 ? TypeCompte.COURANT : TypeCompte.EPARGNE)
                        .build();
                compteRepository.save(compte);
            }

            // Affichage des comptes créés
            System.out.println("=== Liste de tous les comptes ===");
            compteRepository.findAll().forEach(c -> {
                System.out.println("ID: " + c.getId() + ", Solde: " + c.getSolde() +
                                 ", Type: " + c.getType() + ", Devise: " + c.getDevise());
            });
        };
    }
}

