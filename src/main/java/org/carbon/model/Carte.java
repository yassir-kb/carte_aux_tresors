package org.carbon.model;

/**
 * Classe représentant la carte du jeu.
 * Auteur: Yassir EL KOBI
 */
public class Carte {
    private int largeur;
    private int hauteur;
    private Cellule[][] grille;

    public Carte(int largeur, int hauteur) {
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

    // Getters et méthodes associées
    public int getLargeur() {
        return largeur;
    }

    public int getHauteur() {
        return hauteur;
    }

    public Cellule[][] getGrille() {
        return grille;
    }

    public Cellule getCellule(Position position) {
        return grille[position.getY()][position.getX()];
    }
}
