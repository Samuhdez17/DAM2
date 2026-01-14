package org.example.dao;

import entidades.Empleado;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

import java.math.BigDecimal;
import java.util.List;

public class EmpleadoDAO {
    private final EntityManager em;
    public EmpleadoDAO(EntityManager em) {
        this.em = em;
    }

    public void insertarEmpleado(Empleado empleado) {
        em.getTransaction().begin();
        em.persist(empleado);
        em.getTransaction().commit();
    }

    public List<Empleado> listarPorDepartamento(int idDepartamento) {
        Query q = em.createQuery("SELECT e FROM Empleado e WHERE e.idDepartamento.id = :idDepartamento");
        q.setParameter("idDepartamento", idDepartamento);
        return (List<Empleado>) q.getResultList();
    }

    public void actualizarSalario(int idEmpleado, BigDecimal nuevoSalario) {
        em.getTransaction().begin();
        Empleado empleado = em.find(Empleado.class, idEmpleado);
        empleado.setSalario(nuevoSalario);
        em.getTransaction().commit();
    }

    public void eliminarEmpleado(int idEmpleado) {
        em.getTransaction().begin();
        Empleado empleado = em.find(Empleado.class, idEmpleado);
        em.remove(empleado);
        em.getTransaction().commit();
    }
}
