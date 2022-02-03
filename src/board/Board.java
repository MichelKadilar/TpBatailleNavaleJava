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
        this.tailleGrille = tailleGrille;
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
                    System.out.print(" " + navires[i][j].getShip().getLabel());
                } else {
                    System.out.print(" .");
                }
                j++;
            }
            while (j < this.tailleGrille) {
                if (navires[i][j] != null) {
                    System.out.print("  " + navires[i][j].getShip().getLabel());
                } else {
                    System.out.print("  .");
                }
                j++;
            }
            System.out.println();
        }
    }

    public void printGrilleFrappe() {
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
        if (x > 0 && y > 0) {
            int realX = x - 1;
            int realY = y - 1;
            int longueurShip = ship.getLongueurShip();
            if (ship.getOrientation() == Direction.EAST) {
                for (int i = 0; i < longueurShip; i++) {
                    if (realX + i < this.tailleGrille) {
                        if ((!this.hasShip(realX + i, realY))) {
                            this.navires[realY][realX + i] = new ShipState(ship, false);
                        } else {
                            throw new ShipSuperpositionError("Votre navire : " + ship.getShipName() + " Se superpose sur un autre aux positions : x=" + (realX + i) + " et y=" + realY);
                        }
                    } else {
                        throw new ShipPosOutOfBoardError("Votre navire : " + ship.getShipName() + " Sort du terrain aux positions : x=" + (realX + i) + " et y=" + realY);
                    }
                }
            } else if (ship.getOrientation() == Direction.WEST) {
                for (int i = 0; i < longueurShip; i++) {
                    if (realX - i >= 0) {
                        if ((!this.hasShip(realX - i, realY))) {
                            navires[realY][realX - i] = new ShipState(ship, false);
                        } else {
                            throw new ShipSuperpositionError("Votre navire : " + ship.getShipName() + " Se superpose sur un autre aux positions : x=" + (realX - i) + " et y=" + realY);
                        }
                    } else {
                        throw new ShipPosOutOfBoardError("Votre navire : " + ship.getShipName() + " Sort du terrain aux positions : x=" + (realX - i) + " et y=" + realY);
                    }
                }
            } else if (ship.getOrientation() == Direction.NORTH) {
                for (int i = 0; i < longueurShip; i++) {
                    if (realY + i < this.tailleGrille) {
                        if ((!this.hasShip(realX, realY + i))) {
                            navires[realY + i][realX] = new ShipState(ship, false);
                        } else {
                            throw new ShipSuperpositionError("Votre navire : " + ship.getShipName() + " Se superpose sur un autre aux positions : x=" + realX + " et y=" + (realY + i));
                        }
                    } else {
                        throw new ShipPosOutOfBoardError("Votre navire : " + ship.getShipName() + " Sort du terrain aux positions : x=" + realX + " et y=" + (realY + i));
                    }
                }
            } else if (ship.getOrientation() == Direction.SOUTH) {
                for (int i = 0; i < longueurShip; i++) {
                    if (realY - i >= 0) {
                        if ((!this.hasShip(realX, realY - i))) {
                            navires[realY - i][realX] = new ShipState(ship, false);
                        } else {
                            throw new ShipSuperpositionError("Votre navire : " + ship.getShipName() + " Se superpose sur un autre aux positions : x=" + realX + " et y=" + (realY - i));
                        }
                    } else {
                        throw new ShipPosOutOfBoardError("Votre navire : " + ship.getShipName() + " Sort du terrain aux positions : x=" + realX + " et y=" + (realY - i));
                    }
                }
            }
        }
    }

    @Override
    public boolean hasShip(int x, int y) { // L'écart de -1 dans les coordonnées est pris en compte directement dans la méthode putShip()
        // Avec realX et realY.
        if (navires[y][x] == null || navires[y][x].getShip().isSunk()) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void setHit(boolean hit, int x, int y) throws ShipStruckAtSamePosError {
        if (frappes[y - 1][x - 1] != null) {
            throw new ShipStruckAtSamePosError("Le navire : " + navires[y - 1][x - 1].getShip().getShipName() + " de votre adversaire a déjà été touché aux positions x = " + x + " et y = " + y);
        }
        hit = navires[y - 1][x - 1] != null;
        frappes[y - 1][x - 1] = hit;
        if (hit) {
            this.navires[y - 1][x - 1].addStrike();
        }
    }

    @Override
    public Boolean getHit(int x, int y) {
        return frappes[y - 1][x - 1];
    }

    @Override
    public Hit sendHit(int x, int y) throws ShipStruckAtSamePosError {
        try {
            this.setHit(false, x, y);
        } catch (ShipStruckAtSamePosError e) {
            System.out.println("Votre tir aux coordonnées x = " + x + " et y = " + y + " n'est pas valide car vous l'avez déjà réalisé auparavant");
        }
        if (Boolean.TRUE.equals(this.getHit(x, y))) {
            char currentShipLabel = navires[y - 1][x - 1].getShip().getLabel();
            boolean isCurrentShipSunk = navires[y - 1][x - 1].isSunk();
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
