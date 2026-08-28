# language: es
Característica: Servicio Seguidores (microservicio seguidores del caso caso12)
  Los escenarios validan el contrato REST del microservicio alineado a sus endpoints.

  Escenario: el listado del recurso responde 200
    Dado el servicio "Seguidores" está disponible
    Cuando consulto el listado de "seguimientos"
    Entonces el listado responde con código 200

  Escenario: ciclo de vida completo del recurso
    Dado un nuevo "seguimiento" con nombre "hola-cucumber"
    Cuando consulto el "seguimiento" recién creado
    Entonces el recurso tiene nombre "hola-cucumber" y código 200
    Cuando actualizo el "seguimiento" con nombre "cucumber-actualizado"
    Entonces el recurso queda con nombre "cucumber-actualizado" y código 200
    Cuando elimino el "seguimiento"
    Entonces la eliminación responde con código 204
    Y al consultar el "seguimiento" eliminado responde 404
