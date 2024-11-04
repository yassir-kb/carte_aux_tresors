package org.carbon.model;

import lombok.Getter;
import lombok.Setter;

/**
 * Classe représentant une cellule de la carte.
 * Auteur: Yassir EL KOBI
 */
@Getter
@Setter
public class Cellule {
    private final Position position;
    private boolean montagne;
    private int nbTresors;
    private Aventurier aventurier;

    public Cellule(Position position) {
        if (position == null) {
            throw new IllegalArgumentException("La position ne peut pas être null.");
        }
        this.position = position;
        this.montagne = false;
        this.nbTresors = 0;
        this.aventurier = null;
    }
}