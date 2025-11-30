# Guide de Test Complet du Micro-service

## Démarrage de l'application

```bash
# Compiler le projet
mvn clean install

# Démarrer l'application
mvn spring-boot:run

# OU utiliser le script
./start.bat

# OU lancer le JAR directement
java -jar target/Micro-service-0.0.1-SNAPSHOT.jar
```

L'application démarre sur le port **8083**.

---

## Accès aux différentes interfaces

| Interface | URL | Description |
|-----------|-----|-------------|
| H2 Console | http://localhost:8083/h2-console | Console de la base de données H2 |
| Swagger UI | http://localhost:8083/swagger-ui.html | Documentation interactive REST API |
| GraphiQL | http://localhost:8083/graphiql | Interface GraphQL interactive |
| Spring Data Rest | http://localhost:8083/api/data | API REST générée automatiquement |
| REST API Custom | http://localhost:8083/api | API REST personnalisée |

---

## Tests à effectuer

### 1. H2 Console
- URL: http://localhost:8083/h2-console
- JDBC URL: `jdbc:h2:mem:compte-db`
- Username: `sa`
- Password: (laisser vide)
- Requêtes SQL:
  ```sql
  SELECT * FROM COMPTE;
  SELECT COUNT(*) FROM COMPTE;
  SELECT SUM(SOLDE) FROM COMPTE;
  ```

### 2. Swagger UI
- URL: http://localhost:8083/swagger-ui.html
- Tester tous les endpoints via l'interface
- Vérifier la documentation des schémas

### 3. Tests Postman - REST API Custom

#### GET tous les comptes
```
GET http://localhost:8083/api/comptes
```

#### POST créer un compte
```
POST http://localhost:8083/api/comptes
Content-Type: application/json

{
  "solde": 5000.0,
  "devise": "MAD",
  "type": "COURANT"
}
```

#### PUT mettre à jour
```
PUT http://localhost:8083/api/comptes/{id}
Content-Type: application/json

{
  "solde": 7500.0,
  "devise": "EUR",
  "type": "EPARGNE"
}
```

#### DELETE supprimer
```
DELETE http://localhost:8083/api/comptes/{id}
```

#### GET par type
```
GET http://localhost:8083/api/comptes/type/COURANT
GET http://localhost:8083/api/comptes/type/EPARGNE
```

#### GET par solde minimum
```
GET http://localhost:8083/api/comptes/solde/5000
```

### 4. Tests Spring Data Rest

#### Root endpoint
```
GET http://localhost:8083/api/data
```

#### Liste des comptes
```
GET http://localhost:8083/api/data/comptes
```

#### Avec pagination
```
GET http://localhost:8083/api/data/comptes?page=0&size=5
```

#### Avec projection soldeOnly
```
GET http://localhost:8083/api/data/comptes?projection=soldeOnly
```

#### Avec projection fullCompte
```
GET http://localhost:8083/api/data/comptes?projection=fullCompte
```

#### Méthodes de recherche
```
GET http://localhost:8083/api/data/comptes/search
GET http://localhost:8083/api/data/comptes/search/byType?type=COURANT
GET http://localhost:8083/api/data/comptes/search/bySolde?solde=5000
```

### 5. Tests GraphQL (GraphiQL)

URL: http://localhost:8083/graphiql

#### Query - Tous les comptes
```graphql
query {
  allComptes {
    id
    dateCreation
    solde
    devise
    type
  }
}
```

#### Query - Compte par ID
```graphql
query {
  compteById(id: "votre-id-ici") {
    id
    solde
    devise
    type
  }
}
```

#### Query - Comptes par type
```graphql
query {
  comptesByType(type: COURANT) {
    id
    solde
    devise
  }
}
```

#### Query - Total des soldes
```graphql
query {
  totalSoldes
}
```

#### Mutation - Créer un compte
```graphql
mutation {
  createCompte(input: {
    solde: 5000.0
    devise: "MAD"
    type: COURANT
  }) {
    id
    solde
    devise
    type
    dateCreation
  }
}
```

#### Mutation - Dépôt
```graphql
mutation {
  deposer(id: "votre-id-ici", montant: 1000.0) {
    id
    solde
  }
}
```

