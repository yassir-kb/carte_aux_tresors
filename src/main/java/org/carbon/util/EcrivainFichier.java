package org.carbon.util;

import org.carbon.model.DonneesJeu;
import org.carbon.model.Aventurier;
import org.carbon.model.Cellule;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

/**
 * Classe pour écrire le fichier de sortie.
 * Auteur: Yassir EL KOBI
 */
public class EcrivainFichier {

    public static void ecrireFichier(String cheminFichier, DonneesJeu donneesJeu) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(cheminFichier))) {
            // Écrire la carte
            writer.write("C - " + donneesJeu.getCarte().getLargeur() + " - " +
                    donneesJeu.getCarte().getHauteur());
            writer.newLine();

            // Écrire les montagnes
            for (Cellule[] ligne : donneesJeu.getCarte().getGrille()) {
                for (Cellule cellule : ligne) {
                    if (cellule.isMontagne()) {
                        writer.write("M - " + cellule.getPosition().getX() + " - " +
                                cellule.getPosition().getY());
                        writer.newLine();
                    }
                }
            }

            // Écrire les trésors restants
            for (Cellule[] ligne : donneesJeu.getCarte().getGrille()) {
                for (Cellule cellule : ligne) {
                    if (cellule.getNbTresors() > 0) {
                        writer.write("T - " + cellule.getPosition().getX() + " - " +
                                cellule.getPosition().getY() + " - " +
                                cellule.getNbTresors());
                        writer.newLine();
                    }
                }
            }

            // Écrire les aventuriers
            for (Aventurier aventurier : donneesJeu.getAventuriers()) {
                writer.write("A - " + aventurier.getNom() + " - " +
                        aventurier.getPosition().getX() + " - " +
                        aventurier.getPosition().getY() + " - " +
                        aventurier.getOrientation().getCode() + " - " +
                        aventurier.getNbTresorsRamasses());
                writer.newLine();
            }
        }
    }

    public static void ecrireDansWriter(Writer writer, DonneesJeu donneesJeu) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(writer);

        // Le reste du code est identique à la méthode précédente, mais on utilise bufferedWriter au lieu de créer un nouveau fichier.

        // Écrire la carte
        bufferedWriter.write("C - " + donneesJeu.getCarte().getLargeur() + " - " +
                donneesJeu.getCarte().getHauteur());
        bufferedWriter.newLine();

        // Écrire les montagnes
        for (Cellule[] ligne : donneesJeu.getCarte().getGrille()) {
            for (Cellule cellule : ligne) {
                if (cellule.isMontagne()) {
                    bufferedWriter.write("M - " + cellule.getPosition().getX() + " - " +
                            cellule.getPosition().getY());
                    bufferedWriter.newLine();
                }
            }
        }

        // Écrire les trésors restants
        for (Cellule[] ligne : donneesJeu.getCarte().getGrille()) {
            for (Cellule cellule : ligne) {
                if (cellule.getNbTresors() > 0) {
                    bufferedWriter.write("T - " + cellule.getPosition().getX() + " - " +
                            cellule.getPosition().getY() + " - " +
                            cellule.getNbTresors());
                    bufferedWriter.newLine();
                }
            }
        }

        // Écrire les aventuriers
        for (Aventurier aventurier : donneesJeu.getAventuriers()) {
            bufferedWriter.write("A - " + aventurier.getNom() + " - " +
                    aventurier.getPosition().getX() + " - " +
                    aventurier.getPosition().getY() + " - " +
                    aventurier.getOrientation().getCode() + " - " +
                    aventurier.getNbTresorsRamasses());
            bufferedWriter.newLine();
        }

        bufferedWriter.flush();
    }

}
