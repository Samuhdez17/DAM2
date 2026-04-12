package com.example.main.controller;

import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.main.model.Amigo;
import com.example.main.repository.AmigosRepo;

@RestController
@RequestMapping("/amigos")
public class AmigoControler {
    @Autowired
    private AmigosRepo repo;

    @PostMapping("/agregar")
    public Amigo agregar(@RequestBody Amigo amigo) {
        return repo.save(amigo);
    }

    @GetMapping("/listar")
    public List<Amigo> listar(@RequestParam(defaultValue="nombre") String tipo) {
        switch (tipo) {
            case "nombre" -> {
                return repo.findAll(Sort.by("nombre"));
            }

            case "edad" -> {
                return repo.findAll(Sort.by("edad"));
            }
            
            case "hobbies" -> {
                return repo.findAll().stream().sorted(Comparator.comparingInt((Amigo a) -> a.getHobbies() == null ? 0 : a.getHobbies().size()).reversed()).toList();
            }

            case "telefonos" -> {
                return repo.findAll().stream().sorted(Comparator.comparingInt((Amigo a) -> a.getTelefonos() == null ? 0 : a.getTelefonos().size()).reversed()).toList();
            }

            case "estudios" -> {
                return repo.findAll().stream().sorted(Comparator.comparingInt((Amigo a) -> a.getEstudios() == null ? 0 : a.getEstudios().size()).reversed()).toList();
            }
            
            default -> throw new AssertionError("Tipo de ordenacion incorrecta");
        }
    }

    @PutMapping("/actualizar")
    public Amigo actualizar(
        @RequestParam String id,
        @RequestBody Amigo amigo
    ) {
        amigo.setId(id);
        return repo.save(amigo);
    }

    @DeleteMapping("/eliminar")
    public void eliminar(@RequestParam String id) {
        repo.deleteById(id);
    }
}