#### Mutation - Retrait
```graphql
mutation {
  retirer(id: "votre-id-ici", montant: 500.0) {
    id
    solde
  }
}
```

#### Mutation - Virement
```graphql
mutation {
  virement(
    idSource: "id-compte-source"
    idDestination: "id-compte-destination"
    montant: 1000.0
  )
}
```

#### Mutation - Supprimer
```graphql
mutation {
  deleteCompte(id: "votre-id-ici")
}
```

---

## Captures d'écran à prendre

### Étape 4 - Couche DAO
- [ ] Console montrant les 5 comptes créés au démarrage
- [ ] H2 Console avec la table COMPTE

### Étape 6 - Tests REST API
- [ ] GET tous les comptes
- [ ] GET un compte par ID
- [ ] POST créer un compte
- [ ] PUT mettre à jour
- [ ] DELETE supprimer
- [ ] GET par type
- [ ] GET par solde

### Étape 7 - Swagger
- [ ] Interface Swagger UI
- [ ] Détails d'un endpoint
- [ ] Exécution d'un test "Try it out"
- [ ] Réponse d'une requête
- [ ] Section Models/Schemas

### Étape 8 - Spring Data Rest
- [ ] Root endpoint (/api/data)
- [ ] Liste des comptes
- [ ] Projection soldeOnly
- [ ] Projection fullCompte
- [ ] Search endpoints
- [ ] Search byType

### Étape 11 - GraphQL
- [ ] Interface GraphiQL
- [ ] Query allComptes
- [ ] Query compteById
- [ ] Query totalSoldes
- [ ] Mutation createCompte
- [ ] Mutation deposer
- [ ] Mutation virement
- [ ] Documentation du schéma

---

## Architecture du Projet

```
Micro-service/
├── src/main/java/com/example/microservice/
│   ├── MicroServiceApplication.java          # Point d'entrée
│   ├── config/
│   │   └── OpenApiConfig.java                # Configuration Swagger
│   ├── dto/
│   │   ├── CompteRequestDTO.java             # DTO pour les requêtes
│   │   └── CompteResponseDTO.java            # DTO pour les réponses
│   ├── entities/
│   │   └── Compte.java                       # Entité JPA
│   ├── enums/
│   │   └── TypeCompte.java                   # Énumération des types
│   ├── mappers/
│   │   └── CompteMapper.java                 # Convertisseur DTO/Entity
│   ├── projections/
│   │   ├── CompteSoldeProjection.java        # Projection Spring Data Rest
│   │   └── CompteFullProjection.java         # Projection complète
│   ├── repositories/
│   │   └── CompteRepository.java             # Repository Spring Data
│   ├── service/
│   │   ├── CompteService.java                # Interface service
│   │   └── CompteServiceImpl.java            # Implémentation service
│   └── web/
│       ├── CompteRestController.java         # Contrôleur REST
│       └── CompteGraphQLController.java      # Contrôleur GraphQL
├── src/main/resources/
│   ├── application.properties                # Configuration
│   └── graphql/
│       └── schema.graphqls                   # Schéma GraphQL
└── pom.xml                                   # Dépendances Maven
```

---

## Technologies utilisées

✅ Spring Boot 3.5.6
✅ Spring Data JPA
✅ H2 Database
✅ Lombok
✅ SpringDoc OpenAPI (Swagger)
✅ Spring Data Rest
✅ Spring GraphQL
✅ Java 21

---

## Commits GitHub

Toutes les étapes ont été commitées séparément :
1. Étape 1: Création du projet avec dépendances
2. Étape 2: Entité JPA Compte
3. Étape 3: Interface CompteRepository
4. Étape 4: Test de la couche DAO
5. Étape 5: Web service RESTful
6. Étape 6: Tests Postman
7. Étape 7: Documentation Swagger
8. Étape 8: Spring Data Rest avec projections
9. Étape 9: DTOs et Mappers
10. Étape 10: Couche Service métier
11. Étape 11: Web service GraphQL

Repository: https://github.com/MAHDIBATIR/Bank-Account-MicroService.git

