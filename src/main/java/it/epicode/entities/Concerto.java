package it.epicode.entities;

import it.epicode.enumeration.GenereMusicale;
import it.epicode.enumeration.TipoEvento;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "concerti")
public class Concerto extends Evento{
    @Column(name = "genere_musicale", nullable = false)
    @Enumerated(EnumType.STRING)
    private GenereMusicale genereMusicale;
    @Column(name = "in_streaming", nullable = false)
    private boolean inStreaming;

    public Concerto() {
    }

    public Concerto(String titolo, LocalDate dataEvento, String descrizione, TipoEvento tipoEvento, int numeroMassimoPartecipanti, Location location, GenereMusicale genereMusicale, boolean inStreaming) {
        super(titolo, dataEvento, descrizione, tipoEvento, numeroMassimoPartecipanti, location);
        this.genereMusicale = genereMusicale;
        this.inStreaming = inStreaming;
    }

    public GenereMusicale getGenereMusicale() {
        return genereMusicale;
    }

    public void setGenereMusicale(GenereMusicale genereMusicale) {
        this.genereMusicale = genereMusicale;
    }

    public boolean isInStreaming() {
        return inStreaming;
    }

    public void setInStreaming(boolean inStreaming) {
        this.inStreaming = inStreaming;
    }

    @Override
    public String toString() {
        return "Concerto{" +
                "genereMusicale=" + genereMusicale +
                ", inStreaming=" + inStreaming +
                ", titolo='" + titolo + '\'' +
                ", dataEvento=" + dataEvento +
                '}';
    }
}
