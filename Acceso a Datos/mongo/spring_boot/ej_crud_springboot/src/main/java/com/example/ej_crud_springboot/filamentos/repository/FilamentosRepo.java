package com.example.ej_crud_springboot.filamentos.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.ej_crud_springboot.filamentos.model.Filamento;

public interface FilamentosRepo extends MongoRepository<Filamento, String> {
    
}
