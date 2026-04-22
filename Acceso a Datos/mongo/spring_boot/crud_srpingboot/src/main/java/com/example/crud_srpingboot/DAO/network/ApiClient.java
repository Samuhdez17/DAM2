package com.example.crud_srpingboot.DAO.network;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

import com.example.crud_srpingboot.DAO.model.Amigo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class ApiClient {
    private final String urlBase = "http://localhost:8080/amigos";
    private final HttpClient cliente = HttpClient.newHttpClient();
    private final Gson gson = new Gson();

    public ApiClient() {
    }

    // GET - Listado
    public List<Amigo> listarAmigos(String orden) throws IOException, InterruptedException {
        // Se le hace la peticion a la api con el tipo de orden
        HttpRequest peticion = HttpRequest.newBuilder()
        .uri(URI.create(urlBase + "/listar?tipo=" + orden))
        .GET()
        .build();

        // Se recibe la respuesta en formato json
        HttpResponse<String> respuesta = cliente.send(peticion, HttpResponse.BodyHandlers.ofString());
        String json = respuesta.body();

        // Transformamos el json para devolver el listado de objetos
        return gson.fromJson(json, new TypeToken<List<Amigo>>(){}.getType());
    }

    // POST - Insertar amigo
    public HttpResponse<String> insertarAmigo(Amigo amigo) throws IOException, InterruptedException {
        // Transformamos el nuevo amigo a json
        String amigoJson = gson.toJson(amigo);

        // Se le pide a la api que lo agrege
        HttpRequest peticion = HttpRequest.newBuilder()
        .uri(URI.create(urlBase + "/agregar"))
        .header("Content-Type", "application/json")
        .POST(HttpRequest.BodyPublishers.ofString(amigoJson))
        .build();

        // Recibimos la respuesta
        return cliente.send(peticion, HttpResponse.BodyHandlers.ofString());
    }

    // PUT - Actualizar amigo
    public HttpResponse<String> actualizarAmigo(String id, Amigo amigoActualizado) throws IOException, InterruptedException {
        // Transformamos el amigo actualizado a json
        String amigoJson = gson.toJson(amigoActualizado);

        // Se le pide a la api que lo actualice
        HttpRequest peticion = HttpRequest.newBuilder()
        .uri(URI.create(urlBase + "/actualizar?id=" + id))
        .header("Content-Type", "application/json")
        .PUT(HttpRequest.BodyPublishers.ofString(amigoJson))
        .build();
     
        return cliente.send(peticion, HttpResponse.BodyHandlers.ofString());
    }

    // DELETE - Borrar amigo
    public HttpResponse<String> borrarAmigo(String id) throws IOException, InterruptedException {
        // Se le pide a la api que lo actualice
        HttpRequest peticion = HttpRequest.newBuilder()
        .uri(URI.create(urlBase + "/eliminar?id=" + id))
        .DELETE()
        .build();
     
        return cliente.send(peticion, HttpResponse.BodyHandlers.ofString());
    }
}
