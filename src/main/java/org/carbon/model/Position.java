package org.carbon.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * Classe représentant une position sur la carte.
 * Auteur: Yassir EL KOBI
 */
@Getter
@EqualsAndHashCode
@ToString
public class Position {
    private final int x;
    private final int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }
}