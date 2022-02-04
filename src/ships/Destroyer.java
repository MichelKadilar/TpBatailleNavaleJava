package ships;

public class Destroyer extends AbstractShip {

    public Destroyer() {
        super("Destroyer", 'D', 2, Direction.EAST);
    }
    // Ce constructeur fait appel au constructeur de la classe mère afin d'initialiser un Destroyer car tous les navires ont les mêmes caractéristiques mais avec des données différentes.
    // L'EST est l'orientation par défaut des navires.

    public Destroyer(Direction orientation) {
        super("Destroyer", 'D', 2, orientation);
    }
    // Ce constructeur fait appel au constructeur de la classe mère, mais ici il permet de donner l'orientation que l'on prend en paramètre à un navire.

}
