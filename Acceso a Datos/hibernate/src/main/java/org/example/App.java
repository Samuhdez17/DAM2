package org.example;

import Entidades.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

import java.util.List;
import java.util.Queue;

public class App {
    public static void main( String[] args ) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("accDatNBA");
        EntityManager em = emf.createEntityManager();

        leerJugadores(em);
        Query q = em.createQuery("INSERT INTO Jugadore (nombre, procedencia, altura, peso, posicion, nombreEquipo) VALUES ('Ossama', 'Atlanta', 67, 230, 'G', 'Timberwolves')");
        q.executeUpdate();

        leerJugadores(em);
    }

    private static void leerJugadores(EntityManager em) {
        Query q = em.createQuery("SELECT j FROM Jugadore j");
        List<Jugadore> jugadores = (List<Jugadore>) q.getResultList();

        for (Jugadore jugador : jugadores) {
            System.out.printf("Jugador: %s ; Procedencia: %s\n", jugador.getNombre(),  jugador.getProcedencia() );
        }
    }
}
