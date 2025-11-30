# Micro-service de gestion des comptes bancaires

## Description
Micro-service Spring Boot pour la gestion des comptes bancaires.

## Technologies utilisées
- Spring Boot 3.5.6
- Spring Data JPA
- H2 Database
- Lombok
- SpringDoc OpenAPI (Swagger)
- Java 21

## Installation et exécution
```bash
mvn clean install
mvn spring-boot:run
```

Ou utiliser le script de démarrage :
```bash
./start.bat
```

## Configuration
- **Port**: 8083
- **H2 Console**: http://localhost:8083/h2-console
- **API Base URL**: http://localhost:8083/api
- **Spring Data Rest**: http://localhost:8083/api/data
- **Swagger UI**: http://localhost:8083/swagger-ui.html
- **API Docs (JSON)**: http://localhost:8083/api-docs
- **GraphiQL**: http://localhost:8083/graphiql
- **GraphQL Endpoint**: http://localhost:8083/graphql

## Accès à H2 Console
1. Démarrer l'application
2. Ouvrir http://localhost:8083/h2-console dans votre navigateur
3. Configurer la connexion :
   - **JDBC URL**: `jdbc:h2:mem:compte-db`
   - **Username**: `sa`
   - **Password**: (laisser vide)
4. Cliquer sur "Connect"

## Endpoints REST disponibles

### 1. GET - Récupérer tous les comptes
```
GET http://localhost:8083/api/comptes
```

### 2. GET - Récupérer un compte par ID
```
GET http://localhost:8083/api/comptes/{id}
```

### 3. POST - Créer un nouveau compte
```
POST http://localhost:8083/api/comptes
Content-Type: application/json

{
  "solde": 5000.0,
  "devise": "MAD",
  "type": "COURANT"
}
```

### 4. PUT - Mettre à jour un compte
```
PUT http://localhost:8083/api/comptes/{id}
Content-Type: application/json

{
  "solde": 7500.0,
  "devise": "EUR",
  "type": "EPARGNE"
}
```

### 5. DELETE - Supprimer un compte
```
DELETE http://localhost:8083/api/comptes/{id}
```

### 6. GET - Rechercher par type
```
GET http://localhost:8083/api/comptes/type/COURANT
GET http://localhost:8083/api/comptes/type/EPARGNE
```

### 7. GET - Rechercher par solde minimum
```
GET http://localhost:8083/api/comptes/solde/5000
```

## Documentation Swagger

Accédez à la documentation interactive Swagger UI : **http://localhost:8083/swagger-ui.html**

Fonctionnalités :
- Visualiser tous les endpoints disponibles
- Voir les détails de chaque opération
- Tester les API directement depuis l'interface web
- Voir les schémas de données (modèles)

## GraphQL API

**GraphiQL Interface**: http://localhost:8083/graphiql

### Queries disponibles :

```graphql
# Récupérer tous les comptes
query {
  allComptes {
    id
    dateCreation
    solde
    devise
    type
  }
}

# Récupérer un compte par ID
query {
  compteById(id: "votre-id") {
    id
    solde
    devise
    type
  }
}

# Récupérer les comptes par type
query {
  comptesByType(type: COURANT) {
    id
    solde
    devise
  }
}

# Total des soldes
query {
  totalSoldes
}
```

### Mutations disponibles :

```graphql
# Créer un nouveau compte
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
  }
}

# Mettre à jour un compte
mutation {
  updateCompte(id: "votre-id", input: {
    solde: 7500.0
    devise: "EUR"
    type: EPARGNE
  }) {
    id
    solde
    devise
    type
  }
}

# Effectuer un dépôt
mutation {
  deposer(id: "votre-id", montant: 1000.0) {
    id
    solde
  }
}

# Effectuer un retrait
mutation {
  retirer(id: "votre-id", montant: 500.0) {
    id
    solde
  }
}

# Effectuer un virement
mutation {
  virement(idSource: "id-source", idDestination: "id-destination", montant: 1000.0)
}

# Supprimer un compte
mutation {
  deleteCompte(id: "votre-id")
}
```

