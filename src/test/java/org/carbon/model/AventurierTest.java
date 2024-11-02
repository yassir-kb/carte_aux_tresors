package org.carbon.model;

import org.carbon.model.enums.Mouvement;
import org.carbon.model.enums.Orientation;
import org.junit.jupiter.api.Test;

import java.util.Queue;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe de test pour la classe Aventurier.
 * Auteur : Yassir EL KOBI
 */
public class AventurierTest {
    /**
     * Test de la méthode tournerGauche().
     */
    @Test
    public void testTournerGauche() {
        Aventurier aventurier = new Aventurier("Test", new Position(0, 0), Orientation.NORD, "");
        aventurier.tournerGauche();
        assertEquals(Orientation.OUEST, aventurier.getOrientation());

        aventurier.tournerGauche();
        assertEquals(Orientation.SUD, aventurier.getOrientation());

        aventurier.tournerGauche();
        assertEquals(Orientation.EST, aventurier.getOrientation());

        aventurier.tournerGauche();
        assertEquals(Orientation.NORD, aventurier.getOrientation());
    }

    /**
     * Test de la méthode tournerDroite().
     */
    @Test
    public void testTournerDroite() {
        Aventurier aventurier = new Aventurier("Test", new Position(0, 0), Orientation.NORD, "");
        aventurier.tournerDroite();
        assertEquals(Orientation.EST, aventurier.getOrientation());

        aventurier.tournerDroite();
        assertEquals(Orientation.SUD, aventurier.getOrientation());

        aventurier.tournerDroite();
        assertEquals(Orientation.OUEST, aventurier.getOrientation());

        aventurier.tournerDroite();
        assertEquals(Orientation.NORD, aventurier.getOrientation());
    }

    /**
     * Test de l'incrémentation des trésors ramassés.
     */
    @Test
    public void testIncrementerTresors() {
        Aventurier aventurier = new Aventurier("Test", new Position(0, 0), Orientation.NORD, "");
        aventurier.incrementerTresors();
        assertEquals(1, aventurier.getNbTresorsRamasses());

        aventurier.incrementerTresors();
        assertEquals(2, aventurier.getNbTresorsRamasses());
    }

    /**
     * Test de la file de mouvements de l'aventurier.
     */
    @Test
    public void testMouvements() {
        Aventurier aventurier = new Aventurier("Test", new Position(0, 0), Orientation.NORD, "AGD");
        Queue<Mouvement> mouvements = aventurier.getMouvements();

        assertEquals(3, mouvements.size());
        assertEquals(Mouvement.AVANCER, mouvements.poll());
        assertEquals(Mouvement.GAUCHE, mouvements.poll());
        assertEquals(Mouvement.DROITE, mouvements.poll());
    }
}
