package org.carbon.model;

import lombok.Getter;

/**
 * Classe représentant la carte du jeu.
 * Auteur: Yassir EL KOBI
 */
@Getter
public class Carte {
    private final int largeur;
    private final int hauteur;
    private final Cellule[][] grille;

    public Carte(int largeur, int hauteur) {
        if (largeur <= 0 || hauteur <= 0) {
            throw new IllegalArgumentException("La largeur et la hauteur doivent être supérieures à zéro.");
        }
        this.largeur = largeur;
        this.hauteur = hauteur;
        this.grille = new Cellule[hauteur][largeur];

        // Initialisation des cellules en plaines par défaut
        for (int y = 0; y < hauteur; y++) {
            for (int x = 0; x < largeur; x++) {
                grille[y][x] = new Cellule(new Position(x, y));
            }
        }
    }

    public Cellule getCellule(Position position) {
        if (position == null) {
            throw new IllegalArgumentException("La position ne peut pas être null.");
        }
        int x = position.getX();
        int y = position.getY();
        if (x < 0 || x >= largeur || y < 0 || y >= hauteur) {
            throw new IndexOutOfBoundsException("Position hors des limites de la carte.");
        }
        return grille[y][x];
    }
}