package org.carbon.model;

import org.carbon.model.enums.Mouvement;
import org.carbon.model.enums.Orientation;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Classe représentant un aventurier.
 * Auteur: Yassir EL KOBI
 */
public class Aventurier {
    private String nom;
    private Position position;
    private Orientation orientation;
    private Queue<Mouvement> mouvements;
    private int nbTresorsRamasses;

    public Aventurier(String nom, Position position, Orientation orientation,
                      String sequenceMouvements) {
        this.nom = nom;
        this.position = position;
        this.orientation = orientation;
        this.mouvements = new LinkedList<>();
        for (char c : sequenceMouvements.toCharArray()) {
            this.mouvements.add(Mouvement.depuisCaractere(c));
        }
        this.nbTresorsRamasses = 0;
    }

    // Méthodes pour gérer les mouvements
    public void tournerGauche() {
        switch (orientation) {
            case NORD:
                orientation = Orientation.OUEST;
                break;
            case OUEST:
                orientation = Orientation.SUD;
                break;
            case SUD:
                orientation = Orientation.EST;
                break;
            case EST:
                orientation = Orientation.NORD;
                break;
        }
    }

    public void tournerDroite() {
        switch (orientation) {
            case NORD:
                orientation = Orientation.EST;
                break;
            case EST:
                orientation = Orientation.SUD;
                break;
            case SUD:
                orientation = Orientation.OUEST;
                break;
            case OUEST:
                orientation = Orientation.NORD;
                break;
        }
    }

    public void incrementerTresors() {
        nbTresorsRamasses++;
    }

    // Getters et setters
    public String getNom() {
        return nom;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Orientation getOrientation() {
        return orientation;
    }

    public Queue<Mouvement> getMouvements() {
        return mouvements;
    }

    public int getNbTresorsRamasses() {
        return nbTresorsRamasses;
    }
}
