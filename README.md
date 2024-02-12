# l2s4-projet-2023

# Equipe

- Lucas Deloison
- Seif-Eddin Bouguerouche
- Victor Leclercq
- Christian-Alexandre Amichia

# Sujet

[Le sujet 2023](https://www.fil.univ-lille.fr/~varre/portail/l2s4-projet/sujet2023.pdf)

# Livrables

## Livrable 1

### Atteinte des objectifs

- diagramme UML concernant les classes Ville/City et Carte/Map
- code correspondant
- les tests unitaires (code mais pas encore executables)
- lecture JSON ok

### Difficultés restant à résoudre

- tests pas encore réalisables via une ligne de commande

## Livrable 2

### Atteinte des objectifs

atteinte des objectifs fait

### Difficultés restant à résoudre

aucun

## Livrable 3

### Atteinte des objectifs

atteinte des objectifs fait

### Difficultés restant à résoudre

resoudre problème pour les cube

## Livrable 4

### Atteinte des objectifs

atteinte des objectifs fait

### Difficultés restant à résoudre

aucun

# Journal de bord

## Semaine 1

Création de la V1 du diagramme UML (comprenant pour le moment City, Board et ClassicalBoard).
Début des fonctions City.java, Board.java et ClassicalBoard.java également.

## Semaine 2

V2 du diagramme UML, début des fonctions tests de City et Board.

## Semaine 3

Lecture JSON ok, suite des fonctions tests, améliorations du code déjà présents dans les fonctions City.java et Board.java.

## Semaine 4

Création de la fonction Player.java.

## Semaine 5

Début des fonctions tests pour Player.java.
* Lucas : début de résolution pour lancer les tests déjà ok en ligne de commandes ("javac -classpath src:jars/junit-4.13.2.jar:jars/json-20220924.jar -d classes test/CityTest.java", il manque le jars du Junit dans le dossier jars/)
* Seif : finalisation UML pour les cards et sa gestion (possible changements)
* Victor : commencement des fonctions dans Player.java

## Semaine 6

* Lucas : Création des fonctions qui héritent de Player pour gérer les 4 différents rôles.
* Seif : début du developpement de ce qui tourne autour des cards.
* Victor : fin de player.java et commencement de PlayerTest.java

## Semaine 7

* Victor : Fin de PlayerTest.java et commencement de la mise en place des cards dans les fonctions (fichier Player) nécessitant l'utilisation de celle-ci (produit une erreur)
* Seif : fin de la modélisation des card et affichage dans le main + fin de l'uml


__Attention :__  Nous avons avancé sur le livrable 3, ainsi la nouvelle version des fichiers PlayerTest et Player ne sont pas à prendre en compte puisque la fonction FindACure ne fonctionne pas et ses tests également. Cependant les anciennes versions de ces fichiers correspondent aux attendus du livrable 2     
Pour vérification regarder à la date du 23 mars (il me semble):
* Player --> nouvelle version du commit "inutile"
* PlayerTest --> nouvelle version du commit "1 des constructeurs inutiles et donc adaptation dans PlayerTest"

## Semaine 8

* Victor : Correction de l'erreur produit dans Player et PlayerTest 
* Seif :  Mise en place de la gestion d'un carte epidemie + correction et finalisation des methodes de pioche dans le joueur

## Semaine 9
* Victor : Correction pour lancer lancer les tests depuis racine sans erreur
* Lucas : Réalisation des différentes fonctions restantes pour les 3 derniers rôles et commentaires manquants dans diverses fonctions
* Seif : methode play et finalisation UMl + preblème sur la phase d'infection qui ne semble pas vouloir augmenter 
* Problème concernant l'affichage de la propagation de la maladie ( vu avec vous les deux villes ne sont pas identiques donc problème pour changé les valeurs et je n'ai pas réussi a le résoudre).

## Semaine 10

* vacances (rien fait)

## Semaine 11

* vacances (rien fait)

## Semaine 12

* Seif : correction du projet avec ce qui est donné dans l'issue et mise en place du diapo pour la semaine prochaine ainsi que le make file mise a jour 