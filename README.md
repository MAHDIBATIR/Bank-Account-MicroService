# Micro-service de Gestion des Comptes Bancaires

## Description
Micro-service Spring Boot pour la gestion des comptes bancaires avec support REST API, Spring Data REST et GraphQL.

## Technologies utilisées
- Spring Boot 3.5.6
- Spring Data JPA
- H2 Database
- Lombok 1.18.38
- SpringDoc OpenAPI 2.5.0
- Spring GraphQL
- Java 21

## Installation et exécution

```bash
mvn clean install
mvn spring-boot:run
```

Ou utiliser le script :
```bash
./start.bat
```

## Configuration

**Port**: 8083

### URLs d'accès
- **H2 Console**: http://localhost:8083/h2-console
- **API REST**: http://localhost:8083/api
- **Spring Data REST**: http://localhost:8083/api/data
- **Swagger UI**: http://localhost:8083/swagger-ui.html
- **GraphiQL**: http://localhost:8083/graphiql

### Connexion H2
- **JDBC URL**: `jdbc:h2:mem:compte-db`
- **Username**: `sa`
- **Password**: (vide)

---

## Démonstrations

### 1. Configuration du Projet

![Configuration du projet](screens/Project%20configuration.png)

Configuration initiale du projet Spring Boot avec toutes les dépendances nécessaires.

---

### 2. Test de la couche DAO

![Test DAO](screens/test%20de%20DAO.png)

Initialisation automatique de la base de données avec des comptes de test.

---

### 3. Accès à la Base de Données H2

![H2 Console](screens/H2%20base%20comptes.png)

Visualisation des données dans la console H2.

---

### 4. Tests Postman - API REST

#### GET - Tous les comptes

![Postman GET](screens/postman%20Get%20comptes.png)

Récupération de tous les comptes bancaires.

---

#### GET - Compte par ID

![Postman GET by ID](screens/postman%20get%20with%20ID.png)

Récupération d'un compte spécifique.

---

#### GET - Comptes par type

![Postman GET by type](screens/postman%20get%20by%20type%20.png)

Recherche des comptes par type (COURANT/EPARGNE).

---

#### GET - Comptes par solde

![Postman GET by solde](screens/Postman%20GET%20by%20sold.png)

Recherche des comptes avec un solde minimum.

---

#### POST - Créer un compte

![Postman POST](screens/Postman%20post%20comptes.png)

Création d'un nouveau compte bancaire.

---

#### PUT - Mettre à jour un compte

![Postman PUT](screens/postman%20update%20with%20PUT.png)

Mise à jour des informations d'un compte.

---

#### DELETE - Supprimer un compte

![Postman DELETE](screens/postman%20Delet.png)

Suppression d'un compte bancaire.

---

### 5. Documentation Swagger

#### Interface Swagger UI

![Swagger Interface](screens/swagger%20interface.png)

Interface de documentation interactive Swagger.

---

#### Swagger - GET par ID

![Swagger GET by ID](screens/swagger%20get%20by%20id.png)

Test d'un endpoint depuis Swagger.

---

#### Swagger - POST Créer compte

![Swagger POST](screens/swagger%20post%20create%20compte.png)

Création de compte via l'interface Swagger.

---

## Endpoints API

### API REST

| Méthode | Endpoint | Description |
|---------|----------|-------------|
| `GET` | `/api/comptes` | Récupérer tous les comptes |
| `GET` | `/api/comptes/{id}` | Récupérer un compte par ID |
| `GET` | `/api/comptes/type/{type}` | Rechercher par type |
| `GET` | `/api/comptes/solde/{montant}` | Rechercher par solde minimum |
| `POST` | `/api/comptes` | Créer un nouveau compte |
| `PUT` | `/api/comptes/{id}` | Mettre à jour un compte |
| `DELETE` | `/api/comptes/{id}` | Supprimer un compte |

### Spring Data REST

**Base URL**: `/api/data`

Endpoints générés automatiquement avec support HATEOAS et pagination.

**Projections disponibles**:
- `soldeOnly` : ID, solde, devise
- `fullCompte` : Toutes les informations

### GraphQL

**Endpoint**: `/graphql`  
**Interface**: `/graphiql`

Queries et mutations disponibles pour toutes les opérations CRUD et opérations bancaires (dépôt, retrait, virement).

---

## Auteur

**Repository**: Bank-Account-MicroService  
**Propriétaire**: MAHDIBATIR  
**Date**: Novembre 2025

