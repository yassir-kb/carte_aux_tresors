package org.carbon.service;

import org.carbon.model.*;
import org.carbon.model.enums.Mouvement;
import org.carbon.model.enums.Orientation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe de test pour SimulationService.
 * Auteur : Yassir EL KOBI
 */
public class SimulationServiceTest {

    private SimulationService simulationService;
    private DonneesJeu donneesJeu;
    private Carte carte;

    @BeforeEach
    public void setUp() {
        simulationService = new SimulationService();
        donneesJeu = new DonneesJeu();
        carte = new Carte(3, 4);
        donneesJeu.setCarte(carte);
    }

    /**
     * Test de la méthode executerSimulation().
     */
    @Test
    public void testExecuterSimulation() {
        // Création des données du jeu
        DonneesJeu donneesJeu = new DonneesJeu();
        Carte carte = new Carte(3, 4);
        donneesJeu.setCarte(carte);

        // Ajout d'une montagne
        Cellule celluleMontagne = carte.getCellule(new Position(1, 1));
        celluleMontagne.setMontagne(true);

        // Ajout de trésors
        Cellule celluleTresor = carte.getCellule(new Position(0, 3));
        celluleTresor.setNbTresors(2);

        // Création de l'aventurier
        Aventurier aventurier = new Aventurier("Indiana", new Position(0, 0), Orientation.SUD, "AADADAGGA");
        donneesJeu.getAventuriers().add(aventurier);
        carte.getCellule(aventurier.getPosition()).setAventurier(aventurier);

        SimulationService simulationService = new SimulationService();
        simulationService.executerSimulation(donneesJeu);

        // Calcul de la position attendue
        Position positionAttendue = new Position(0, 2); // Par exemple

        // Vérifications
        assertEquals(positionAttendue, aventurier.getPosition());
        // Mettez à jour les autres assertions en conséquence
    }

    /**
     * Test de la méthode executerSimulation() avec un aventurier avançant normalement.
     */
    @Test
    public void testExecuterSimulation_AventurierAvance() {
        // Configuration de la carte et des trésors
        Cellule celluleTresor = carte.getCellule(new Position(0, 1));
        celluleTresor.setNbTresors(1);

        // Création de l'aventurier
        Aventurier aventurier = new Aventurier("Test", new Position(0, 0), Orientation.SUD, "A");
        donneesJeu.getAventuriers().add(aventurier);
        carte.getCellule(aventurier.getPosition()).setAventurier(aventurier);

        // Exécution de la simulation
        simulationService.executerSimulation(donneesJeu);

        // Vérifications
        assertEquals(new Position(0, 1), aventurier.getPosition(), "L'aventurier devrait avoir avancé d'une case vers le sud.");
        assertEquals(1, aventurier.getNbTresorsRamasses(), "L'aventurier devrait avoir ramassé un trésor.");
        assertEquals(0, celluleTresor.getNbTresors(), "Le trésor devrait avoir été ramassé.");
    }

    /**
     * Test de la méthode executerSimulation() avec un aventurier face à une montagne.
     */
    @Test
    public void testExecuterSimulation_AventurierFaceMontagne() {
        // Ajout d'une montagne devant l'aventurier
        carte.getCellule(new Position(0, 1)).setMontagne(true);

        // Création de l'aventurier
        Aventurier aventurier = new Aventurier("Test", new Position(0, 0), Orientation.SUD, "A");
        donneesJeu.getAventuriers().add(aventurier);
        carte.getCellule(aventurier.getPosition()).setAventurier(aventurier);

        // Exécution de la simulation
        simulationService.executerSimulation(donneesJeu);

        // Vérifications
        assertEquals(new Position(0, 0), aventurier.getPosition(), "L'aventurier ne devrait pas avoir bougé car il fait face à une montagne.");
    }

    /**
     * Test de la méthode executerSimulation() avec un aventurier essayant de sortir de la carte.
     */
    @Test
    public void testExecuterSimulation_AventurierSortCarte() {
        // Création de l'aventurier en bordure de la carte
        Aventurier aventurier = new Aventurier("Test", new Position(0, 0), Orientation.NORD, "A");
        donneesJeu.getAventuriers().add(aventurier);
        carte.getCellule(aventurier.getPosition()).setAventurier(aventurier);

        // Exécution de la simulation
        simulationService.executerSimulation(donneesJeu);

        // Vérifications
        assertEquals(new Position(0, 0), aventurier.getPosition(), "L'aventurier ne devrait pas avoir bougé car il ne peut pas sortir de la carte.");
    }

