package org.carbon.model;

/**
 * Classe représentant une cellule de la carte.
 * Auteur: Yassir EL KOBI
 */
public class Cellule {
    private Position position;
    private boolean montagne;
    private int nbTresors;
    private Aventurier aventurier;

    public Cellule(Position position) {
        this.position = position;
        this.montagne = false;
        this.nbTresors = 0;
        this.aventurier = null;
    }

    // Getters et setters
    public Position getPosition() {
        return position;
    }

    public boolean isMontagne() {
        return montagne;
    }

    public void setMontagne(boolean montagne) {
        this.montagne = montagne;
    }

    public int getNbTresors() {
        return nbTresors;
    }

    public void setNbTresors(int nbTresors) {
        this.nbTresors = nbTresors;
    }

    public Aventurier getAventurier() {
        return aventurier;
    }

    public void setAventurier(Aventurier aventurier) {
        this.aventurier = aventurier;
    }
}
