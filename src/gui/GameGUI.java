/**
 * @author [Anoruo Peter]
 */
package gui;

import game.BoardController;
import game.Coordinate;
import game.Square;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import player.PlayerType;



public class GameGUI {
    private BoardController boardController;
    private JButton lastSelection = null;
    static JButton resign, newGame, save, restore;
    private JLabel lblscoreCounter = new JLabel();
    private JLabel lblPlayerTurn = new JLabel();
    private JButton[][] allButtons = null;
    private JFrame gameFrame;
    private int blackcounter = 0;
    private int whitecounter = 0;
    JToolBar tools;
    JPanel panel;

    public static void main(String[] args) {
        new GameGUI();
    }

    public GameGUI() {
        boardController = new BoardController();
        initializeGui();

    }

    private void initializeGui() {
        gameFrame = new JFrame();
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        tools = new JToolBar();
        tools.setFloatable(false);
        panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.LEFT));

        lblscoreCounter.setText("SCORE: " + "White(" + whitecounter + ") " + " Black(" +blackcounter + ") ");
        lblPlayerTurn.setText("White has First Move ");


        save = new JButton("Save");
        save.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "This function not been Implemented");
            }
        });
        restore = new JButton("Restore");
        restore.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "This function not been Implemented");
            }
        });
        resign = new JButton("Resign");
       
        newGame = new JButton("New Game");
        newGame.addActionListener(new ActionListener(){
             @Override
            public void actionPerformed(ActionEvent e) {
                Listener acL = new Listener();
                boardController.resetBoard();
                acL.updateBoard();
                }
        });
        tools.add(newGame);
        tools.add(save);
        tools.add(restore);
        tools.addSeparator();
        tools.add(lblscoreCounter);
        tools.addSeparator();
        tools.add(lblPlayerTurn);
        
        panel.add(tools);

        gameFrame.setTitle("Bauernschach");
        gameFrame.setSize(800, 600);


        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        gameFrame.setLocationRelativeTo(null);

        gameFrame.setLayout(new BorderLayout());

        gameFrame window = new gameFrame(boardController.getBoard().getSquares());

        mainPanel.add(panel);
        mainPanel.add(window);

        allButtons = window.getButtons();
        gameFrame.add(mainPanel);
        gameFrame.pack();
        window.setVisible(true);
        gameFrame.setVisible(true);

    }
    public class Listener implements ActionListener {

        public void updateBoard() {
            Square[][] squares = boardController.getBoard().getSquares();
            for (int row = 0; row <= 7; row++) {
                for (int col = 0; col <= 7; col++) {
                    JButton button;
                    Square square;
                    if (boardController.getCurrentPlayer() == PlayerType.BLACK) {
                        button = allButtons[col][row];
                        square = squares[row][col];
                    } else {
                        button = allButtons[col][row];
                        square = squares[row][7 - col];
                    }


                    int offset = 1;
                    if (boardController.getCurrentPlayer() == PlayerType.WHITE) {
                        offset = 0;
                    }
                    if ((row + col + offset) % 2 == 0) {
                        button.setBackground(java.awt.Color.white);
                    } else {
                        button.setBackground(new Color(29, 114, 46));
                    }

                    if (square.Occupied()) {
                        String playerString;
                        String pieceString = "Pawn";
                        if (square.getBauer().getPlayer() == PlayerType.WHITE) {
                            playerString = "white";
                            lblPlayerTurn.setText("White's turn to make a move");
                        } else {
                            playerString = "black";
                            lblPlayerTurn.setText("Black's turn to make a move");
                        }

                        Image image = null;
                        try {
                            image = ImageIO.read(getClass().getResource(playerString + pieceString + ".png"));
                        } catch (IOException e) {
                        }
                        button.setIcon(new ImageIcon(image));

                    } else {
                        button.setIcon(null);
                    }
                }
            }

        }


        public void actionPerformed(ActionEvent e) {

            JButton button = (JButton) e.getSource();
            Point rv = new Point();
            int selectionX = button.getLocation(rv).x / 80;
            int selectionY = button.getLocation(rv).y / 80;
            if (boardController.getCurrentPlayer() == PlayerType.WHITE) {
                selectionY = 7 - selectionY;
            }
            Coordinate currentCoordinate = new Coordinate(selectionX, selectionY);

            boolean moved = false;
            if (lastSelection != null) {
                selectionX = lastSelection.getLocation(rv).x / 80;
                selectionY = lastSelection.getLocation(rv).y / 80;
                if (boardController.getCurrentPlayer() == PlayerType.WHITE) {
                    selectionY = 7 - selectionY;
                }
                Coordinate lastCoordinate = new Coordinate(selectionX, selectionY);
                moved = boardController.move(lastCoordinate, currentCoordinate);

                if (moved) {
                    if (boardController.confirmEnd(boardController.getBoard().getSquare(currentCoordinate))) {

                        Square square = boardController.getBoard().getSquare(currentCoordinate);

                        if( square.getBauer().getPlayer() == PlayerType.WHITE){
                            whitecounter = whitecounter+1;
                            boardController.inOpponentSquare(boardController.getBoard().getSquare(currentCoordinate),  "White Player has won the game");
                            lblscoreCounter.setText("SCORE: " + "White(" +whitecounter + ") " + " Black(" +blackcounter + ") ");
                            lblPlayerTurn.setText("GAME OVER!!! WHITE HAS WON THE GAME");
                            if(boardController.restart()){
                                boardController.resetBoard();
                                updateBoard();
                            }
                            else{
                                gameFrame.dispose();
                            }
                        }
                        else if(square.getBauer().getPlayer() == PlayerType.BLACK){
                            blackcounter = blackcounter+1;
                            boardController.inOpponentSquare(boardController.getBoard().getSquare(currentCoordinate),  "BLACK Player has won the game");
                            lblscoreCounter.setText("SCORE: " + "White(" +whitecounter + ") " + " Black(" +blackcounter + ") ");
                            lblPlayerTurn.setText("GAME OVER!!! BLACK HAS WON THE GAME");
                            if(boardController.restart()){
                                boardController.resetBoard();
                                updateBoard();
                            }
                            else{
                                gameFrame.dispose();
                            }
                        }
                    }
                    lastSelection = null;
                    updateBoard();
                }
            }

            if (!moved) {
                lastSelection = button;
            }

        }
    }

    public class gameFrame extends JPanel {
        private static final long serialVersionUID = 1L;

        private JButton[][] allButtons = new JButton[8][8];

        public JButton[][] getButtons() {
            return allButtons;
        }

        public gameFrame(Square[][] squares) {
            setLayout(new GridBagLayout());
            GridBagConstraints gb = new GridBagConstraints();
            for (int row = 0; row <= 7; row++) {
                for (int col = 0; col <= 7; col++) {
                    gb.gridx = row;
                    gb.gridy = col;
                    JButton button = new JButton();
                    allButtons[col][row] = button;

                    button.setBorderPainted(false);
                    button.setPreferredSize(new Dimension(80, 80));

                    Square square;
                    if (boardController.getCurrentPlayer() == PlayerType.BLACK) {
                        square = squares[row][col];
                    } else {
                        square = squares[row][7 - col];
                    }

                    if (square.Occupied()) {
                        String playerString;
                        if (square.getBauer().getPlayer() == PlayerType.WHITE) {
                            playerString = "white";
                        } else {
                            playerString = "black";
                        }

                        Image image = null;
                        try {
                            image = ImageIO.read(getClass().getResource(playerString + "Pawn.png"));
                        } catch (IOException e) {
                        }
                        button.setIcon(new ImageIcon(image));

                    } else {
                        button.setIcon(null);
                    }

                    if ((row + col) % 2 == 0) {
                        button.setBackground(java.awt.Color.white);
                    } else {
                        button.setBackground(new Color(29, 114, 46));
                    }
                    Listener actionListener = new Listener();
                    button.addActionListener(actionListener);
                    add(button, gb);


                }

            }
        }
    }

}