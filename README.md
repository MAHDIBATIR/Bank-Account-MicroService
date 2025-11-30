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
- **Swagger UI**: http://localhost:8083/swagger-ui.html
- **API Docs (JSON)**: http://localhost:8083/api-docs

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

### H2 Console
![H2 Console - Connexion](screens/h2_console_connection.png)
![H2 Console - Table COMPTE](screens/h2_console_table.png)

