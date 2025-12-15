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
        
//        em.getTransaction().begin();
//        em.persist(jugador); // Para hacer insert
//        em.getTransaction().commit(); // Para ejecutar el comando

//        em.getTransaction().begin();
//        Jugadore j1 = em.find(Jugadore.class, 1773); // Select del jugador por ID
//        em.remove(j1); // Lo eliminamos de la tabla
//        em.getTransaction().commit();

        em.getTransaction().begin();
        Jugadore j2 = em.find(Jugadore.class, 1772);
        j2.setNombre("Lukaku");
        em.getTransaction().commit();
    }
}
