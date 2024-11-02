package org.carbon.model;

import org.carbon.model.enums.Orientation;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe de test pour DonneesJeu.
 * Auteur : Yassir EL KOBI
 */
public class DonneesJeuTest {

    /**
     * Test des getters et setters de DonneesJeu.
     */
    @Test
    public void testDonneesJeuGettersSetters() {
        DonneesJeu donneesJeu = new DonneesJeu();
        assertNull(donneesJeu.getCarte());
        assertNotNull(donneesJeu.getAventuriers());
        assertTrue(donneesJeu.getAventuriers().isEmpty());

        Carte carte = new Carte(3, 4);
        donneesJeu.setCarte(carte);
        assertEquals(carte, donneesJeu.getCarte());

        Aventurier aventurier = new Aventurier("Test", new Position(0, 0), Orientation.NORD, "");
        donneesJeu.getAventuriers().add(aventurier);
        assertEquals(1, donneesJeu.getAventuriers().size());
        assertEquals(aventurier, donneesJeu.getAventuriers().get(0));
    }
}