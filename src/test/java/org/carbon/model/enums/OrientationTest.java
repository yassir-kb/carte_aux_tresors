package org.carbon.model.enums;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe de test pour Orientation.
 * Auteur : Yassir EL KOBI
 */
public class OrientationTest {

    /**
     * Test de la méthode depuisCaractere().
     */
    @Test
    public void testDepuisCaractere() {
        assertEquals(Orientation.NORD, Orientation.depuisCaractere('N'));
        assertEquals(Orientation.SUD, Orientation.depuisCaractere('S'));
        assertEquals(Orientation.EST, Orientation.depuisCaractere('E'));
        assertEquals(Orientation.OUEST, Orientation.depuisCaractere('O'));

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            Orientation.depuisCaractere('X');
        });
        assertEquals("Orientation invalide: X", exception.getMessage());
    }

}