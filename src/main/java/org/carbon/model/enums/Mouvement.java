package org.carbon.model.enums;

/**
 * Enumération des mouvements possibles.
 * Auteur: Yassir EL KOBI
 */
public enum Mouvement {
    AVANCER('A'),
    GAUCHE('G'),
    DROITE('D');

    private char code;

    Mouvement(char code) {
        this.code = code;
    }

    public char getCode() {
        return code;
    }

    public static Mouvement depuisCaractere(char c) {
        for (Mouvement m : values()) {
            if (m.code == c) {
                return m;
            }
        }
        throw new IllegalArgumentException("Mouvement invalide: " + c);
    }
}
