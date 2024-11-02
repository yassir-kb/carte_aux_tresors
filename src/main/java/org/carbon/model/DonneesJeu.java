package org.carbon.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe contenant les données du jeu lues depuis le fichier.
 * Auteur: Yassir EL KOBI
 */
public class DonneesJeu {
    private Carte carte;
    private List<Aventurier> aventuriers;

    public DonneesJeu() {
        this.aventuriers = new ArrayList<>();
    }

    // Getters et setters
    public Carte getCarte() {
        return carte;
    }

    public void setCarte(Carte carte) {
        this.carte = carte;
    }

    public List<Aventurier> getAventuriers() {
        return aventuriers;
    }
}
