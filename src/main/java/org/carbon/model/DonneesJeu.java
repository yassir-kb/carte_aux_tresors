package org.carbon.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe contenant les données du jeu lues depuis le fichier.
 * Auteur: Yassir EL KOBI
 */
@Getter
public class DonneesJeu {
    @Setter
    private Carte carte;
    private final List<Aventurier> aventuriers;

    public DonneesJeu() {
        this.aventuriers = new ArrayList<>();
    }
}