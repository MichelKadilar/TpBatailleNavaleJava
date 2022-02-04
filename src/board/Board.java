package board;

import color.ColorUtil;
import ships.AbstractShip;
import ships.Direction;
import ships.ShipState;

public class Board implements IBoard {
    private String nom;
    private ShipState[][] navires;
    private Boolean[][] frappes;
    private final int tailleGrille;

    public Board(String nom, int tailleGrille) {
        this.nom = nom;
        if (tailleGrille >= 10) {
            this.tailleGrille = tailleGrille;
        } else {
            this.tailleGrille = 10;
        }
        navires = new ShipState[this.tailleGrille][this.tailleGrille];
        frappes = new Boolean[this.tailleGrille][this.tailleGrille];
    }

    public Board(String nom) {
        this.nom = nom;
        this.tailleGrille = 10;
        navires = new ShipState[this.tailleGrille][this.tailleGrille];
        frappes = new Boolean[this.tailleGrille][this.tailleGrille];
    }

    public void print() {
        printGrilleNavires();
        System.out.println();
        printGrilleFrappe();
    }

    private void printGrilleNavires() {
        System.out.println("Navires :");
        char[] tabAlphabet = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J'};
        System.out.print("   ");
        for (char c : tabAlphabet) {
            System.out.print(c + "  ");
        }
        System.out.println();
        for (int i = 0; i < this.tailleGrille; i++) {
            System.out.print(i + 1);
            int j = 0;
            if (i == this.tailleGrille - 1) {
                if (navires[i][j] != null) {
                    System.out.print(" " + navires[i][j].toString());
                } else {
                    System.out.print(" .");
                }
                j++;
            }
            while (j < this.tailleGrille) {
                if (navires[i][j] != null) {
                    System.out.print("  " + navires[i][j].toString());
                } else {
                    System.out.print("  .");
                }
                j++;
            }
            System.out.println();
        }
    }

    private void printGrilleFrappe() {
        System.out.println("Frappes :");
        char[] tabAlphabet = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J'};
        System.out.print("   ");
        for (char c : tabAlphabet) {
            System.out.print(c + "  ");
        }
        System.out.println();
        for (int i = 0; i < this.tailleGrille; i++) {
            System.out.print(i + 1);
            int j = 0;
            if (i == this.tailleGrille - 1) {
                if (Boolean.FALSE.equals(frappes[i][j])) {
                    System.out.print(" X");
                } else if (Boolean.TRUE.equals(frappes[i][j])) {
                    System.out.print(" " + ColorUtil.colorize("X", ColorUtil.Color.RED));
                } else {
                    System.out.print(" .");
                }
                j++;
            }
            while (j < this.tailleGrille) {
                if (Boolean.FALSE.equals(frappes[i][j])) {
                    System.out.print("  X");
                } else if (Boolean.TRUE.equals(frappes[i][j])) {
                    System.out.print("  " + ColorUtil.colorize("X", ColorUtil.Color.RED));
                } else {
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
    public void putShip(AbstractShip ship, int x, int y) throws ShipPosOutOfBoardError, ShipSuperpositionError {
        if (x >= 0 && y >= 0) {
            int longueurShip = ship.getLongueurShip();
            if (ship.getOrientation() == Direction.EAST) {
                for (int i = 0; i < longueurShip; i++) {
                    if (x + i < this.tailleGrille) {
                        if ((!this.hasShip(x + i, y))) {
                            this.navires[y][x + i] = new ShipState(ship, false);
                        } else {
                            throw new ShipSuperpositionError("Votre navire : " + ship.getShipName() + " Se superpose sur un autre aux positions : x=" + (x + i) + " et y=" + y);
                        }
                    } else {
                        throw new ShipPosOutOfBoardError("Votre navire : " + ship.getShipName() + " Sort du terrain aux positions : x=" + (x + i) + " et y=" + y);
                    }
                }
            } else if (ship.getOrientation() == Direction.WEST) {
                for (int i = 0; i < longueurShip; i++) {
                    if (x - i >= 0) {
                        if ((!this.hasShip(x - i, y))) {
                            navires[y][x - i] = new ShipState(ship, false);
                        } else {
                            throw new ShipSuperpositionError("Votre navire : " + ship.getShipName() + " Se superpose sur un autre aux positions : x=" + (x - i) + " et y=" + y);
                        }
                    } else {
                        throw new ShipPosOutOfBoardError("Votre navire : " + ship.getShipName() + " Sort du terrain aux positions : x=" + (x - i) + " et y=" + y);
                    }
                }
            } else if (ship.getOrientation() == Direction.SOUTH) {
                for (int i = 0; i < longueurShip; i++) {
                    if (y + i < this.tailleGrille) {
                        if ((!this.hasShip(x, y + i))) {
                            navires[y + i][x] = new ShipState(ship, false);
                        } else {
                            throw new ShipSuperpositionError("Votre navire : " + ship.getShipName() + " Se superpose sur un autre aux positions : x=" + x + " et y=" + (y + i));
                        }
                    } else {
                        throw new ShipPosOutOfBoardError("Votre navire : " + ship.getShipName() + " Sort du terrain aux positions : x=" + x + " et y=" + (y + i));
                    }
                }
            } else if (ship.getOrientation() == Direction.NORTH) {
                for (int i = 0; i < longueurShip; i++) {
                    if (y - i >= 0) {
                        if ((!this.hasShip(x, y - i))) {
                            navires[y - i][x] = new ShipState(ship, false);
                        } else {
                            throw new ShipSuperpositionError("Votre navire : " + ship.getShipName() + " Se superpose sur un autre aux positions : x=" + x + " et y=" + (y - i));
                        }
                    } else {
                        throw new ShipPosOutOfBoardError("Votre navire : " + ship.getShipName() + " Sort du terrain aux positions : x=" + x + " et y=" + (y - i));
                    }
                }
            }
        } else {
            throw new ShipPosOutOfBoardError("Votre navire : " + ship.getShipName() + " Sort du terrain aux positions : x=" + x + " et y=" + y);
        }
    }

    @Override
    public boolean hasShip(int x, int y) { // L'écart de -1 dans les coordonnées est pris en compte directement lors de l'appel à cette fonction
        return navires[y][x] != null && !navires[y][x].getShip().isSunk();
    }

    @Override
    public void setHit(boolean hit, int x, int y) throws ShipStruckAtSamePosError {
        if (frappes[y][x] != null) {
            throw new ShipStruckAtSamePosError("Vous avez déjà tiré aux positions x = " + x + " et y = " + y);
        } else {
            frappes[y][x] = hit;
        }
    }

    @Override
    public Boolean getHit(int x, int y) {
        return frappes[y][x];
    }

    @Override
    public Hit sendHit(int x, int y) {
        if (navires[y][x] != null) {
            char currentShipLabel = navires[y][x].getShip().getLabel();
            this.navires[y][x].addStrike();
            boolean isCurrentShipSunk = navires[y][x].getShip().isSunk();
            if (currentShipLabel == 'D') {
                if (isCurrentShipSunk) {
                    return Hit.DESTROYER;
                }
            } else if (currentShipLabel == 'B') {
                if (isCurrentShipSunk) {
                    return Hit.BATTLESHIP;
                }
            } else if (currentShipLabel == 'S') {
                if (isCurrentShipSunk) {
                    return Hit.SUBMARINE;
                }
            } else if (currentShipLabel == 'C') {
                if (isCurrentShipSunk) {
                    return Hit.CARRIER;
                }
            }
            return Hit.STRIKE;
        } else {
            return Hit.MISS;
        }
    }
}
