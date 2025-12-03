package org.example;

import Entidades.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

import java.util.List;
import java.util.Scanner;

public class App {
    private static Scanner sc = new Scanner(System.in);

    public static void main( String[] args ) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("accDatNBA");
        EntityManager em = emf.createEntityManager();

        System.out.print("indica la posicion de los jugadires que quieras ver: ");
        String posicion = sc.next();
        System.out.println();

        System.out.print("indica la cantidad de jugadores que quieras ver: ");
        int limite = sc.nextInt();
        System.out.println();

        buscarJugadoresPorPos(posicion, limite, em);

        em.close();
        emf.close();
    }

    private static void buscarJugadoresPorPos(String posicion, int limite, EntityManager em) {
        Query q = em.createQuery("SELECT j.nombre, j.nombreEquipo.nombre, j.posicion FROM Jugadore j WHERE posicion = :posicion LIMIT :limite");
        q.setParameter("posicion", posicion);
        q.setParameter("limite", limite);

        List<Object[]> jugadores = q.getResultList();

        for (Object[] jugador : jugadores) {
            System.out.printf("Jugador: %s ; Equipo: %s ; Posicion: %s\n",jugador[0], jugador[1], jugador[2]);
        }
    }

    private static void queryConJoin(EntityManager em) {
        Query q = em.createQuery("SELECT j.nombre, j.posicion FROM Jugadore j WHERE posicion = :posicion LIMIT 10");
        q.setParameter("posicion", "C");

        List<Object[]> jugadores = q.getResultList();

        for (Object[] jugador : jugadores) {
            System.out.printf("Jugador: %s ; Posicion: %s\n",jugador[0], jugador[1]);
        }
    }

    private static void leerJugadores(EntityManager em) {
        Query q = em.createQuery("SELECT j FROM Jugadore j");
        List<Jugadore> jugadores = (List<Jugadore>) q.getResultList();

        for (Jugadore jugador : jugadores) {
            System.out.printf("Jugador: %s ; Procedencia: %s\n", jugador.getNombre(),  jugador.getProcedencia() );
        }
    }
}
