package board;

import ships.AbstractShip;
import ships.Direction;

public class Board implements IBoard {
    private String nom;
    private Character[][] navires;
    private boolean[][] frappes;
    private final int tailleGrille;

    public Board(String nom, int tailleGrille) {
        this.nom = nom;
        this.tailleGrille = tailleGrille;
        navires = new Character[this.tailleGrille][this.tailleGrille];
        frappes = new boolean[this.tailleGrille][this.tailleGrille];
    }

    public Board(String nom) {
        this.nom = nom;
        this.tailleGrille = 10;
        navires = new Character[this.tailleGrille][this.tailleGrille];
        frappes = new boolean[this.tailleGrille][this.tailleGrille];
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
            if (i == this.tailleGrille) {
                if (navires[i][j] != null) {
                    System.out.print(" " + navires[i][j]);
                } else {
                    System.out.print(" .");
                }
                j++;
            }
            while (j < this.tailleGrille) {
                if (navires[i][j] != null) {
                    System.out.print("  " + navires[i][j]);
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
        for (int i = 1; i < 11; i++) {
            System.out.print(i);
            int j = 0;
            if (i == 10) {
                System.out.print(" .");
                j++;
            }
            while (j < 10) {
                System.out.print("  .");
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
    public void putShip(AbstractShip ship, int x, int y) {
        if (x > 0 && y > 0) {
            int realX = x - 1;
            int realY = y - 1;
            int longueurShip = ship.getLongueurShip();
            char labelShip = ship.getLabel();
            if (ship.getOrientation() == Direction.EAST) {
                for (int i = 0; i < longueurShip; i++) {
                    if (realX + i < this.tailleGrille && navires[realY][realX + i] == null) {
                        navires[realY][realX + i] = labelShip;
                    }
                }
            } else if (ship.getOrientation() == Direction.WEST) {
                for (int i = 0; i < longueurShip; i++) {
                    if (realX - i >= 0 && navires[realY][realX - i] == null) {
                        navires[realY][realX - i] = labelShip;
                    }
                }
            } else if (ship.getOrientation() == Direction.NORTH) {
                for (int i = 0; i < longueurShip; i++) {
                    if (realY + i < this.tailleGrille && navires[realY + i][realX] == null) {
                        navires[realY + i][realX] = labelShip;
                    }
                }
            } else if (ship.getOrientation() == Direction.SOUTH) {
                for (int i = 0; i < longueurShip; i++) {
                    if (realY - i >= 0 && navires[realY - i][realX] == null) {
                        navires[realY - i][realX] = labelShip;
                    }
                }
            }
        }
    }

    @Override
    public boolean hasShip(int x, int y) {
        return false;
    }

    @Override
    public void setHit(boolean hit, int x, int y) {

    }

    @Override
    public Boolean getHit(int x, int y) {
        return null;
    }
}
