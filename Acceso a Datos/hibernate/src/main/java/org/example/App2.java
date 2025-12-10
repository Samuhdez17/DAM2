package org.example;

import Entidades.Equipo;
import Entidades.Jugadore;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class App2 {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("accDatNBA");
        EntityManager em = emf.createEntityManager();

        Jugadore jugador = new Jugadore(
                "Cristina",
                "Salamanca",
                "6,5",
                190,
                "F-G",
                new Equipo("Lakers")
        );

        em.getTransaction().begin();
        em.persist(jugador); // Para hacer insert
        em.getTransaction().commit(); // Para ejecutar el comando
    }
}
