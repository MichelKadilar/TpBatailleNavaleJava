package ships;

public class Submarine extends AbstractShip {

    public Submarine() {
        super("Submarine", 'S', 3, Direction.EAST);
    }
    // Ce constructeur fait appel au constructeur de la classe mère afin d'initialiser un Submarine car tous les navires ont les mêmes caractéristiques mais avec des données différentes.
    // L'EST est l'orientation par défaut des navires.

    public Submarine(Direction orientation) {
        super("Submarine", 'S', 3, orientation);
    }
    // Ce constructeur fait appel au constructeur de la classe mère, mais ici il permet de donner l'orientation que l'on prend en paramètre à un navire.
}
