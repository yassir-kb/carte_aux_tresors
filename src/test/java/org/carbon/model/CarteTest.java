package org.carbon.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe de test pour Carte.
 * Auteur : Yassir EL KOBI
 */
public class CarteTest {

    /**
     * Test de l'initialisation de la carte.
     */
    @Test
    public void testInitialisationCarte() {
        Carte carte = new Carte(3, 4);
        assertEquals(3, carte.getLargeur());
        assertEquals(4, carte.getHauteur());

        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 3; x++) {
                Cellule cellule = carte.getCellule(new Position(x, y));
                assertNotNull(cellule);
                assertFalse(cellule.isMontagne());
                assertEquals(0, cellule.getNbTresors());
                assertNull(cellule.getAventurier());
            }
        }
    }

    /**
     * Test de la méthode getCellule() avec position invalide.
     */
    @Test
    public void testGetCellulePositionInvalide() {
        Carte carte = new Carte(2, 2);
        Position positionInvalide = new Position(-1, 0);

        // On peut modifier ici le type d'exception attendu
        assertThrows(IndexOutOfBoundsException.class, () -> {
            carte.getCellule(positionInvalide);
        });
    }

}