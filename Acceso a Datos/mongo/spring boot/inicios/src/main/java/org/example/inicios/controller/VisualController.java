package org.example.inicios.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class VisualController {
    @GetMapping("/status")
    public Map<String, String> status() {
        return Map.of(
                "api", "AMIGOS HUB",
                "estado", "OK",
                "autor", "Samuel"
        );
    }

}
