package board;

import color.ColorUtil;
import ships.AbstractShip;
import ships.Direction;
import ships.ShipState;

import java.io.Serializable;

public class Board implements IBoard, Serializable {
    public String nom;
    private ShipState[][] navires; // Afin que seule cette classe ait accès à ce tableau 2D
    private Boolean[][] frappes; // Idem
    public final int tailleGrille; // Final car la taille de la grille ne va pas changer au cours de la partie
    // Pas static car sinon on devrait avoir des constructeurs statiques pour initialiser la valeur de ce champ avec l'entrée utilisateur

    public Board(String nom, int tailleGrille) { // On crée un board de taille tailleGrille * tailleGrille qui contient le terrain où sont placés les navires et le terrain où sont on tire
        this.nom = nom;
        if (tailleGrille >= 10) {
            this.tailleGrille = tailleGrille;
        } else {
            this.tailleGrille = 10;
        }
        navires = new ShipState[this.tailleGrille][this.tailleGrille];
        frappes = new Boolean[this.tailleGrille][this.tailleGrille];
    }

    public Board(String nom) { // On crée un board de taille 10 contenant le terrain des navires et le terrain des tirs
        this.nom = nom;
        this.tailleGrille = 10;
        navires = new ShipState[this.tailleGrille][this.tailleGrille];
        frappes = new Boolean[this.tailleGrille][this.tailleGrille];
    }

    public void print() { // Fonction qui affiche le board complet (terrain navires + terrain tirs) en utilisant deux sous-fonctions, une pour chaque terrain.
        printGrilleNavires();
        System.out.println(); // Saut de ligne entre les deux terrains
        printGrilleFrappes();
    }

    private void printGrilleNavires() { // Affichage du terrain où sont placés les navires
        System.out.println("Navires :");
        System.out.print("   ");
        for (char c = 'A'; c < 'A' + this.tailleGrille; c++) { // On affiche les lettres de l'alphabet en majuscule en haut, selon la taille de la grille
            System.out.print(c + "  ");
        }
        System.out.println();
        for (int i = 0; i < this.tailleGrille; i++) { // Parcours des lignes de la grille pour afficher les navires selon leur label
            System.out.print(i + 1); // Affichage des numéros de lignes tout à gauche
            int j = 0;
            if (i >= 9) { // Traitement du premier caractère d'affichage à la ligne courante dans le cas où i >=9.
                // Si i >= 9 (donc à partir de la 10ème ligne, comme notre tableau commence à 0).
                if (navires[i][j] != null) { // on affiche le label d'un navire si le navire existe.
                    System.out.print(" " + navires[i][j].toString()); // Affichage du label du navire
                } else {
                    System.out.print(" .");  // sinon un point.
                }
                j++;
            }
            while (j < this.tailleGrille) { // Affichage du reste des caractères de la ligne courante (donc affichage des colonnes de la ligne).
                if (navires[i][j] != null) { // on affiche le label d'un navire si le navire existe.
                    System.out.print("  " + navires[i][j].toString()); // Affichage du label du navire
                } else {
                    System.out.print("  .");  // sinon un point.
                }
                j++;
            }
            System.out.println();
        }
    }

    private void printGrilleFrappes() { // Affichage du terrain où sont indiqués les tirs effectués
        System.out.println("Frappes :");
        System.out.print("   ");
        for (char c = 'A'; c < 'A' + this.tailleGrille; c++) { // On affiche les lettres de l'alphabet en majuscule en haut, selon la taille de la grille
            System.out.print(c + "  ");
        }
        System.out.println();
        for (int i = 0; i < this.tailleGrille; i++) { // Parcours des lignes de la grille pour afficher les tirs selon leur état (touché, manqué, pas encore tiré).
            System.out.print(i + 1); // Affichage des numéros de lignes tout à gauche
            int j = 0;
            if (i >= 9) {// Traitement du premier caractère d'affichage à la ligne courante dans le cas où i >=9.
                // Si i >= 9 (donc à partir de la 10ème ligne, comme notre tableau commence à 0), on affiche l'état du tir.
                if (Boolean.FALSE.equals(frappes[i][j])) { // Soit une croix blanche si le tir est manqué.
                    System.out.print(" X");
                } else if (Boolean.TRUE.equals(frappes[i][j])) { // Soit une croix rouge si le tir est touché.
                    System.out.print(" " + ColorUtil.colorize("X", ColorUtil.Color.RED));
                } else { // Soit un point si on n'a pas encore tiré aux coordonnées (x,y).
                    System.out.print(" .");
                }
                j++;
            }
            while (j < this.tailleGrille) { // Affichage du reste des caractères de la ligne courante (donc affichage des colonnes de la ligne).
                if (Boolean.FALSE.equals(frappes[i][j])) { // Soit une croix blanche si le tir est manqué.
                    System.out.print("  X");
                } else if (Boolean.TRUE.equals(frappes[i][j])) { // Soit une croix rouge si le tir est touché.
                    System.out.print("  " + ColorUtil.colorize("X", ColorUtil.Color.RED));
                } else { // Soit un point si on n'a pas encore tiré aux coordonnées (x,y).
                    System.out.print("  .");
                }
                j++;
            }
            System.out.println();
        }
    }

