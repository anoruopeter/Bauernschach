/**
 * @author [Anoruo Peter]
 */

package game;

import piece.Piece;


public class Square {

    private Coordinate coordinate;

    private Piece piece = null;


    public Square(Coordinate coordinate, Piece piece) {
        this.coordinate = coordinate;
        this.piece = piece;
    }


    public Square(Coordinate coordinate) {

        this(coordinate, null);
    }


    public Coordinate getCoordinate() {

        return coordinate;
    }


    public Piece getBauer() {

        return piece;
    }


    public boolean equals(Square square) {
        if (square.getCoordinate().equals(coordinate))
            return true;
        return false;
    }

    public boolean Occupied() {
        if (piece == null) {
            return false;
        }
        return true;
    }


    public void releasePiece() {

        piece = null;
    }

    public void setBauer(Piece piece) {

        this.piece = piece;
    }

}