## Spring Data Rest

Spring Data Rest expose automatiquement les repositories via REST API.

**Base URL**: http://localhost:8083/api/data

### Endpoints générés automatiquement :
```
GET    /api/data/comptes              - Liste tous les comptes (avec pagination)
GET    /api/data/comptes/{id}         - Récupérer un compte par ID
POST   /api/data/comptes              - Créer un nouveau compte
PUT    /api/data/comptes/{id}         - Mettre à jour un compte
PATCH  /api/data/comptes/{id}         - Mise à jour partielle
DELETE /api/data/comptes/{id}         - Supprimer un compte
GET    /api/data/comptes/search       - Découvrir les méthodes de recherche
```

### Projections disponibles :
- **soldeOnly**: Affiche uniquement ID, solde et devise
  ```
  GET /api/data/comptes?projection=soldeOnly
  GET /api/data/comptes/{id}?projection=soldeOnly
  ```

- **fullCompte**: Affiche toutes les informations
  ```
  GET /api/data/comptes?projection=fullCompte
  GET /api/data/comptes/{id}?projection=fullCompte
  ```

### Méthodes de recherche personnalisées :
```
GET /api/data/comptes/search/byType?type=COURANT
GET /api/data/comptes/search/bySolde?solde=5000
```

---

## Screenshots

### Étape 4 - Test de la couche DAO
![Console avec comptes créés](screens/etape4_console.png)
![H2 Console - Données](screens/etape4_h2_console.png)

### Étape 6 - Tests avec Postman

![GET tous les comptes](screens/etape6_get_all.png)
![GET compte par ID](screens/etape6_get_by_id.png)
![POST créer un compte](screens/etape6_post_create.png)
![PUT mettre à jour](screens/etape6_put_update.png)
![DELETE supprimer](screens/etape6_delete.png)
![GET par type](screens/etape6_get_by_type.png)
![GET par solde](screens/etape6_get_by_solde.png)

### Étape 7 - Documentation Swagger

![Swagger UI - Liste des endpoints](screens/etape7_swagger_ui.png)
![Swagger UI - Détails endpoint](screens/etape7_swagger_details.png)
![Swagger UI - Try it out](screens/etape7_swagger_try.png)
![Swagger UI - Réponse](screens/etape7_swagger_response.png)
![Swagger UI - Models](screens/etape7_swagger_models.png)

### Étape 8 - Spring Data Rest avec Projections

![Spring Data Rest - Root endpoint](screens/etape8_data_rest_root.png)
![Spring Data Rest - Liste comptes](screens/etape8_data_rest_list.png)
![Spring Data Rest - Projection soldeOnly](screens/etape8_projection_solde.png)
![Spring Data Rest - Projection fullCompte](screens/etape8_projection_full.png)
![Spring Data Rest - Search endpoints](screens/etape8_data_rest_search.png)
![Spring Data Rest - Search byType](screens/etape8_search_by_type.png)

### Étape 11 - GraphQL API

![GraphiQL Interface](screens/etape11_graphiql_interface.png)
![GraphQL - Query allComptes](screens/etape11_query_all_comptes.png)
![GraphQL - Query compteById](screens/etape11_query_by_id.png)
![GraphQL - Query totalSoldes](screens/etape11_query_total.png)
![GraphQL - Mutation createCompte](screens/etape11_mutation_create.png)
![GraphQL - Mutation deposer](screens/etape11_mutation_deposer.png)
![GraphQL - Mutation virement](screens/etape11_mutation_virement.png)
![GraphQL - Schema Documentation](screens/etape11_schema_docs.png)

### H2 Console
![H2 Console - Connexion](screens/h2_console_connection.png)
![H2 Console - Table COMPTE](screens/h2_console_table.png)

