package com.example.ej_crud_springboot.filamentos.client;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

import com.example.ej_crud_springboot.filamentos.model.Filamento;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class ApiClient {
    private final String urlBase = "http://localhost:8080/filamentos";
    private final HttpClient cliente = HttpClient.newHttpClient();
    private final Gson gson = new Gson();
    
    public ApiClient() {
    }
    
    // GET - Listado filamentos
    public List<Filamento> listarFilamentos(String orden) throws IOException, InterruptedException {
        // Se le hace la peticion a la api con el tipo de orden
        HttpRequest peticion = HttpRequest.newBuilder()
        .uri(URI.create(urlBase + "/listar?orden=" + orden))
        .GET()
        .build();

        // Se recibe la respuesta en formato json
        HttpResponse<String> respuesta = cliente.send(peticion, HttpResponse.BodyHandlers.ofString());
        String json = respuesta.body();

        // Transformamos el json para devolver el listado de objetos
        return gson.fromJson(json, new TypeToken<List<Filamento>>(){}.getType());
    }

    // POST - Insertar filamento
    public HttpResponse<String> insertarFilamento(Filamento filamento) throws IOException, InterruptedException {
        // Transformamos el nuevo filamento a json
        String filamentoJson = gson.toJson(filamento);

        // Se le pide a la api que lo agrege
        HttpRequest peticion = HttpRequest.newBuilder()
        .uri(URI.create(urlBase + "/agregar"))
        .header("Content-Type", "application/json")
        .POST(HttpRequest.BodyPublishers.ofString(filamentoJson))
        .build();

        // Recibimos la respuesta
        return cliente.send(peticion, HttpResponse.BodyHandlers.ofString());
    }

    // PUT - Actualizar filamento
    public HttpResponse<String> actualizarFilamento(String id, Filamento filamentoActualizado) throws IOException, InterruptedException {
        // Transformamos el filamento actualizado a json
        String filamentoJson = gson.toJson(filamentoActualizado);

        // Se le pide a la api que lo actualice
        HttpRequest peticion = HttpRequest.newBuilder()
        .uri(URI.create(urlBase + "/actualizar?id=" + id))
        .header("Content-Type", "application/json")
        .PUT(HttpRequest.BodyPublishers.ofString(filamentoJson))
        .build();
     
        return cliente.send(peticion, HttpResponse.BodyHandlers.ofString());
    }

    // DELETE - Borrar filamento
    public HttpResponse<String> borrarFilamento(String id) throws IOException, InterruptedException {
        // Se le pide a la api que lo actualice
        HttpRequest peticion = HttpRequest.newBuilder()
        .uri(URI.create(urlBase + "/eliminar?id=" + id))
        .DELETE()
        .build();
     
        return cliente.send(peticion, HttpResponse.BodyHandlers.ofString());
    }
}
