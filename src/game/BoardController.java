/**
 * @author [Anoruo Peter]
 */

package game;

import java.util.ArrayList;
import java.util.List;


import player.PlayerType;

import javax.swing.*;


public class BoardController {

    private Board board;

    private PlayerType currentPlayer = PlayerType.WHITE;

    private List<Move> moveList = new ArrayList<Move>();

    public BoardController() {

        this.board = new Board();
    }

    public void resetBoard() {
        moveList = new ArrayList<Move>();
        board.reset();
        currentPlayer = PlayerType.WHITE;
    }

    private void switchCurrentPlayer() {
        if (currentPlayer == PlayerType.WHITE) {
            currentPlayer = PlayerType.BLACK;
        } else {
            currentPlayer = PlayerType.WHITE;
        }

    }

    public PlayerType getCurrentPlayer() {
        return currentPlayer;
    }

    public Board getBoard() {
        return board;
    }


    public boolean inOpponentSquare(Square square, String message) {
        if (confirmEnd(square)) {
          JOptionPane.showMessageDialog(null,message);
            return true;
        }
        return false;
    }

    public boolean restart(){
        Object[] options = {"Yes", "No"};
        int n = JOptionPane.showOptionDialog(null,
                "Do you want to play again?",
                "New Game",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]);
        if(n == JOptionPane.YES_OPTION){
            resetBoard();
            return true;
        }
       return false;
    }


    public boolean confirmEnd(Square square) {
        if (!square.Occupied() == true) {
            return false;
        }

        int col = 7;
        if (square.getBauer().getPlayer() == PlayerType.BLACK) {
            col = 0;
        }
        if (square.getCoordinate().equals(new Coordinate(square.getCoordinate().getX(), col))) {
            return true;
        }
        return false;
    }


    public boolean move(Coordinate initCoordinate, Coordinate finalCoordinate) {
        if(initCoordinate==null || finalCoordinate==null){return false;}

        if (!(initCoordinate.isValidCoordinate() && finalCoordinate.isValidCoordinate())) {
            return false;
        }
        Square s1 = board.getSquare(initCoordinate);
        Square s2 = board.getSquare(finalCoordinate);

        if(!isSaneMove(s1,s2)){
            return false;
        }

        if (currentPlayer == s1.getBauer().getPlayer()) {
            if (ValidMove(s1, s2)) {
                switchCurrentPlayer();
                moveList.add(new Move(s1));
                board.makeMove(s1, s2);
                return true;
            }
        }
        return false;
    }


    private boolean isValidPawnCapture(Square initSquare, Square finalSquare) {

        if (!finalSquare.Occupied()) {
            return false;
        }
        Coordinate initPos = initSquare.getCoordinate();
        Coordinate finalPos = finalSquare.getCoordinate();
        PlayerType player = initSquare.getBauer().getPlayer();


        if (Math.abs(initPos.getY() - finalPos.getY()) == 1
                && Math.abs(initPos.getX() - finalPos.getX()) == 1) {

            if (player == PlayerType.WHITE) {
                if (initPos.getY() < finalPos.getY()) {
                    return true;
                }
            }

            if (player == PlayerType.BLACK) {
                if (initPos.getY() > finalPos.getY()) {
                    return true;
                }
            }

        }
        return false;
    }


    private boolean isPathClear(Coordinate[] path, Coordinate initCoordinate, Coordinate finalCoordinate) {
        Square[][] squares = board.getSquares();
        for (Coordinate coordinate : path) {
            if ((squares[coordinate.getX()][coordinate.getY()].Occupied())
                    && (!coordinate.equals(initCoordinate))
                    && (!coordinate.equals(finalCoordinate))) {
                return false;
            }
        }
        return true;
    }


    private boolean isSaneMove(Square initSquare, Square finalSquare) {

        if(!initSquare.getCoordinate().isValidCoordinate() || !initSquare.getCoordinate().isValidCoordinate() )
        {
            return false;
        }

        if (!initSquare.Occupied()) {
            return false;
        }

        if (initSquare.equals(finalSquare)) {
            return false;
        }

        return true;
    }


    private boolean ValidMovement(Square initSquare, Square finalSquare) {
        if(!isSaneMove(initSquare,finalSquare)){
            return false;
        }

        if (finalSquare.Occupied()) {
            if (initSquare.getBauer().getPlayer() == finalSquare.getBauer().getPlayer())
                return false;
        }

        if (!initSquare.getBauer().ValidMove(initSquare.getCoordinate(), finalSquare.getCoordinate()) && !isValidPawnCapture(initSquare, finalSquare)) {
            return false;
        }

        if (finalSquare.Occupied() && !isValidPawnCapture(initSquare, finalSquare)) {
            return false;
        }

        Coordinate[] path = initSquare.getBauer().getPath(initSquare.getCoordinate(), finalSquare.getCoordinate());
        if (!isPathClear(path, initSquare.getCoordinate(),
                finalSquare.getCoordinate())) {
            return false;
        }
        return true;
    }


    public boolean ValidMove(Square initSquare, Square finalSquare) {
        if (!ValidMovement(initSquare, finalSquare)) {
            return false;
        }
        return true;
    }

}
