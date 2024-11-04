package org.carbon.util;

import org.carbon.model.DonneesJeu;
import org.carbon.model.*;
import org.carbon.model.enums.Orientation;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Classe pour lire et parser le fichier d'entrée.
 * Auteur: Yassir EL KOBI
 */
public class LecteurFichier {

    public static DonneesJeu lireFichier(String cheminFichier) throws IOException {
        if (cheminFichier == null || cheminFichier.isEmpty()) {
            throw new IllegalArgumentException("Le chemin du fichier ne peut pas être null ou vide.");
        }
        DonneesJeu donneesJeu = new DonneesJeu();
        Carte carte = null;

        try (BufferedReader reader = new BufferedReader(new FileReader(cheminFichier))) {
            String ligne;
            while ((ligne = reader.readLine()) != null) {
                ligne = ligne.trim();
                if (ligne.isEmpty() || ligne.startsWith("#")) {
                    continue;
                }
                String[] parties = ligne.split(" - ");

                if (parties.length == 0) {
                    throw new IllegalArgumentException("Ligne vide ou mal formatée : " + ligne);
                }

                switch (parties[0]) {
                    case "C":
                        if (parties.length != 3) {
                            throw new IllegalArgumentException("Ligne 'C' mal formatée : " + ligne);
                        }
                        int largeur = Integer.parseInt(parties[1]);
                        int hauteur = Integer.parseInt(parties[2]);
                        carte = new Carte(largeur, hauteur);
                        donneesJeu.setCarte(carte);
                        break;
                    case "M":
                        if (carte == null) {
                            throw new IllegalStateException("La carte doit être définie avant de définir des montagnes.");
                        }
                        if (parties.length != 3) {
                            throw new IllegalArgumentException("Ligne 'M' mal formatée : " + ligne);
                        }
                        int xM = Integer.parseInt(parties[1]);
                        int yM = Integer.parseInt(parties[2]);
                        Cellule celluleM = carte.getCellule(new Position(xM, yM));
                        celluleM.setMontagne(true);
                        break;
                    case "T":
                        if (carte == null) {
                            throw new IllegalStateException("La carte doit être définie avant de définir des trésors.");
                        }
                        if (parties.length != 4) {
                            throw new IllegalArgumentException("Ligne 'T' mal formatée : " + ligne);
                        }
                        int xT = Integer.parseInt(parties[1]);
                        int yT = Integer.parseInt(parties[2]);
                        int nbTresors = Integer.parseInt(parties[3]);
                        Cellule celluleT = carte.getCellule(new Position(xT, yT));
                        celluleT.setNbTresors(nbTresors);
                        break;
                    case "A":
                        if (carte == null) {
                            throw new IllegalStateException("La carte doit être définie avant de définir des aventuriers.");
                        }
                        if (parties.length != 6) {
                            throw new IllegalArgumentException("Ligne 'A' mal formatée : " + ligne);
                        }
                        String nom = parties[1];
                        int xA = Integer.parseInt(parties[2]);
                        int yA = Integer.parseInt(parties[3]);
                        Orientation orientation = Orientation.depuisCaractere(parties[4].charAt(0));
                        String sequenceMouvements = parties[5];
                        Aventurier aventurier = new Aventurier(nom, new Position(xA, yA),
                                orientation, sequenceMouvements);
                        donneesJeu.getAventuriers().add(aventurier);
                        // Placer l'aventurier sur la carte
                        Cellule celluleA = carte.getCellule(aventurier.getPosition());
                        if (celluleA.isMontagne() || celluleA.getAventurier() != null) {
                            throw new IllegalArgumentException("La position de l'aventurier est invalide ou déjà occupée : " + aventurier.getPosition());
                        }
                        celluleA.setAventurier(aventurier);
                        break;
                    default:
                        throw new IllegalArgumentException("Type de ligne inconnu: " + parties[0]);
                }
            }
        }
        if (donneesJeu.getCarte() == null) {
            throw new IllegalStateException("La carte n'a pas été définie dans le fichier.");
        }
        return donneesJeu;
    }
}