package org.carbon.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe de test pour Position.
 * Auteur : Yassir EL KOBI
 */
public class PositionTest {

    /**
     * Test des méthodes equals() et hashCode().
     */
    @Test
    public void testEqualsAndHashCode() {
        Position position1 = new Position(1, 2);
        Position position2 = new Position(1, 2);
        Position position3 = new Position(2, 3);

        assertEquals(position1, position2);
        assertNotEquals(position1, position3);

        assertEquals(position1.hashCode(), position2.hashCode());
        assertNotEquals(position1.hashCode(), position3.hashCode());
    }
}