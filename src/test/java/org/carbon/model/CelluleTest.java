package org.carbon.model;

import org.carbon.model.enums.Orientation;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe de test pour Cellule.
 * Auteur : Yassir EL KOBI
 */
public class CelluleTest {

    /**
     * Test des getters et setters de Cellule.
     */
    @Test
    public void testCelluleGettersSetters() {
        Position position = new Position(1, 1);
        Cellule cellule = new Cellule(position);

        assertEquals(position, cellule.getPosition());
        assertFalse(cellule.isMontagne());
        assertEquals(0, cellule.getNbTresors());
        assertNull(cellule.getAventurier());

        cellule.setMontagne(true);
        assertTrue(cellule.isMontagne());

        cellule.setNbTresors(3);
        assertEquals(3, cellule.getNbTresors());

        Aventurier aventurier = new Aventurier("Test", new Position(0, 0), Orientation.NORD, "A");
        cellule.setAventurier(aventurier);
        assertEquals(aventurier, cellule.getAventurier());
    }
}