package com.example.ej_crud_springboot.filamentos.service;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.ej_crud_springboot.filamentos.model.Filamento;
import com.example.ej_crud_springboot.filamentos.repository.FilamentosRepo;

@Service
public class FilamentoService {
    private final FilamentosRepo repo;

    public FilamentoService(FilamentosRepo repo) {
        this.repo = repo;
    }

    @SuppressWarnings("null")
    public Filamento agregarFilamento(Filamento filamento) {
        return repo.save(filamento);
    }

    public List<Filamento> listarFilametnos(String orden) {
        Sort sentencia = null;

        switch (orden) {
            case "materialmM" -> {
                sentencia = Sort.by("material");
            }

            case "materialMm" -> {
                sentencia = Sort.by("material").reverse();
            }

            case "marcamM" -> {
                sentencia = Sort.by("marca");
            }

            case "marcaMm" -> {
                sentencia = Sort.by("marca").reverse();
            }

            case "pesomM" -> {
                sentencia = Sort.by("grDispo");
            }

            case "pesoMm" -> {
                sentencia = Sort.by("grDispo").reverse();
            }

            default -> throw new AssertionError("");
        }

        return repo.findAll(sentencia);
    }

    public Filamento actualizarFilamento(String id, Filamento filamento) {
        filamento.setId(id);
        return repo.save(filamento);
    }

    @SuppressWarnings("null")
    public void eliminarFilamento(String id) {
        repo.deleteById(id);
    }

}
