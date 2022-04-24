/**
 * @author [Anoruo Peter]
 */

package game;

import piece.Bauer;

public class Quadrat {

    private Koordinaten koordinat;

    private Bauer bauer = null;


    public Quadrat(Koordinaten koordinat, Bauer bauer) {
        this.koordinat = koordinat;
        this.bauer = bauer;
    }


    public Quadrat(Koordinaten koordinat) {

        this(koordinat, null);
    }


    public Koordinaten getKoordinaten() {

        return koordinat;
    }


    public Bauer getBauer() {

        return bauer;
    }


    public boolean equals(Quadrat square) {
        if (square.getKoordinaten().equals(koordinat))
            return true;
        return false;
    }

    public boolean Occupied() {
        if (bauer == null) {
            return false;
        }
        return true;
    }

    public void release() {

        bauer = null;
    }

    public void setBauer(Bauer bauer) {

        this.bauer = bauer;
    }

}
