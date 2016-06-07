import java.util.ArrayList;
import java.awt.Color;

/**
 * Contains static methods to produce default buildings.
 */
public class Buildings {
    /**
     * Creates a standard fortified camp.
     */
    public static Building getCamp(int xloc, int yloc, int length, int width) {
        return new Building(xloc, yloc, length, width, Color.orange);
    }
    
    /**
     * Creates a standard castle.
     */
    public static Building getCastle(int length, int width, int xloc, int yloc) {
        return new Building(xloc, yloc, length, width, Color.lightGray);
    }
    
    /**
     * Creates a hospital. 
     * The hospital will reside in a hospital terrain and will be found somewhere in the hospital terrain.
     */
     public static Building getHospital(int length, int width, int xloc, int yloc) {
        return new Building(xloc, yloc, length, width, Color.white);
    }
}
