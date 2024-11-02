package org.carbon.model.enums;

/**
 * Enumération des orientations possibles.
 * Auteur: Yassir EL KOBI
 */
public enum Orientation {
    NORD('N'),
    SUD('S'),
    EST('E'),
    OUEST('O');

    private char code;

    Orientation(char code) {
        this.code = code;
    }

    public char getCode() {
        return code;
    }

    public static Orientation depuisCaractere(char c) {
        for (Orientation o : values()) {
            if (o.code == c) {
                return o;
            }
        }
        throw new IllegalArgumentException("Orientation invalide: " + c);
    }
}
