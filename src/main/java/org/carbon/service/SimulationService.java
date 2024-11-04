package org.carbon.service;

import org.carbon.model.Aventurier;
import org.carbon.model.Cellule;
import org.carbon.model.DonneesJeu;
import org.carbon.model.Position;
import org.carbon.model.enums.Mouvement;
import org.carbon.util.EcrivainFichier;
import org.carbon.util.LecteurFichier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;

/**
 * Classe pour exécuter la simulation du jeu.
 * Auteur: Yassir EL KOBI
 */
@Service
public class SimulationService {

    /**
     * Exécute la simulation en utilisant le fichier d'entrée fourni et écrit le résultat dans un fichier.
     *
     * @param fichier             Fichier d'entrée uploadé par l'utilisateur.
     * @param cheminFichierSortie Chemin du fichier de sortie.
     * @return Résultat de la simulation sous forme de chaîne.
     * @throws IOException En cas d'erreur lors de la lecture ou de l'écriture.
     */
    public String executerSimulation(MultipartFile fichier, String cheminFichierSortie) throws IOException {
        if (fichier == null || fichier.isEmpty()) {
            throw new IllegalArgumentException("Le fichier d'entrée est vide ou invalide.");
        }
        if (cheminFichierSortie == null || cheminFichierSortie.isEmpty()) {
            throw new IllegalArgumentException("Le chemin du fichier de sortie ne peut pas être null ou vide.");
        }

        // Sauvegarder le fichier uploadé temporairement
        File fichierTemp = File.createTempFile("entree", ".txt");
        fichier.transferTo(fichierTemp);

        try {
            // Lire les données du fichier
            DonneesJeu donneesJeu = LecteurFichier.lireFichier(fichierTemp.getAbsolutePath());

            // Exécuter la simulation
            executerSimulation(donneesJeu);

            // Écrire le résultat dans une chaîne
            StringWriter writer = new StringWriter();
            EcrivainFichier.ecrireDansWriter(writer, donneesJeu);

            // Écrire le résultat dans un fichier pour le téléchargement
            EcrivainFichier.ecrireFichier(cheminFichierSortie, donneesJeu);

            return writer.toString();
        } finally {
            // Supprimer le fichier temporaire
            if (!fichierTemp.delete()) {
                // Log ou gérer si le fichier n'a pas pu être supprimé
            }
        }
    }

    /**
     * Méthode privée pour exécuter la simulation du jeu.
     *
     * @param donneesJeu Données du jeu.
     */
    public void executerSimulation(DonneesJeu donneesJeu) {
        if (donneesJeu == null) {
            throw new IllegalArgumentException("Les données du jeu ne peuvent pas être null.");
        }
        if (donneesJeu.getCarte() == null) {
            throw new IllegalArgumentException("La carte ne peut pas être null.");
        }
        boolean mouvementsRestants = true;

        while (mouvementsRestants) {
            mouvementsRestants = false;
            for (Aventurier aventurier : donneesJeu.getAventuriers()) {
                if (!aventurier.getMouvements().isEmpty()) {
                    mouvementsRestants = true;
                    Mouvement mouvement = aventurier.getMouvements().poll();
                    traiterMouvement(aventurier, mouvement, donneesJeu);
                }
            }
        }
    }

    /**
     * Traite un mouvement pour un aventurier donné.
     *
     * @param aventurier Aventurier en cours de traitement.
     * @param mouvement  Mouvement à effectuer.
     * @param donneesJeu Données du jeu.
     */
    private void traiterMouvement(Aventurier aventurier, Mouvement mouvement, DonneesJeu donneesJeu) {
        if (aventurier == null || mouvement == null || donneesJeu == null) {
            throw new IllegalArgumentException("Les arguments ne peuvent pas être null.");
        }

        switch (mouvement) {
            case AVANCER:
                Position prochainePosition = calculerProchainePosition(aventurier);
                if (positionValide(prochainePosition, donneesJeu) && caseAccessible(prochainePosition, donneesJeu)) {
                    Cellule prochaineCellule = donneesJeu.getCarte().getCellule(prochainePosition);
                    if (prochaineCellule.getAventurier() == null) {
                        Cellule celluleActuelle = donneesJeu.getCarte().getCellule(aventurier.getPosition());
                        celluleActuelle.setAventurier(null);

                        aventurier.setPosition(prochainePosition);
                        prochaineCellule.setAventurier(aventurier);

                        if (prochaineCellule.getNbTresors() > 0) {
                            prochaineCellule.setNbTresors(prochaineCellule.getNbTresors() - 1);
                            aventurier.incrementerTresors();
                        }
                    }
                }
                break;
            case GAUCHE:
                aventurier.tournerGauche();
                break;
            case DROITE:
                aventurier.tournerDroite();
                break;
        }
    }

    /**
     * Calcule la prochaine position en fonction de l'orientation de l'aventurier.
     *
     * @param aventurier Aventurier concerné.
     * @return Position suivante.
     */
    private Position calculerProchainePosition(Aventurier aventurier) {
        if (aventurier == null || aventurier.getPosition() == null || aventurier.getOrientation() == null) {
            throw new IllegalArgumentException("L'aventurier et ses attributs ne peuvent pas être null.");
        }
        int x = aventurier.getPosition().getX();
        int y = aventurier.getPosition().getY();

        switch (aventurier.getOrientation()) {
            case NORD:
                y -= 1;
                break;
            case SUD:
                y += 1;
                break;
            case EST:
                x += 1;
                break;
            case OUEST:
                x -= 1;
                break;
        }
        return new Position(x, y);
    }

    /**
     * Vérifie si la position est valide sur la carte.
     *
     * @param position   Position à vérifier.
     * @param donneesJeu Données du jeu.
     * @return true si la position est valide, false sinon.
     */
    private boolean positionValide(Position position, DonneesJeu donneesJeu) {
        if (position == null || donneesJeu == null || donneesJeu.getCarte() == null) {
            return false;
        }
        return position.getX() >= 0 &&
                position.getX() < donneesJeu.getCarte().getLargeur() &&
                position.getY() >= 0 &&
                position.getY() < donneesJeu.getCarte().getHauteur();
    }

    /**
     * Vérifie si la case est accessible (non montagne).
     *
     * @param position   Position de la case.
     * @param donneesJeu Données du jeu.
     * @return true si la case est accessible, false sinon.
     */
    private boolean caseAccessible(Position position, DonneesJeu donneesJeu) {
        if (position == null || donneesJeu == null || donneesJeu.getCarte() == null) {
            return false;
        }
        Cellule cellule = donneesJeu.getCarte().getCellule(position);
        return !cellule.isMontagne();
    }
}