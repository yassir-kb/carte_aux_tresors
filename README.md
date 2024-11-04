# Carte aux Trésors

Bienvenue dans le projet **Carte aux Trésors**, une application Java développée pour simuler une aventure sur une carte où des aventuriers se déplacent, ramassent des trésors, et interagissent avec l'environnement composé de montagnes et de plaines.

## Table des matières

- [Introduction](#introduction)
- [Fonctionnalités](#fonctionnalités)
- [Architecture Technique](#architecture-technique)
- [Prérequis](#prérequis)
- [Installation et Exécution](#installation-et-exécution)
- [Utilisation](#utilisation)
- [Tests](#tests)
- [Contribuer](#contribuer)
- [Licence](#licence)

## Introduction

L'application **Carte aux Trésors** est une simulation interactive où des aventuriers explorent une carte définie, collectent des trésors et évitent des obstacles tels que des montagnes. L'application lit un fichier d'entrée contenant les détails de la carte et des aventuriers, exécute la simulation en fonction des mouvements définis, et génère un fichier de sortie reflétant l'état final de la carte.

## Fonctionnalités

- **Lecture de fichier d'entrée** : Parse un fichier texte contenant la configuration de la carte, les positions des montagnes, des trésors, et les aventuriers avec leurs séquences de mouvements.
- **Simulation des mouvements** : Gère les déplacements des aventuriers en fonction des instructions (avancer, tourner à gauche, tourner à droite) et des règles du jeu.
- **Gestion des trésors** : Permet aux aventuriers de ramasser des trésors lorsqu'ils se déplacent sur une case contenant des trésors.
- **Gestion des obstacles** : Empêche les aventuriers de se déplacer sur des cases occupées par des montagnes ou d'autres aventuriers.
- **Génération de fichier de sortie** : Produit un fichier texte résumant l'état final de la carte, y compris les positions des aventuriers, les trésors restants, etc.
- **Interface Web** : Fournit une interface utilisateur simple pour télécharger le fichier d'entrée, exécuter la simulation et télécharger le fichier de sortie.

## Architecture Technique

- **Langage** : Java 21+
- **Framework** : Spring Boot 3.3.5
- **Gestion des dépendances** : Maven
- **Utilisation de Lombok** : Pour réduire le code boilerplate en utilisant des annotations pour les getters, setters, etc.
- **Tests unitaires** : Utilisation de JUnit 5 pour les tests unitaires des classes principales.
- **Structure du projet** :
    - `controller` : Contient le contrôleur Spring MVC pour gérer les requêtes HTTP.
    - `service` : Contient la logique métier pour exécuter la simulation.
    - `model` : Contient les classes représentant les entités du jeu (Carte, Aventurier, Position, etc.).
    - `util` : Contient les classes utilitaires pour la lecture et l'écriture des fichiers.
    - `resources` : Contient les templates Thymeleaf pour les vues.

## Prérequis

- **Java** : JDK 21 ou supérieur
- **Maven** : Version 3.6 ou supérieure
- **IDE recommandé** : IntelliJ IDEA ou Eclipse avec le plugin Lombok installé
- **Navigateur Web** : Pour accéder à l'interface utilisateur

## Installation et Exécution

### 1. Cloner le dépôt

```bash
git clone https://github.com/yassir-kb/carte_aux_tresors.git
cd carte-aux-tresors
```

### 2. Construire le projet

Assurez-vous que Maven est installé et configuré dans votre environnement.

```bash
mvn clean install
```

### 3. Exécuter l'application

Vous pouvez exécuter l'application en utilisant Maven ou en démarrant le JAR généré.

#### Avec Maven

```bash
mvn spring-boot:run
```

#### Avec le JAR

Après avoir construit le projet, un fichier JAR sera généré dans le dossier `target`.

```bash
java -jar target/carte-aux-tresors-1.0.0.jar
```

### 4. Accéder à l'application

Ouvrez votre navigateur et accédez à l'URL suivante :

```
http://localhost:8082/
```

## Utilisation

### Interface Web

1. **Page d'accueil** : Vous verrez une page vous invitant à télécharger un fichier d'entrée.

2. **Télécharger le fichier d'entrée** : Cliquez sur "Choisir un fichier" et sélectionnez votre fichier d'entrée au format texte.

    - Le fichier doit respecter le format spécifié (voir [Format du fichier d'entrée](#format-du-fichier-dentrée)).

3. **Exécuter la simulation** : Cliquez sur "Simuler". L'application exécutera la simulation et affichera le résultat sur la page suivante.

4. **Télécharger le fichier de sortie** : Vous pouvez télécharger le fichier de sortie en cliquant sur le lien fourni.

### Format du fichier d'entrée

Le fichier d'entrée doit être un fichier texte contenant les informations suivantes :

- **Carte** :

  ```
  C - Largeur - Hauteur
  ```

- **Montagnes** :

  ```
  M - X - Y
  ```

- **Trésors** :

  ```
  T - X - Y - Nombre de trésors
  ```

- **Aventuriers** :

  ```
  A - Nom - X - Y - Orientation - Séquence de mouvements
  ```

#### Exemple :

```
C - 3 - 4
M - 1 - 0
M - 2 - 1
T - 0 - 3 - 2
T - 1 - 3 - 3
A - Lara - 1 - 1 - S - AADADAGGA
```

### Règles de la simulation

- **Déplacement des aventuriers** :

    - `A` : Avancer
    - `G` : Tourner à gauche
    - `D` : Tourner à droite

- **Collecte de trésors** :

    - Lorsqu'un aventurier se déplace sur une case contenant un ou plusieurs trésors, il ramasse un trésor. Le trésor est retiré de la case.

- **Obstacles** :

    - Les aventuriers ne peuvent pas se déplacer sur une case contenant une montagne ou un autre aventurier.
    - Les mouvements en dehors de la carte sont ignorés.

## Tests

Des tests unitaires ont été écrits pour assurer la fiabilité de l'application.

### Exécution des tests

Pour exécuter les tests, utilisez la commande Maven suivante :

```bash
mvn test
```

Les tests couvrent les principales classes du modèle et du service, en vérifiant les cas standard et les cas d'erreur.

### Rapport de couverture

Vous pouvez générer un rapport de couverture de code avec JaCoCo en exécutant :

```bash
mvn clean test jacoco:report
```

Le rapport sera disponible dans `target/site/jacoco/index.html`.

---

© 2024 Yassir EL KOBI