package it.epicode.dao;

import it.epicode.entities.Evento;
import it.epicode.enumeration.GenereMusicale;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.List;

public class EventoDAO {
    private final EntityManager entityManager;
    public EventoDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    public void save(Evento evento) {
        EntityTransaction transaction = entityManager.getTransaction();

        transaction.begin();
        entityManager.persist(evento);
        transaction.commit();

        System.out.println("Evento '" + evento.getTitolo() + "' aggiunto correttamente!");
    }
    public Evento getById(long id) {
        // Equivale a una ricerca SQL: SELECT * FROM eventi WHERE id = id
        return entityManager.find(Evento.class, id);
    }
    public void deleteById(long id) {
        Evento found = this.getById(id);

        if (found != null) {
            EntityTransaction transaction = entityManager.getTransaction();

            transaction.begin();
            entityManager.remove(found);
            transaction.commit();

            System.out.println("Evento '" + found.getTitolo() + "' eliminato correttamente!");

        } else {
            System.out.println("L'evento con l'id [" + id + "] non è stato trovato");
        }
    }

    public List<Evento> getConcertiInStreaming(boolean bool) {
        TypedQuery<Evento> getAllQuery = entityManager.createQuery("SELECT e FROM Evento e WHERE e.inStreaming = :bool", Evento.class);
        getAllQuery.setParameter("bool", bool);
        return getAllQuery.getResultList();
    }

    public List<Evento> getConcertiPerGenere(GenereMusicale genereMusicale) {
        TypedQuery<Evento> getAllQuery = entityManager.createQuery("SELECT e FROM Evento e WHERE e.genereMusicale = :genereMusicale", Evento.class);
        getAllQuery.setParameter("genereMusicale", genereMusicale);
        return getAllQuery.getResultList();
    }


}
