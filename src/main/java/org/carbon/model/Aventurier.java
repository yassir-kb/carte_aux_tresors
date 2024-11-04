package org.carbon.model;

import lombok.Getter;
import lombok.Setter;
import org.carbon.model.enums.Mouvement;
import org.carbon.model.enums.Orientation;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Classe représentant un aventurier.
 * Auteur: Yassir EL KOBI
 */
@Getter
@Setter
public class Aventurier {
    private String nom;
    private Position position;
    private Orientation orientation;
    private Queue<Mouvement> mouvements;
    private int nbTresorsRamasses;

    public Aventurier(String nom, Position position, Orientation orientation, String sequenceMouvements) {
        if (nom == null || nom.isEmpty()) {
            throw new IllegalArgumentException("Le nom de l'aventurier ne peut pas être null ou vide.");
        }
        if (position == null) {
            throw new IllegalArgumentException("La position de l'aventurier ne peut pas être null.");
        }
        if (orientation == null) {
            throw new IllegalArgumentException("L'orientation de l'aventurier ne peut pas être null.");
        }
        if (sequenceMouvements == null) {
            throw new IllegalArgumentException("La séquence de mouvements ne peut pas être null.");
        }
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
        if (orientation == null) {
            throw new IllegalStateException("L'orientation actuelle est null.");
        }
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
        if (orientation == null) {
            throw new IllegalStateException("L'orientation actuelle est null.");
        }
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
}