package org.example.inicios.controller;

import org.example.inicios.model.Amigo;
import org.example.inicios.repository.AmigoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/listar")
    public List<Amigo> listar() {
        return repositorio.findAll();
    }

    @PutMapping("/actualizar/{id}")
    public Amigo actualizar(@PathVariable String id, @RequestBody Amigo amigo) {
        amigo.setId(id);
        return repositorio.save(amigo);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable String id) {
        repositorio.deleteById(id);
    }
}
