package com.example.microservice.entities;

import com.example.microservice.enums.TypeCompte;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Compte {
    @Id
    private String id;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreation;

    private Double solde;

    private String devise;

    @Enumerated(EnumType.STRING)
    private TypeCompte type;
}