    /**
     * Test de la méthode executerSimulation() avec collecte de plusieurs trésors sur la même case.
     */
    @Test
    public void testExecuterSimulation_CollectePlusieursTresors() {
        // Ajout de plusieurs trésors sur une case
        Cellule celluleTresor = carte.getCellule(new Position(0, 1));
        celluleTresor.setNbTresors(2);

        // Création de l'aventurier avec des mouvements pour revenir sur la même case
        Aventurier aventurier = new Aventurier("Test", new Position(0, 0), Orientation.SUD, "AAGGA");
        donneesJeu.getAventuriers().add(aventurier);
        carte.getCellule(aventurier.getPosition()).setAventurier(aventurier);

        // Exécution de la simulation
        simulationService.executerSimulation(donneesJeu);

        // Vérifications
        assertEquals(new Position(0, 1), aventurier.getPosition(), "L'aventurier devrait être revenu sur la case (0,1).");
        assertEquals(2, aventurier.getNbTresorsRamasses(), "L'aventurier devrait avoir ramassé deux trésors.");
        assertEquals(0, celluleTresor.getNbTresors(), "Les trésors devraient avoir été ramassés.");
    }

    /**
     * Test de la méthode executerSimulation() avec deux aventuriers se déplaçant sans conflit.
     */
    @Test
    public void testExecuterSimulation_DeuxAventuriersSansConflit() {
        // Création du premier aventurier
        Aventurier aventurier1 = new Aventurier("Alice", new Position(0, 0), Orientation.EST, "A");
        donneesJeu.getAventuriers().add(aventurier1);
        carte.getCellule(aventurier1.getPosition()).setAventurier(aventurier1);

        // Création du second aventurier
        Aventurier aventurier2 = new Aventurier("Bob", new Position(2, 0), Orientation.OUEST, "A");
        donneesJeu.getAventuriers().add(aventurier2);
        carte.getCellule(aventurier2.getPosition()).setAventurier(aventurier2);

        // Exécution de la simulation
        simulationService.executerSimulation(donneesJeu);

        // Vérifications
        assertEquals(new Position(1, 0), aventurier1.getPosition(), "Alice devrait avoir avancé à (1,0).");
        assertEquals(new Position(2, 0), aventurier2.getPosition(), "Bob ne devrait pas avoir bougé.");

        // Vérification des positions sur la carte
        assertEquals(aventurier1, carte.getCellule(new Position(1, 0)).getAventurier(), "La case (1,0) devrait contenir Alice.");
        assertEquals(aventurier2, carte.getCellule(new Position(2, 0)).getAventurier(), "La case (2,0) devrait contenir Bob.");
    }

    /**
     * Test de la méthode executerSimulation() avec deux aventuriers en conflit de mouvement.
     */
    @Test
    public void testExecuterSimulation_DeuxAventuriersConflit() {
        // Création du premier aventurier
        Aventurier aventurier1 = new Aventurier("Alice", new Position(1, 1), Orientation.EST, "A");
        donneesJeu.getAventuriers().add(aventurier1);
        carte.getCellule(aventurier1.getPosition()).setAventurier(aventurier1);

        // Création du second aventurier
        Aventurier aventurier2 = new Aventurier("Bob", new Position(2, 1), Orientation.OUEST, "A");
        donneesJeu.getAventuriers().add(aventurier2);
        carte.getCellule(aventurier2.getPosition()).setAventurier(aventurier2);

        // Exécution de la simulation
        simulationService.executerSimulation(donneesJeu);

        // Vérifications
        assertEquals(new Position(1, 1), aventurier1.getPosition(), "Alice devrait être restée à (1,1).");
        assertEquals(new Position(2, 1), aventurier2.getPosition(), "Bob devrait être resté à (2,1).");
    }

    /**
     * Test de la méthode executerSimulation() avec un aventurier effectuant des rotations.
     */
    @Test
    public void testExecuterSimulation_AventurierRotations() {
        // Création de l'aventurier
        Aventurier aventurier = new Aventurier("Test", new Position(1, 1), Orientation.NORD, "GDGDA");
        donneesJeu.getAventuriers().add(aventurier);
        carte.getCellule(aventurier.getPosition()).setAventurier(aventurier);

        // Exécution de la simulation
        simulationService.executerSimulation(donneesJeu);

        // Vérifications
        // Séquence de mouvements :
        // G : OUEST
        // D : NORD
        // G : OUEST
        // D : NORD
        // A : Avance vers le nord (1,0)

        assertEquals(new Position(1, 0), aventurier.getPosition(), "L'aventurier devrait être en position (1,0).");
        assertEquals(Orientation.NORD, aventurier.getOrientation(), "L'aventurier devrait être orienté vers le nord.");
    }

    /**
     * Test de la méthode executerSimulation() avec un aventurier sans mouvements restants.
     */
    @Test
    public void testExecuterSimulation_AventurierSansMouvements() {
        // Création de l'aventurier sans mouvements
        Aventurier aventurier = new Aventurier("Test", new Position(1, 1), Orientation.NORD, "");
        donneesJeu.getAventuriers().add(aventurier);
        carte.getCellule(aventurier.getPosition()).setAventurier(aventurier);

        // Exécution de la simulation
        simulationService.executerSimulation(donneesJeu);

        // Vérifications
        assertEquals(new Position(1, 1), aventurier.getPosition(), "L'aventurier ne devrait pas avoir bougé.");
        assertEquals(Orientation.NORD, aventurier.getOrientation(), "L'orientation de l'aventurier devrait être inchangée.");
    }
}