    @Override
    public int getSize() {
        return this.tailleGrille;
    }

    @Override
    public void putShip(AbstractShip ship, int x, int y) throws ShipPosOutOfBoardError, ShipSuperpositionError { //  Permet de placer un navire
        if (x >= 0 && y >= 0 && x < this.tailleGrille && y < this.tailleGrille) { // Si les coordonnées où l'on veut placer un navire se trouvent bien dans la grille.
            int longueurShip = ship.getLongueurShip();
            if (ship.getOrientation() == Direction.EAST) { // Si le navire est orienté vers l'EST.
                for (int i = 0; i < longueurShip; i++) { // Parcours de toute la longueur du navire
                    if (x + i < this.tailleGrille) { // Si un navire peut se placer en fonction de sa taille sans dépasser de la grille
                        // on ajoute i à x car l'EST est vers la droite de la grille, on veut donc placer le navire de gauche à droite.
                        if ((!this.hasShip(x + i, y))) { // S'il n'y a pas de navire positionné à cet endroit
                            this.navires[y][x + i] = new ShipState(ship, false); // Placement du navire à cet endroit.
                        } else {
                            throw new ShipSuperpositionError("Votre navire : " + ship.getShipName() + " Se superpose sur un autre aux positions : x=" + (x + i) + " et y=" + y); // Superposition, on lève une exception qui l'indique
                        }
                    } else {
                        throw new ShipPosOutOfBoardError("Votre navire : " + ship.getShipName() + " Sort du terrain aux positions : x=" + (x + i) + " et y=" + y); // Dépassement de la grille, on lève une exception qui l'indique
                    }
                }
            } else if (ship.getOrientation() == Direction.WEST) { // Si le navire est orienté vers l'OUEST.
                for (int i = 0; i < longueurShip; i++) { // Parcours de toute la longueur du navire
                    if (x - i >= 0) { // Si un navire peut se placer en fonction de sa taille sans dépasser de la grille
                        // on retire i à x car l'OUEST est vers la droite de la grille, on veut donc placer le navire de droite à gauche.
                        if ((!this.hasShip(x - i, y))) { // S'il n'y a pas de navire positionné à cet endroit
                            navires[y][x - i] = new ShipState(ship, false); // Placement du navire à cet endroit.
                        } else {
                            throw new ShipSuperpositionError("Votre navire : " + ship.getShipName() + " Se superpose sur un autre aux positions : x=" + (x - i) + " et y=" + y); // Superposition, on lève une exception qui l'indique
                        }
                    } else {
                        throw new ShipPosOutOfBoardError("Votre navire : " + ship.getShipName() + " Sort du terrain aux positions : x=" + (x - i) + " et y=" + y); // Dépassement de la grille, on lève une exception qui l'indique
                    }
                }
            } else if (ship.getOrientation() == Direction.SOUTH) { // Si le navire est orienté vers le SUD.
                for (int i = 0; i < longueurShip; i++) { // Parcours de toute la longueur du navire
                    if (y + i < this.tailleGrille) { // Si un navire peut se placer en fonction de sa taille sans dépasser de la grille
                        // on ajoute i à y car le SUD est vers la bas de la grille, on veut donc placer le navire de haut en bas.
                        if ((!this.hasShip(x, y + i))) { // S'il n'y a pas de navire positionné à cet endroit
                            navires[y + i][x] = new ShipState(ship, false); // Placement du navire à cet endroit.
                        } else {
                            throw new ShipSuperpositionError("Votre navire : " + ship.getShipName() + " Se superpose sur un autre aux positions : x=" + x + " et y=" + (y + i)); // Superposition, on lève une exception qui l'indique
                        }
                    } else {
                        throw new ShipPosOutOfBoardError("Votre navire : " + ship.getShipName() + " Sort du terrain aux positions : x=" + x + " et y=" + (y + i)); // Dépassement de la grille, on lève une exception qui l'indique
                    }
                }
            } else if (ship.getOrientation() == Direction.NORTH) { // Si le navire est orienté vers le NORD.
                for (int i = 0; i < longueurShip; i++) { // Parcours de toute la longueur du navire
                    if (y - i >= 0) { // Si un navire peut se placer en fonction de sa taille sans dépasser de la grille
                        // on retire i à y car le NORD est vers le haut de la grille, on veut donc placer le navire de bas en haut.
                        if ((!this.hasShip(x, y - i))) { // S'il n'y a pas de navire positionné à cet endroit
                            navires[y - i][x] = new ShipState(ship, false); // Placement du navire à cet endroit.
                        } else {
                            throw new ShipSuperpositionError("Votre navire : " + ship.getShipName() + " Se superpose sur un autre aux positions : x=" + x + " et y=" + (y - i)); // Superposition, on lève une exception qui l'indique
                        }
                    } else {
                        throw new ShipPosOutOfBoardError("Votre navire : " + ship.getShipName() + " Sort du terrain aux positions : x=" + x + " et y=" + (y - i)); // Dépassement de la grille, on lève une exception qui l'indique
                    }
                }
            }
        } else {
            throw new ShipPosOutOfBoardError("Votre navire : " + ship.getShipName() + " Sort du terrain aux positions : x=" + x + " et y=" + y); // Dépassement de la grille, on lève une exception qui l'indique
        }
    }

