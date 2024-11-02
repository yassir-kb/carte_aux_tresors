package org.carbon.controller;

import org.carbon.service.SimulationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

/**
 * Contrôleur pour gérer les requêtes de simulation.
 * Auteur: Yassir EL KOBI
 */
@Controller
public class SimulationController {

    @Autowired
    private SimulationService simulationService;

    private String cheminFichierSortie = "sortie.txt"; // Chemin du fichier de sortie

    @GetMapping("/")
    public String accueil() {
        return "index";
    }

    @PostMapping("/simuler")
    public String simuler(@RequestParam("fichier") MultipartFile fichier, Model model) {
        try {
            // Exécuter la simulation
            String resultat = simulationService.executerSimulation(fichier, cheminFichierSortie);

            // Ajouter le résultat au modèle pour l'afficher
            model.addAttribute("resultat", resultat);

            return "resultat";
        } catch (Exception e) {
            model.addAttribute("erreur", e.getMessage());
            return "index";
        }
    }

    @GetMapping("/telecharger")
    public ResponseEntity<FileSystemResource> telechargerFichier() {
        File fichier = new File(cheminFichierSortie);
        if (!fichier.exists()) {
            return ResponseEntity.notFound().build();
        }

        FileSystemResource fichierResource = new FileSystemResource(fichier);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentDisposition(ContentDisposition.builder("attachment")
                .filename("sortie.txt")
                .build());
        headers.setContentType(MediaType.TEXT_PLAIN);

        return ResponseEntity.ok()
                .headers(headers)
                .body(fichierResource);
    }
}
