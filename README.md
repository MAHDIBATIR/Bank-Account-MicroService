# Micro-service de gestion des comptes bancaires

## Description
Micro-service Spring Boot pour la gestion des comptes bancaires.

## Technologies utilisées
- Spring Boot 3.5.6
- Spring Data JPA
- H2 Database
- Lombok
- Java 21

## Installation et exécution
```bash
mvn clean install
mvn spring-boot:run
```

## Configuration
- Port: 8083
- H2 Console: http://localhost:8083/h2-console
- API Base URL: http://localhost:8083/api

## Accès à H2 Console
1. Démarrer l'application
2. Ouvrir http://localhost:8083/h2-console dans votre navigateur
3. Configurer la connexion :
   - JDBC URL: `jdbc:h2:mem:compte-db`
   - Username: `sa`
   - Password: (laisser vide)
4. Cliquer sur "Connect"

## Endpoints REST disponibles

### GET - Récupérer tous les comptes
```
GET http://localhost:8083/api/comptes
```

### POST - Créer un nouveau compte
```
POST http://localhost:8083/api/comptes
Content-Type: application/json

{
  "solde": 5000.0,
  "devise": "MAD",
  "type": "COURANT"
}
```

### GET - Récupérer un compte par ID
```
GET http://localhost:8083/api/comptes/{id}
```

### PUT - Mettre à jour un compte
```
PUT http://localhost:8083/api/comptes/{id}
```

### DELETE - Supprimer un compte
```
DELETE http://localhost:8083/api/comptes/{id}
```

Voir [TESTS_POSTMAN.md](TESTS_POSTMAN.md) pour plus de détails sur tous les endpoints.

