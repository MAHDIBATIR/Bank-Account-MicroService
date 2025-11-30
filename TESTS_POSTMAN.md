# Tests API REST avec Postman

## URL de base
`http://localhost:8083/api`

## Endpoints disponibles

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

