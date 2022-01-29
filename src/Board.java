public class Board {
    protected String nom;
    private Character[][] navires;
    private boolean[][] frappes;
    private final int tailleGrille;

    public Board(String nom, int tailleGrille) {
        this.nom = nom;
        this.tailleGrille = tailleGrille;
    }

    public Board(String nom) {
        this.nom = nom;
        this.tailleGrille = 10;
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

    public int getTailleGrille() {
        return this.tailleGrille;
    }
}
