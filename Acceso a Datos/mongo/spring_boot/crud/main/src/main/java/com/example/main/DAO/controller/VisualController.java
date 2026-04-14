package com.example.main.DAO.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.main.DAO.service.StatusService;

@RestController
@RequestMapping("/amigos")
public class VisualController {
    private final StatusService estado = new StatusService();

    @GetMapping("/status")
    public Map<String, String> status() {
        return estado.decirEstado();
    }
}
