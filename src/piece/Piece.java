/**
 * @author [Anoruo Peter]
 */

package piece;

import player.PlayerType;
import game.Coordinate;



public abstract class Piece {
    private PlayerType player;

    public Piece(PlayerType player){
        this.player = player;
    }

    public String toString(){

        return player.toString();
    }

    public PlayerType getPlayer(){
        return player;
    }

    public abstract boolean ValidMove(Coordinate initPos,Coordinate finalPos);


    public abstract Coordinate[] getPath(Coordinate initPos,Coordinate finalPos);


}
