package org.carbon.model.enums;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe de test pour Mouvement.
 * Auteur : Yassir EL KOBI
 */
public class MouvementTest {

    /**
     * Test de la méthode depuisCaractere().
     */
    @Test
    public void testDepuisCaractere() {
        assertEquals(Mouvement.AVANCER, Mouvement.depuisCaractere('A'));
        assertEquals(Mouvement.GAUCHE, Mouvement.depuisCaractere('G'));
        assertEquals(Mouvement.DROITE, Mouvement.depuisCaractere('D'));

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            Mouvement.depuisCaractere('X');
        });
        assertEquals("Mouvement invalide: X", exception.getMessage());
    }
}