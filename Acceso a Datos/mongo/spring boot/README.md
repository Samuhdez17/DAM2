Los test los he ejecutado en Postman.
Las peticiones que se pueden hacer son:

  - Agregar amigo nuevo: Mediante un body rellenado en Postman.

  - Listar amigos: En el cual se le puede indicar mediante parametro el orden en base a algun valor de la clase Amigo (nombre, edad, num telefonos, num hobbies, num estudios). Los tipos de parametros son: nombre, edad, telefonos, hobbies y estudios.

  - Actualizar amigo: Se le indica por parametros el id del amigo a actualizar y se manda el amigo actualizado mediante el rellenado del body en Postman.

  - Eliminar amigo: Mediante el id del amigo mandado por parametros.

  - Estado api: Mediante una peticion get te devuelve una descripcion y stado de la api.

#*PETICIONES*
  - GET estado: http://localhost:8080/amigos/status
  - GET listado: http://localhost:8080/amigos/listar?tipo=
  - DEL: http://localhost:8080/amigos/eliminar?id=
  - POST: http://localhost:8080/amigos/agregar
  - PUT: http://localhost:8080/amigos/actualizar?id=

#*EJEMPLO DE INSERCION*
{
    "nombre": "Lucas",
    "edad": 50,
    "hobbies": ["arte", "bailar"],
    "telefonos": ["123123123, 214214241"],
    "estudios": [
        {
            "titulo": "IEA",
            "centro": "IES Satafi",
            "anio": 2024
        },
        {
            "titulo": "DAM",
            "centro": "IES Laguna de Joatzel",
            "anio": 2026
        }
    ]
}