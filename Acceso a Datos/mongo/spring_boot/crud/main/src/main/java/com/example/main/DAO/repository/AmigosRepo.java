package com.example.main.DAO.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.main.DAO.model.Amigo;

public interface AmigosRepo extends MongoRepository<Amigo, String> {

}
