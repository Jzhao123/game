import java.util.ArrayList;
import java.util.Arrays;

/**
 * Represents a building as a series of individual wall tiles.
 */
public class Building {
    private int[][] walls;
    private int def;
    private boolean high;

    /**
     * Constructor for objects of class Building
     */
    public Building(ArrayList<Integer> xLocs, ArrayList<Integer> yLocs, int def, boolean high) {
        walls = new int[xLocs.size()][2];
        for (int i = 0; i < xLocs.size(); i++) {
            walls[0][i] = xLocs.get(i).intValue();
            walls[1][i] = yLocs.get(i).intValue();
        }
    }
    
    /**
     * Returns the wall x-locations.
     */
    public int[] getXLocs() {
        return Arrays.copyOf(walls[0], walls[0].length);
    }
    
    /**
     * Returns the wall y-locations.
     */
    public int[] getYLocs() {
        return Arrays.copyOf(walls[1], walls[1].length);
    }
}
