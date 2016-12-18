import java.awt.Color;

/**
 * Contains static methods for default terrains.
 */
public class Terrains {
    /**
     * Returns a default water terrain.
     */
    public static Terrain getWater() {
        return new Terrain(100, 0, Color.blue);
    }
    
    /**
     * Returns a default mountain terrain.
     */
    public static Terrain getMountain() {
        return new Terrain(100, 0, Color.darkGray);
    }
    
    /**
     * Returns a default field terrain.
     */
    public static Terrain getField() {
        return new Terrain(2, 1, Color.green);
    }
    
    /**
     * Returns a default road terrain.
     */
    public static Terrain getRoad() {
        return new Terrain(0, 0, Color.lightGray);
    }
    /*
    * Returns a default Hospital terrain that will units inside of it.
    */
    public static Terrain getHospital()
    {
        return new Terrain(50,0,Color.white);
    }
}
