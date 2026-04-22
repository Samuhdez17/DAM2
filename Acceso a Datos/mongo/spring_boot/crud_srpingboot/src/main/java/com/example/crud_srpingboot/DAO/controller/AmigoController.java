package com.example.crud_srpingboot.DAO.controller;

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

import com.example.crud_srpingboot.DAO.model.Amigo;
import com.example.crud_srpingboot.DAO.service.AmigoService;

@RestController
@RequestMapping("/amigos")
public class AmigoController {
    @Autowired
    private AmigoService amigoService;

    @PostMapping("/agregar")
    public Amigo agregar(@RequestBody Amigo amigo) {
        return amigoService.agregarAmigo(amigo);
    }

    @GetMapping("/listar")
    public List<Amigo> listar(@RequestParam(defaultValue="nombremM") String tipo) throws Exception {
        return amigoService.listarPor(tipo);
    }

    @PutMapping("/actualizar")
    public Amigo actualizar(
        @RequestParam String id,
        @RequestBody Amigo amigo
    ) {
        return amigoService.actualizarAmigo(id, amigo);
    }

    @DeleteMapping("/eliminar")
    public void eliminar(@RequestParam String id) {
        amigoService.eliminarAmigo(id);
    }
}