    @Override
    public boolean hasShip(int x, int y) {
        return navires[y][x] != null && !navires[y][x].getShip().isSunk(); // S'il n'y a pas de navire ou s'il a coulé, on retourne false, sinon on retourne true.
    }

    @Override
    public void setHit(boolean hit, int x, int y) throws ShipStruckAtSamePosError {
        if (frappes[y][x] != null) { // S'il y a déjà une frappe effectuée ici
            throw new ShipStruckAtSamePosError("Vous avez déjà tiré aux positions x = " + x + " et y = " + y); // Frappe répétitive, on lève une exception
        } else {
            frappes[y][x] = hit; // On affecte à la grille des frappes l'état du tir effectué (manqué, touché)
        }
    }

    @Override
    public Boolean getHit(int x, int y) {
        return frappes[y][x];
    }

    @Override
    public Hit sendHit(int x, int y) { // On tire et on renvoie le résultat du tir (touché, manqué, navire coulé, et si oui, lequel).
        if (navires[y][x] != null) { // S'il y a un navire à cet endroit
            char currentShipLabel = navires[y][x].getShip().getLabel();
            this.navires[y][x].addStrike(); // On ajjoute une frappe effectuée sur un navire
            boolean isCurrentShipSunk = navires[y][x].getShip().isSunk();
            if (currentShipLabel == 'D') { // Si le navire est un destroyer
                if (isCurrentShipSunk) { // S'il on l'a coulé
                    return Hit.DESTROYER;
                }
            } else if (currentShipLabel == 'B') { // Si le navire est un battleship
                if (isCurrentShipSunk) {  // S'il on l'a coulé
                    return Hit.BATTLESHIP;
                }
            } else if (currentShipLabel == 'S') { // Si le navire est un submarine
                if (isCurrentShipSunk) {  // S'il on l'a coulé
                    return Hit.SUBMARINE;
                }
            } else if (currentShipLabel == 'C') { // Si le navire est un carrier
                if (isCurrentShipSunk) {  // S'il on l'a coulé
                    return Hit.CARRIER;
                }
            }
            return Hit.STRIKE;
        } else { // S'il n'y a pas de navire, alors le tir est manqué
            return Hit.MISS;
        }
    }
}
