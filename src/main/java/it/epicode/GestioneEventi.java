package it.epicode;

import it.epicode.dao.EventoDAO;
import it.epicode.dao.LocationDAO;
import it.epicode.dao.PartecipazioneDAO;
import it.epicode.dao.PersonaDAO;
import it.epicode.enumeration.GenereMusicale;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class GestioneEventi {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("gestioneeventi");

    public static void main(String[] args) {
        EntityManager entityManager = emf.createEntityManager(); // Entity Manager è responsabile della gestione delle interazioni col DB
        EventoDAO eventoDAO = new EventoDAO(entityManager);
        LocationDAO locationDAO = new LocationDAO(entityManager);
        PartecipazioneDAO partecipazioneDAO = new PartecipazioneDAO(entityManager);
        PersonaDAO personaDAO = new PersonaDAO(entityManager);

//        Location location1 = new Location("Villa Franca", "Roma");
//        Location location2 = new Location("Castello Sforzesco", "Milano");
//        Persona persona1 = new Persona("Chiara", "Marini", "chiaramarini@gmail.com", LocalDate.now(), Genere.F);
//        Persona persona2 = new Persona("Stefano", "Miceli", "stefanomiceli@gmail.com", LocalDate.now(), Genere.M);
//        Evento evento1 = new Concerto("Live Green Day", LocalDate.now(), "Concerto Live dei Green Day a Roma", TipoEvento.PUBBLICO, 10000, location1, GenereMusicale.ROCK, false);
//        Evento evento2 = new Concerto("Live Green Day", LocalDate.now(), "Concerto Live dei Green Day a Milano", TipoEvento.PUBBLICO, 10000, location2, GenereMusicale.POP, true);
//        Partecipazione partecipazione1 = new Partecipazione(persona1, evento1, Stato.DA_CONFERMARE);
//        Partecipazione partecipazione2 = new Partecipazione(persona1, evento1, Stato.CONFERMATA);
//
//        locationDAO.save(location1);
//        locationDAO.save(location2);
//        personaDAO.save(persona1);
//        personaDAO.save(persona2);
//        eventoDAO.save(evento1);
//        eventoDAO.save(evento2);
//        partecipazioneDAO.save(partecipazione1);
//        partecipazioneDAO.save(partecipazione2);
        System.out.println("**CONCERTI IN STREAMING**");
        eventoDAO.getConcertiInStreaming(true).forEach(System.out::println);
        System.out.println();
        GenereMusicale genere = GenereMusicale.ROCK;
        System.out.println("**CONCERTI " + genere + "**");
        eventoDAO.getConcertiPerGenere(genere).forEach(System.out::println);

        entityManager.close();
    }
}
