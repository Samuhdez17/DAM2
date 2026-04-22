package com.example.crud_srpingboot.DAO.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.crud_srpingboot.DAO.model.Amigo;

public interface AmigosRepo extends MongoRepository<Amigo, String> {

}
