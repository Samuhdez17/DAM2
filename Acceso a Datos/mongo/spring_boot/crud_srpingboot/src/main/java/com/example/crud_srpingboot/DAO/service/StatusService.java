package com.example.crud_srpingboot.DAO.service;

import java.util.Map;

public class StatusService {

    public Map<String, String> decirEstado() {
        return Map.of(
                "api", "AMIGOS CRUD",
                "estado", "OK",
                "autor", "Samuel"
        );
    }
    
}
