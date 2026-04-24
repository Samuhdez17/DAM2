package com.example.ej_crud_springboot.filamentos.client;

import java.net.http.HttpClient;

import com.google.gson.Gson;

public class ApiClient {
    private final String urlBase = "http://localhost:8080/filamentos";
    private final HttpClient cliente = HttpClient.newHttpClient();
    private final Gson gson = new Gson();
    
}
