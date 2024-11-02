package org.carbon.util;

import org.carbon.model.*;
import org.carbon.model.enums.Orientation;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe de test pour EcrivainFichier.
 * Auteur : Yassir EL KOBI
 */
public class EcrivainFichierTest {

    /**
     * Test de la méthode ecrireFichier().
     */
    @Test
    public void testEcrireFichier() throws IOException {
        // Préparation des données du jeu
        DonneesJeu donneesJeu = new DonneesJeu();
        Carte carte = new Carte(3, 4);
        donneesJeu.setCarte(carte);

        // Ajout d'une montagne
        Cellule celluleMontagne = carte.getCellule(new Position(1, 0));
        celluleMontagne.setMontagne(true);

        // Ajout de trésors
        Cellule celluleTresor = carte.getCellule(new Position(0, 3));
        celluleTresor.setNbTresors(2);

        // Création de l'aventurier
        Aventurier aventurier = new Aventurier("Lara", new Position(1, 1), Orientation.SUD, "AADADAGGA");
        aventurier.incrementerTresors(); // Simuler qu'elle a ramassé un trésor
        donneesJeu.getAventuriers().add(aventurier);

        // Écriture dans un fichier temporaire
        File fichierTemp = File.createTempFile("sortie", ".txt");
        EcrivainFichier.ecrireFichier(fichierTemp.getAbsolutePath(), donneesJeu);

        // Lecture du fichier pour vérification
        try (BufferedReader reader = new BufferedReader(new FileReader(fichierTemp))) {
            assertEquals("C - 3 - 4", reader.readLine());
            assertEquals("M - 1 - 0", reader.readLine());
            assertEquals("T - 0 - 3 - 2", reader.readLine());
            assertEquals("A - Lara - 1 - 1 - S - 1", reader.readLine());
            assertNull(reader.readLine()); // Fin du fichier
        }
    }
}