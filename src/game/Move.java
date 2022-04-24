/**
 * @author [Anoruo Peter]
 */

package game;
public class Move {

    public Move(Quadrat captureQuadrat) {
        if(captureQuadrat != null){
            captureQuadrat.getBauer();
            captureQuadrat.getKoordinaten();
        }
    }
}
