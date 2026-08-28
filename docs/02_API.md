# Seguidores — Contrato de la API REST

## Base

- **Base path**: `/api/seguimientos`
- **Formato**: JSON — **Puerto**: 8080 (configurable con `PORT`)

## Recursos

| Método | Ruta | Códigos de estado | Descripción |
|--------|------|-------------------|-------------|
| GET | `/api/seguimientos` | 200 | Lista todos los recursos |
| GET | `/api/seguimientos/{id}` | 200 / 404 | Obtiene un recurso por id |
| POST | `/api/seguimientos` | 201 / 400 | Crea un recurso |
| PUT | `/api/seguimientos/{id}` | 200 / 404 / 400 | Actualiza un recurso |
| DELETE | `/api/seguimientos/{id}` | 204 / 404 | Elimina un recurso |

## Atributos de un recurso

| Campo | Tipo | Obligatorio | Descripción |
|-------|------|-------------|-------------|
| id | Long | - | Identificador autogenerado |
| nombre | String | Sí | Nombre del recurso |

| seguidor | String | No | Campo del dominio |
| seguido | String | No | Campo del dominio |

## Ejemplos con curl

```bash
# Listar
curl http://localhost:8080/api/seguimientos

# Crear
curl -X POST http://localhost:8080/api/seguimientos \
  -H "Content-Type: application/json" \
  -d '{"nombre":"Mi recurso"}'

# Obtener por id
curl http://localhost:8080/api/seguimientos/1

# Actualizar
curl -X PUT http://localhost:8080/api/seguimientos/1 \
  -H "Content-Type: application/json" \
  -d '{"nombre":"Recurso actualizado"}'

# Eliminar
curl -X DELETE http://localhost:8080/api/seguimientos/1
```
