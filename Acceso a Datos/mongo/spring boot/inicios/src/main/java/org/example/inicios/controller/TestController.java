package org.example.inicios.controller;

import org.example.inicios.model.Amigo;
import org.example.inicios.repository.AmigoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
public class TestController {
    @Autowired
    private AmigoRepository repositorio;

    @PostMapping("/insertar")
    public Amigo insertar() {
        Amigo amigo = new Amigo();

        amigo.setNombre("Rodrigo");
        amigo.setEdad(20);

        return repositorio.save(amigo);
    }
}
