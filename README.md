# TraceTech

## Présentation

Salut !! ! Je suis Azangue Leonel Delmat, étudiant en 3ème année de Génie Informatique à l'École Nationale Supérieure Polytechnique de Yaoundé. Ce projet, TraceTech, a été réalisé dans le cadre d'un TP de l'unité d'enseignement Programmation Orientée Objet Java (POO 2).

L'objectif de TraceTech est de fournir une plateforme centralisée pour signaler les objets volés et faciliter leur recherche.

## Table des matières

- [TraceTech](#tracetech)
  - [Présentation](#présentation)
  - [Table des matières](#table-des-matières)
  - [Contexte du projet](#contexte-du-projet)
  - [Fonctionnalités](#fonctionnalités)
  - [Technologies utilisées](#technologies-utilisées)
  - [Installation](#installation)
  - [Utilisation](#utilisation)
  - [Architecture du projet](#architecture-du-projet)
  - [Améliorations possibles](#améliorations-possibles)
  - [Auteur](#auteur)
  - [Contact](#contact)

## Contexte du projet

Ce projet a été développé dans le cadre d'un TP pour l'unité d'enseignement Programmation Orientée Objet (POO 2) en 3ème année de Génie Informatique à l'ENSPY. L'objectif était de mettre en pratique les concepts de la programmation orientée objet en Java, en particulier l'utilisation de classes, d'interfaces, d'héritage, de polymorphisme, de gestion des exceptions et de persistance des données.

## Fonctionnalités

TraceTech offre les fonctionnalités suivantes :

*   **Authentification :**
    *   Inscription des utilisateurs avec validation des informations.
    *   Connexion sécurisée avec hachage des mots de passe.
*   **Signalement de vol :**
    *   Formulaire de signalement de vol complet (type d'objet, marque, modèle, numéro de série, IMEI, date du vol, description détaillée, photos de l'objet, preuve d'achat).
    *   Possibilité d'ajouter des photos et une preuve d'achat (facultatif).
*   **Recherche d'objets :** (Fonctionnalité à implémenter)
    *   Interface de recherche intuitive avec des critères pertinents (type d'objet, marque, modèle, etc.).
*   **Gestion des utilisateurs :** (Fonctionnalité à implémenter)
    *   Profil utilisateur avec historique des objets signalés.
*   **Interface utilisateur :**
    *   Interface graphique conviviale et facile à utiliser.

## Technologies utilisées

*   **Langage :** Java
*   **Framework :** JavaFX pour l'interface utilisateur
*   **Base de données :** SQLite pour la persistance des données
*   **Outils de développement :**
    *   IntelliJ IDEA (ou autre IDE Java)
* **Dependences :**
  * JavaFx 23.01
  * SQLITE

## Installation

1.  Clonez le dépôt GitHub :
    ```
    git clone https://github.com/Delmat237/TraceTech.git
    ```
2.  Ouvrez le projet dans votre IDE Java (par exemple, IntelliJ IDEA).
3.  Assurez-vous d'avoir installé les dépendances nécessaires (JavaFX, SQLite JDBC driver). ou utiliser Maven
4.  Compilez et exécutez le projet.

## Utilisation

1.  Lancez l'application.
2.  Créez un compte utilisateur ou connectez-vous avec un compte existant.
3.  Pour signaler un vol, accédez à la section "Signaler un vol" et remplissez le formulaire.
4.  Pour rechercher un objet, accédez à la section "Rechercher un objet" et utilisez les critères de recherche disponibles.

## Architecture du projet

L'architecture du projet est basée sur les principes de la programmation orientée objet et suit une structure MVC (Modèle-Vue-Contrôleur) :

*   **Modèle :**
    *   Contient les classes de données (par exemple, `User`, `StolenItem`).
*   **Vue :**
    *   Contient les fichiers FXML qui définissent l'interface utilisateur.
*   **Contrôleur :**
    *   Contient les classes Java qui gèrent l'interaction entre le modèle et la vue.
*   **Service :**
    *   Contient les classes qui implémentent la logique métier (par exemple, `AuthService`, `StolenItemService`).

## Améliorations possibles

*   Implémenter la fonctionnalité de recherche d'objets.
*   Implémenter la gestion des utilisateurs et les profils utilisateurs.
*   Ajouter des validations plus poussées des données saisies par l'utilisateur.
*   Implémenter un système de notifications pour informer les utilisateurs lorsqu'un objet correspondant à leur recherche est signalé.
*   Améliorer l'interface utilisateur pour la rendre plus intuitive et conviviale.
*   Ajouter des tests unitaires pour améliorer la qualité du code.

## Auteur

*   Azangue Leonel Delmat
*  Etudiant en 3 GI À L'ENSPY

## Contact

*   **WhatsApp :** +237657450314
*   **Email :** azangueleonel9@gmail.com
*   **LinkedIn :** [leonel-azangue](https://www.linkedin.com/in/leonel-azangue)
