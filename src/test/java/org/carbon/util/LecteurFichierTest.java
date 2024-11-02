package org.carbon.util;

import org.carbon.model.Aventurier;
import org.carbon.model.Carte;
import org.carbon.model.Cellule;
import org.carbon.model.DonneesJeu;
import org.carbon.model.enums.Orientation;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe de test pour LecteurFichier.
 * Auteur : Yassir EL KOBI
 */
public class LecteurFichierTest {

    /**
     * Test de la méthode lireFichier() avec un fichier d'entrée valide.
     */
    @Test
    public void testLireFichierValide() throws IOException {
        // Création d'un fichier temporaire avec des données valides
        File fichierTemp = File.createTempFile("test", ".txt");
        try (FileWriter writer = new FileWriter(fichierTemp)) {
            writer.write("C - 3 - 4\n");
            writer.write("M - 1 - 0\n");
            writer.write("T - 0 - 3 - 2\n");
            writer.write("A - Lara - 1 - 1 - S - AADADAGGA\n");
        }

        DonneesJeu donneesJeu = LecteurFichier.lireFichier(fichierTemp.getAbsolutePath());

        // Vérifications
        Carte carte = donneesJeu.getCarte();
        assertEquals(3, carte.getLargeur());
        assertEquals(4, carte.getHauteur());

        Cellule celluleMontagne = carte.getCellule(new org.carbon.model.Position(1, 0));
        assertTrue(celluleMontagne.isMontagne());

        Cellule celluleTresor = carte.getCellule(new org.carbon.model.Position(0, 3));
        assertEquals(2, celluleTresor.getNbTresors());

        Aventurier aventurier = donneesJeu.getAventuriers().get(0);
        assertEquals("Lara", aventurier.getNom());
        assertEquals(Orientation.SUD, aventurier.getOrientation());
    }

    /**
     * Test de la méthode lireFichier() avec un fichier d'entrée invalide.
     */
    @Test
    public void testLireFichierInvalide() throws IOException {
        // Création d'un fichier temporaire avec des données invalides
        File fichierTemp = File.createTempFile("test", ".txt");
        try (FileWriter writer = new FileWriter(fichierTemp)) {
            writer.write("C - 3\n"); // Ligne invalide (manque une dimension)
        }

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            LecteurFichier.lireFichier(fichierTemp.getAbsolutePath());
        });

        assertTrue(exception.getMessage().contains("Ligne 'C' mal formatée"));
    }

}