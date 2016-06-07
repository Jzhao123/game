import java.util.ArrayList;
import java.util.Arrays;
import java.awt.Color;

/**
 * Represents a building as a series of individual wall tiles.
 */
public class Building {
    private int x, y, xlen, ylen;
    private Color color;

    /**
     * Constructor for objects of class Building
     */
    public Building(int xPos, int yPos, int xLen, int yLen, Color c) {
        x = xPos;
        y = yPos;
        xlen = xLen;
        ylen = yLen;
        color = c;
    }
    
    /**
     * Returns the color of the building
     */
    public Color getColor() {
        return color;
    }
    
    /**
     * Returns the x-position.
     */
    public int getXPos() {
        return x;
    }
    
    /**
     * Returns the y-position.
     */
    public int getYPos() {
        return y;
    }
    
    /**
     * Returns the width.
     */
    public int getXLen() {
        return xlen;
    }
    
    /**
     * Returns the other width.
     */
    public int getYLen() {
        return ylen;
    }
}
