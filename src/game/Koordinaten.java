/**
 * @author [Anoruo Peter]
 */

package game;



public class Koordinaten {
    int positionX;
    int positionY;


    public Koordinaten(int x, int y) {
        positionX = x;
        positionY = y;

    }


    public boolean isValidKoordinaten() {
        if ((positionX >= 0 && positionX < 8) && (positionY >= 0 && positionY < 8)) {
            return true;
        }
        return false;
    }


    public int getX() {

        return positionX;
    }


    public int getY() {

        return positionY;
    }


    public String toString() {
        return Integer.toString(positionX) + "," + Integer.toString(positionY);
    }


    public boolean equals(Koordinaten koordinate) {
        if ((positionX == koordinate.getX()) && (positionY == koordinate.getY())) {
            return true;
        }
        return false;
    }

}