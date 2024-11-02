package org.carbon;

import org.carbon.service.SimulationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Classe principale de l'application.
 * Auteur: Yassir EL KOBI
 */

@SpringBootApplication
public class CarteAuxTresorsApplication {

    @Autowired
    private SimulationService simulationService;

    public static void main(String[] args) {
        SpringApplication.run(CarteAuxTresorsApplication.class, args);
    }
}

