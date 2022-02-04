package ships;

public class Battleship extends AbstractShip {

    public Battleship() { // Ce constructeur fait appel au constructeur de la classe mère afin d'initialiser un Battleship car tous les navires ont les mêmes caractéristiques mais avec des données différentes.
        super("Battleship", 'B', 4, Direction.EAST); // L'EST est l'orientation par défaut des navires.
    }

    public Battleship(Direction orientation) {
        super("Battleship", 'B', 4, orientation);
    } // Ce constructeur fait appel au constructeur de la classe mère, mais ici il permet de donner l'orientation que l'on prend en paramètre à un navire.
}
