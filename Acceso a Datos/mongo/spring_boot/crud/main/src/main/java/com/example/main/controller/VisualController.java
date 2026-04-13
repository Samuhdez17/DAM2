package com.example.main.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/amigos")
public class VisualController {
    
    @GetMapping("/status")
    public Map<String, String> status() {
        return Map.of(
                "api", "AMIGOS CRUD",
                "estado", "OK",
                "autor", "Samuel"
        );
    }

}
