/**
 * @author [Anoruo Peter]
 */

package piece;

import player.PlayerType;
import game.Coordinate;


public class Bauer extends Piece {


    public Bauer(PlayerType player) {
        super(player);
    }

    @Override
    public boolean ValidMove(Coordinate initPos, Coordinate finalPos) {
        if (initPos.equals(finalPos)) {
            return false;
        }

        if (Math.abs(initPos.getY() - finalPos.getY()) == 1 && Math.abs(initPos.getX() - finalPos.getX()) == 0) {

            if (this.getPlayer() == PlayerType.WHITE) {
                if (initPos.getY() < finalPos.getY()) {
                    return true;
                }
            }

            if (this.getPlayer() == PlayerType.BLACK) {
                if (initPos.getY() > finalPos.getY()) {
                    return true;
                }
            }

        }

        if (Math.abs(initPos.getY() - finalPos.getY()) == 2 && Math.abs(initPos.getX() - finalPos.getX()) == 0 && (initPos.getY() == 1 || initPos.getY() == 6)) {

            if (this.getPlayer() == PlayerType.WHITE) {
                if (initPos.getY() < finalPos.getY()) {
                    return true;
                }
            }
            if (this.getPlayer() == PlayerType.BLACK) {
                if (initPos.getY() > finalPos.getY()) {
                    return true;
                }
            }

        }

        return false;
    }

    @Override
    public Coordinate[] getPath(Coordinate initPos, Coordinate finalPos) {

        if (initPos.getX() != finalPos.getX()){
            return new Coordinate[]{initPos,finalPos};
        }

        int pathLength = Math.abs(initPos.getY() - finalPos.getY()) + 1;
        Coordinate[] path = new Coordinate[pathLength];

        for (int cnt = 0; cnt < pathLength; cnt++) {
            path[cnt] = new Coordinate(initPos.getX(), Math.min(initPos.getY(), finalPos.getY()) + cnt);
        }

        return path;
    }
}
