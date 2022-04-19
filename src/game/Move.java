/**
 * @author [Anoruo Peter]
 */

package game;
public class Move {

    public Move(Square captureSquare) {
        if(captureSquare!=null){
            captureSquare.getBauer();
            captureSquare.getCoordinate();
        }
    }
}
