package player;

import board.Board;
import board.Hit;
import board.ShipStruckAtSamePosError;
import ships.AbstractShip;

import java.util.List;

public class AIPlayer extends Player {
    /* **
     * Attribut
     */
    private BattleShipsAI ai;

    /* **
     * Constructeur
     */
    public AIPlayer(Board ownBoard, Board opponentBoard, List<AbstractShip> ships) {
        super(ownBoard, opponentBoard, ships);
        ai = new BattleShipsAI(ownBoard, opponentBoard);
    }

    @Override
    public void putShips() {
        ai.putShips(ships);
    }

    @Override
    public Hit sendHit(int[] coords) throws ShipStruckAtSamePosError {
        return ai.sendHit(coords);
    }

    // TODO AIPlayer must not inherit "keyboard behavior" from player. Call ai instead.
}
