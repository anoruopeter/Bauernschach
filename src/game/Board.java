/**
 * @author [Anoruo Peter]
 */

package game;

import piece.*;
import player.PlayerType;



public class Board {

    private Square[][] squares = new Square[8][8];


    public Board()
    {
        setSquares();
        setWhite();
        setBlack();

    }


    public void reset(){
        setSquares();
        setWhite();
        setBlack();
    }

    private void setSquares(){
        for (int x = 0; x <= 7; x++)
        {
            for (int y = 0; y <= 7; y++)
            {
                squares[x][y]=new Square(new Coordinate(x,y));
            }
        }

    }

    private void setWhite(){
        for(int x = 0; x <= 7; x++){
            for(int y = 0; y <= 1; y++){
                squares[x][y].setBauer(new Bauer(PlayerType.WHITE));
            }
        }
    }

    private void setBlack(){
        for(int x = 0; x <= 7; x++){
            for(int y = 0; y <= 7; y++){
                if((y == 6) || (y == 7)){
                    squares[x][y].setBauer(new Bauer(PlayerType.BLACK));
                }
            }
        }
    }

    public Square[][] getSquares(){
        return squares;
    }


    public Square getSquare(Coordinate coordinate){
        Square result=null;
        for (int x = 0; x <= 7; x++)
        {
            for (int y = 0; y <= 7; y++){
                if (squares[x][y].getCoordinate().equals(coordinate))
                {
                    result=squares[x][y];
                }
            }
        }
        return result;
    }


    public void capturePawn(Square square){
        if(square.Occupied())
        {
            square.releasePiece();
        }
    }


    public void makeMove(Square initSquare,Square finalSquare){

        if(finalSquare.Occupied())
        {
            capturePawn(finalSquare);
        }
        finalSquare.setBauer(initSquare.getBauer());
        initSquare.releasePiece();
    }

}
