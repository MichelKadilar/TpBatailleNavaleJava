package game;

import board.Board;
import board.Hit;
import board.ShipStruckAtSamePosError;
import color.ColorUtil;
import player.AIPlayer;
import player.Player;
import ships.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.prefs.BackingStoreException;

public class Game {

    /* ***
     * Constante
     */
    public static final File SAVE_FILE = new File("savegame.dat");

    /* ***
     * Attributs
     */
    private static Game singleton = new Game();
    private Player player1;
    private Player player2;
    private Scanner sin;

    /* ***
     * Constructeurs
     */
    private Game() {
        // Je laisse ce constructeur vide car on initialise tout dans init, qui renvoie l'instance courante de la classe Game, utilisée par la méthode run().
    }

    public Game init() {
        if (!loadSave()) {
            // init attributes
            System.out.println("entre ton nom, joueur 1 :");
            // TODO use a scanner to read player name
            sin = new Scanner(System.in);
            String userName = sin.nextLine();
            System.out.println("entre ton nom, joueur 2 :");
            String userName2 = sin.nextLine();
            // TODO init boards
            Board b1 = new Board(userName);
            Board b2 = new Board(userName2);
            // TODO init this.player1 & this.player2
            this.player1 = new Player(b1, b2, createDefaultShips());
            this.player2 = new Player(b2, b1, createDefaultShips());
            System.out.println("Board du joueur : " + userName);
            b1.print();
            System.out.println("Board du joueur : " + userName2);
            b2.print();
            // place player ships
            System.out.println("Veuillez placer vos bateaux, " + userName);
            player1.putShips();
            System.out.println("Veuillez placer vos bateaux, " + userName2);
            player2.putShips();
        }
        return this;
    }

    /* ***
     * Méthodes
     */
    public void run() throws ShipStruckAtSamePosError {
        int[] coords = new int[2];
        Board b1 = player1.board;
        Board b2 = player2.board;
        Hit hit;

        // main loop
        b1.print();
        boolean done;
        do {
            System.out.println("Nom du joueur 1 : " + b1.nom);
            b1.print();
            hit = this.player1.sendHit(coords); // TODO player1 send a hit
            boolean strike = hit != Hit.MISS; // TODO set this hit on his board (b1)
            b1.setHit(strike, coords[0], coords[1]);
            done = updateScore();
            b1.print();
            System.out.println(makeHitMessage(false /* outgoing hit */, coords, hit));

            save();

            if (!done && !strike) {
                do {
                    System.out.println("Nom du joueur 2 : " + b2.nom);
                    hit = this.player2.sendHit(coords); // TODO player2 send a hit.
                    strike = hit != Hit.MISS;
                    b2.setHit(strike, coords[0], coords[1]);
                    done = updateScore();
                    b2.print();
                    System.out.println(makeHitMessage(true /* incoming hit */, coords, hit));
                    if (!done) {
                        save();
                    }
                } while (strike && !done);
            }

        } while (!done);

        SAVE_FILE.delete();
        System.out.println(String.format("joueur %d gagne", player1.lose ? 2 : 1));
        sin.close();
    }


    private void save() {
        /*try {
            // TODO bonus 2 : uncomment
            //  if (!SAVE_FILE.exists()) {
            //      SAVE_FILE.getAbsoluteFile().getParentFile().mkdirs();
            //  }

            // TODO bonus 2 : serialize players

        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }

    private boolean loadSave() {
        if (SAVE_FILE.exists()) {
            /*try {
                // TODO bonus 2 : deserialize players

                return true;
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }*/
        }
        return false;
    }

    private boolean updateScore() {
        for (Player player : new Player[]{player1, player2}) {
            int destroyed = 0;
            for (AbstractShip ship : player.getShips()) {
                if (ship.isSunk()) {
                    destroyed++;
                }
            }

            player.destroyedCount = destroyed;
            player.lose = destroyed == player.getShips().length;
            if (player.lose) {
                return true;
            }
        }
        return false;
    }

    private String makeHitMessage(boolean incoming, int[] coords, Hit hit) {
        String msg;
        ColorUtil.Color color = ColorUtil.Color.RESET;
        switch (hit) {
            case MISS:
                msg = hit.toString();
                break;
            case STRIKE:
                msg = hit.toString();
                color = ColorUtil.Color.RED;
                break;
            default:
                msg = hit.toString() + " coulé";
                color = ColorUtil.Color.RED;
        }
        msg = String.format("%s Frappe en %c%d : %s", incoming ? "<=" : "=>",
                ((char) ('A' + coords[0])),
                (coords[1] + 1), msg);
        return ColorUtil.colorize(msg, color);
    }

    private static List<AbstractShip> createDefaultShips() {
        return Arrays.asList(new AbstractShip[]{new Destroyer(), new Submarine(), new Submarine(), new Battleship(), new Carrier()});
    }

    public static void main(String args[]) throws ShipStruckAtSamePosError {
        Game game = Game.getInstance();
        game.init().run();
    }

    public static Game getInstance() {
        if (singleton == null) {
            singleton = new Game();
        }
        return singleton;
    }
}
