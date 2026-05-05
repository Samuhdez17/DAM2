package com.example.ej_crud_springboot.filamentos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.ej_crud_springboot.filamentos.model.Filamento;
import com.example.ej_crud_springboot.filamentos.service.FilamentoService;

@RestController
@RequestMapping("/filamentos")
public class FilamentosControler {
    @Autowired
    private FilamentoService filamentoService;

    @PostMapping("/agregar")
    public Filamento agregar(
        @RequestBody Filamento filamento
    ) {
        return filamentoService.agregarFilamento(filamento);
    }
    
    @GetMapping("/listar")
    public List<Filamento> listar(
        @RequestParam(defaultValue="marcamM") String orden
    ) {
        return filamentoService.listarFilametnos(orden);
    }
    
    @PutMapping("/actualizar")
    public Filamento actualizar(
        @RequestParam String id,
        @RequestBody Filamento filamento
    ) {
        return filamentoService.actualizarFilamento(id, filamento);
    }
    
    @DeleteMapping("/eliminar")
    public void eliminar(
        @RequestParam String id
    ) {
        filamentoService.eliminarFilamento(id);
    }
}
