/**
 * @author [Anoruo Peter]
 */

package game;

import java.util.ArrayList;
import java.util.List;


import player.PlayerType;

import javax.swing.*;


public class Schachbrettkontroller {

    private Schachbrett board;

    private PlayerType currentPlayer = PlayerType.WHITE;

    private List<Move> moveList = new ArrayList<Move>();

    public Schachbrettkontroller() {

        this.board = new Schachbrett();
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

    public Schachbrett getBoard() {
        return board;
    }


    public boolean inOpponentSquare(Quadrat quadrat, String message) {
        if (confirmEnd(quadrat)) {
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


    public boolean confirmEnd(Quadrat quadrat) {
        if (!quadrat.Occupied() == true) {
            return false;
        }

        int col = 7;
        if (quadrat.getBauer().getPlayer() == PlayerType.BLACK) {
            col = 0;
        }
        if (quadrat.getKoordinaten().equals(new Koordinaten(quadrat.getKoordinaten().getX(), col))) {
            return true;
        }
        return false;
    }


    public boolean move(Koordinaten initialKoordinaten, Koordinaten finalKoordinaten) {
        if(initialKoordinaten == null || finalKoordinaten == null){return false;}

        if (!(initialKoordinaten.isValidKoordinaten() && finalKoordinaten.isValidKoordinaten())) {
            return false;
        }
        Quadrat s1 = board.getQuadraten(initialKoordinaten);
        Quadrat s2 = board.getQuadraten(finalKoordinaten);

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


    private boolean isValidCapture(Quadrat initialSquare, Quadrat finalSquare) {

        if (!finalSquare.Occupied()) {
            return false;
        }
        Koordinaten initialPos = initialSquare.getKoordinaten();
        Koordinaten finalPos = finalSquare.getKoordinaten();
        PlayerType player = initialSquare.getBauer().getPlayer();


        if (Math.abs(initialPos.getY() - finalPos.getY()) == 1
                && Math.abs(initialPos.getX() - finalPos.getX()) == 1) {

            if (player == PlayerType.WHITE) {
                if (initialPos.getY() < finalPos.getY()) {
                    return true;
                }
            }

            if (player == PlayerType.BLACK) {
                if (initialPos.getY() > finalPos.getY()) {
                    return true;
                }
            }

        }
        return false;
    }


    private boolean isPathClear(Koordinaten[] path, Koordinaten initialKoordinaten, Koordinaten finalKoordinaten) {
        Quadrat[][] quadraten = board.getQuadraten();
        for (Koordinaten Koordinat : path) {
            if ((quadraten[Koordinat.getX()][Koordinat.getY()].Occupied())
                    && (!Koordinat.equals(initialKoordinaten))
                    && (!Koordinat.equals(finalKoordinaten))) {
                return false;
            }
        }
        return true;
    }


    private boolean isSaneMove(Quadrat initialSquare, Quadrat finalSquare) {

        if(!initialSquare.getKoordinaten().isValidKoordinaten() || !initialSquare.getKoordinaten().isValidKoordinaten() )
        {
            return false;
        }

        if (!initialSquare.Occupied()) {
            return false;
        }

        if (initialSquare.equals(finalSquare)) {
            return false;
        }

        return true;
    }


    private boolean ValidMovement(Quadrat initialSquare, Quadrat finalSquare) {
        if(!isSaneMove(initialSquare,finalSquare)){
            return false;
        }

        if (finalSquare.Occupied()) {
            if (initialSquare.getBauer().getPlayer() == finalSquare.getBauer().getPlayer())
                return false;
        }

        if (!initialSquare.getBauer().ValidMove(initialSquare.getKoordinaten(), finalSquare.getKoordinaten()) && !isValidCapture(initialSquare, finalSquare)) {
            return false;
        }

        if (finalSquare.Occupied() && !isValidCapture(initialSquare, finalSquare)) {
            return false;
        }

        Koordinaten[] path = initialSquare.getBauer().getPath(initialSquare.getKoordinaten(), finalSquare.getKoordinaten());
        if (!isPathClear(path, initialSquare.getKoordinaten(),
                finalSquare.getKoordinaten())) {
            return false;
        }
        return true;
    }


    public boolean ValidMove(Quadrat initialSquare, Quadrat finalSquare) {
        if (!ValidMovement(initialSquare, finalSquare)) {
            return false;
        }
        return true;
    }

}
