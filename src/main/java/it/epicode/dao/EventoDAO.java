package it.epicode.dao;

import it.epicode.entities.Concerto;
import it.epicode.entities.Evento;
import it.epicode.entities.PartitaDiCalcio;
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

    public List<Concerto> getConcertiInStreaming(boolean bool) {
        TypedQuery<Concerto> getAllQuery = entityManager.createQuery("SELECT e FROM Concerto e WHERE e.inStreaming = :bool", Concerto.class);
        getAllQuery.setParameter("bool", bool);
        return getAllQuery.getResultList();
    }

    public List<Concerto> getConcertiPerGenere(GenereMusicale genereMusicale) {
        TypedQuery<Concerto> getAllQuery = entityManager.createQuery("SELECT e FROM Concerto e WHERE e.genereMusicale = :genereMusicale", Concerto.class);
        getAllQuery.setParameter("genereMusicale", genereMusicale);
        return getAllQuery.getResultList();
    }

    public  List<PartitaDiCalcio> getPartiteVinteInCasa(){
        return entityManager.createNamedQuery("getPartiteVinteInCasa", PartitaDiCalcio.class).getResultList();
    }

    public  List<PartitaDiCalcio> getPartiteVinteInTrasferta(){
        return entityManager.createNamedQuery("getPartiteVinteInTrasferta", PartitaDiCalcio.class).getResultList();
    }

    public  List<PartitaDiCalcio> getPartitePareggiate(){
        return entityManager.createNamedQuery("getPartitePareggiate", PartitaDiCalcio.class).getResultList();
    }

}
