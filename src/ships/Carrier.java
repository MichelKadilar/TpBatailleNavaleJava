package ships;

public class Carrier extends AbstractShip {

    public Carrier() { // Ce constructeur fait appel au constructeur de la classe mère afin d'initialiser un Carrier car tous les navires ont les mêmes caractéristiques mais avec des données différentes.
        super("Carrier", 'C', 5, Direction.EAST); // L'EST est l'orientation par défaut des navires.
    }

    public Carrier(Direction orientation) {
        super("Carrier", 'C', 5, orientation);
    }
    // Ce constructeur fait appel au constructeur de la classe mère, mais ici il permet de donner l'orientation que l'on prend en paramètre à un navire.
}
