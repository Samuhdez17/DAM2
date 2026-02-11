package org.example.inicios.repository;

import org.example.inicios.model.Amigo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AmigoRepository extends MongoRepository<Amigo, String> {

}
