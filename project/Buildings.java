import java.util.ArrayList;

/**
 * Contains static methods to produce default buildings.
 */
public class Buildings {
    /**
     * Creates a standard fortified camp.
     */
    public static Building getCamp(int length, int width, int xloc, int yloc) {
        ArrayList<Integer> xLocs = new ArrayList<Integer>();
        ArrayList<Integer> yLocs = new ArrayList<Integer>();
        for (int i = 0; i < length; i++) {
            xLocs.add(xloc + i);
        }
        for (int i = 0; i < width; i++) {
            yLocs.add(yloc + i);
        }
        return new Building(xLocs, yLocs, 5, false);
    }
    
    /**
     * Creates a standard castle.
     */
    public static Building getCastle(int length, int width, int xloc, int yloc) {
        ArrayList<Integer> xLocs = new ArrayList<Integer>();
        ArrayList<Integer> yLocs = new ArrayList<Integer>();
        for (int i = 0; i < length; i++) {
            xLocs.add(xloc + i);
        }
        for (int i = 0; i < width; i++) {
            yLocs.add(yloc + i);
        }
        return new Building(xLocs, yLocs, 100, true);
    }
}
