/**
 * @author [Anoruo Peter]
 */

package piece;

import player.PlayerType;
import game.Koordinaten;


public class Bauer{

    private PlayerType player;

    public Bauer(PlayerType player) {
        this.player = player;
    }

    public PlayerType getPlayer(){
        return player;
    }

    public boolean ValidMove(Koordinaten initialPos, Koordinaten finalPos) {
        if (initialPos.equals(finalPos)) {
            return false;
        }

        if (Math.abs(initialPos.getY() - finalPos.getY()) == 1 && Math.abs(initialPos.getX() - finalPos.getX()) == 0) {

            if (this.getPlayer() == PlayerType.WHITE) {
                if (initialPos.getY() < finalPos.getY()) {
                    return true;
                }
            }

            if (this.getPlayer() == PlayerType.BLACK) {
                if (initialPos.getY() > finalPos.getY()) {
                    return true;
                }
            }

        }

        if (Math.abs(initialPos.getY() - finalPos.getY()) == 2 && Math.abs(initialPos.getX() - finalPos.getX()) == 0 && (initialPos.getY() == 1 || initialPos.getY() == 6)) {

            if (this.getPlayer() == PlayerType.WHITE) {
                if (initialPos.getY() < finalPos.getY()) {
                    return true;
                }
            }
            if (this.getPlayer() == PlayerType.BLACK) {
                if (initialPos.getY() > finalPos.getY()) {
                    return true;
                }
            }

        }

        return false;
    }

    public Koordinaten[] getPath(Koordinaten initialPos, Koordinaten finalPos) {

        if (initialPos.getX() != finalPos.getX()){
            return new Koordinaten[]{initialPos,finalPos};
        }

        int pathLength = Math.abs(initialPos.getY() - finalPos.getY()) + 1;
        Koordinaten[] path = new Koordinaten[pathLength];

        for (int plc = 0; plc < pathLength; plc++) {
            path[plc] = new Koordinaten(initialPos.getX(), Math.min(initialPos.getY(), finalPos.getY()) + plc);
        }

        return path;
    }
}
