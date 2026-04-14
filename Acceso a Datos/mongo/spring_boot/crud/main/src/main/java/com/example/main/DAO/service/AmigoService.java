package com.example.main.DAO.service;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.main.DAO.model.Amigo;
import com.example.main.DAO.repository.AmigosRepo;


@Service
public class AmigoService {
    private final AmigosRepo repo;

    public AmigoService(AmigosRepo repo) {
        this.repo = repo;
    }

    public Amigo agregarAmigo(Amigo amigo) {
        return repo.save(amigo);
    }

    public List<Amigo> listarPor(String tipo) throws Exception {
        Sort sentencia = null;

        switch (tipo) {
            case "nombre" -> {
                sentencia = Sort.by("nombre");
            }

            case "edadMm" -> {
                sentencia = Sort.by("edad").reverse();
            }

            case "edadmM" -> {
                sentencia = Sort.by("edad");
            }
            
            case "hobbiesMm" -> {
                sentencia = Sort.by(Sort.Direction.DESC, "hobbies");
            }

            case "hobbiesmM" -> {
                sentencia = Sort.by(Sort.Direction.ASC, "hobbies");
            }

            case "telefonosMm" -> {
                sentencia = Sort.by(Sort.Direction.DESC, "telefonos");
            }

            case "telefonosmM" -> {
                sentencia = Sort.by(Sort.Direction.ASC, "telefonos");
            }

            case "estudiosMm" -> {
                sentencia = Sort.by(Sort.Direction.DESC, "estudios");
            }

            case "estudiosmM" -> {
                sentencia = Sort.by(Sort.Direction.ASC, "estudios");
            }

            default -> throw new AssertionError("Tipo de ordenacion incorrecta");
            
        }

        return repo.findAll(sentencia);
    }

    public Amigo actualizarAmigo(String id, Amigo amigo) {
        amigo.setId(id);
        return repo.save(amigo);
    }

    public void eliminarAmigo(String id) {
        repo.deleteById(id);
    }
    
    
}
