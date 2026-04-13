package com.example.main.repository;

import com.example.main.model.Amigo;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface AmigosRepo extends MongoRepository<Amigo, String> {

}
