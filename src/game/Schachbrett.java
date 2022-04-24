/**
 * @author [Anoruo Peter]
 */

package game;

import piece.*;
import player.PlayerType;



public class Schachbrett {

    private Quadrat[][] quadrat = new Quadrat[8][8];


    public Schachbrett()
    {
        setQuadraten();
        setWhite();
        setBlack();

    }


    public void reset(){
        setQuadraten();
        setWhite();
        setBlack();
    }

    private void setQuadraten(){
        for (int x = 0; x <= 7; x++)
        {
            for (int y = 0; y <= 7; y++)
            {
                quadrat[x][y]=new Quadrat(new Koordinaten(x,y));
            }
        }

    }

    private void setWhite(){
        for(int x = 0; x <= 7; x++){
            for(int y = 1; y <= 1; y++){
                quadrat[x][y].setBauer(new Bauer(PlayerType.WHITE));
            }
            //Um zwei Reihen von Bauern zu haben, verwenden Sie dies
            // for(int y = 0; y <= 1; y++){
            //     quadrat[x][y].setBauer(new Bauer(PlayerType.WHITE));
            // }
        }
    }

    private void setBlack(){
        for(int x = 0; x <= 7; x++){
            for(int y = 0; y <= 7; y++){
                if(y == 6){
                    quadrat[x][y].setBauer(new Bauer(PlayerType.BLACK));
                }
                //Um zwei Reihen von Bauern zu haben, verwenden Sie dies
                // if((y == 6) || (y == 7)){
                //     quadrat[x][y].setBauer(new Bauer(PlayerType.BLACK));
                // }
            }
        }
    }

    public Quadrat[][] getQuadraten(){
        return quadrat;
    }


    public Quadrat getQuadraten(Koordinaten koordinaten){
        Quadrat result=null;
        for (int x = 0; x <= 7; x++)
        {
            for (int y = 0; y <= 7; y++){
                if (quadrat[x][y].getKoordinaten().equals(koordinaten))
                {
                    result = quadrat[x][y];
                }
            }
        }
        return result;
    }


    public void capture(Quadrat quadrat){
        if(quadrat.Occupied())
        {
            quadrat.release();
        }
    }


    public void makeMove(Quadrat initialSquare,Quadrat finalSquare){

        if(finalSquare.Occupied())
        {
            capture(finalSquare);
        }
        finalSquare.setBauer(initialSquare.getBauer());
        initialSquare.release();
    }

}
