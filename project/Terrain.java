import java.awt.Color;


/**
 * Contains a representation of a single map tile.
 */
public class Terrain {
    private int xspd;
    private int xdef;
    private Color color;

    /**
     * Constructor for objects of class Terrain
     */
    public Terrain(int speedCost, int defBuff, Color appearance) {
        xspd = speedCost;//Speed will be a increase or decrease in speed
        xdef = defBuff;//Defense will be a multiplier
        color = appearance;
    }

    /**
     * Returns the defense buff.
     */
    public int getXdef() {
        return xdef;
    }
    
    /**
     * Returns the speed cost.
     */
    public double getXspd() {
        return xspd;
    }
    
    /**
     * Returns the color of the tile.
     */
    public Color getColor() {
        return color;
    }
}
