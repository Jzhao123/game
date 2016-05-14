import java.awt.*;
import java.awt.image.*;

/**
 * Represents a map.
 */
public class Map {
    private Terrain[][] landscape;
    private Building[][] buildings;
    private Unit[][] units;
    private Player[] players;
    private int width, height;
    private final static int scale = 3;
    private final static int size = 2;

    /**
     * Constructor for objects of class Map
     */
    public Map(int mapWidth, int mapHeight) {
        width = mapWidth;
        height = mapHeight;
        landscape = new Terrain[width*scale][height*scale];
        buildings = new Building[width*scale][height*scale];
        units = new Unit[width][height];
        players = new Player[2];
    }
    
    /**
     * Adds a unit to the map.
     */
    public void addUnit(Unit u, int xPos, int yPos) {
        units[xPos][yPos] = u;
    }
    
    /**
     * Adds a terrain landscape to the map.
     */
    public void addTerrains(Terrain[][] terrains) {
        landscape = terrains;
    }
    
    /**
     * Adds a building to the map.
     */
    public void addBuilding(Building b) {
        int[] xlocs = b.getXLocs();
        int[] ylocs = b.getYLocs();
        for (int i = 0; i < xlocs.length; i++) {
            buildings[xlocs[i]][ylocs[i]] = b;
        }
    }
    
    /**
     * Moves a unit at (ox, oy) towards a destination at (nx, ny).
     * [Must account for other units, terrain, buildings, etc. -- not an easy method]
     */
    public void moveUnit(int ox, int oy, int nx, int ny) {
        if(units[ox][oy] != null)
        {
            if((Math.abs(ox-nx) + Math.abs(oy-ny))>units[ox][oy].getStamina())
            {
                //Message says can't move
            }
            else
            {
                units[ox][oy].updateStam(Math.abs(ox-nx) + Math.abs(oy-ny));
                units[nx][ny] = units[ox][oy];
                units[ox][oy] = null;
            }
        }
        else {
            //Message says no unit on grid position
        }
    }
    
    /**
     * Simulates combat between two units.
     */
    private void combat(Unit attacker,Unit defender) {
        if(!(defender.hit(attacker.getAttack() * (1-(defender.getDefense() * (3/4) * landscape[defender.getXPos()][defender.getYPos()].getXdef())))))
        {
            units[defender.getXPos()][defender.getYPos()] = null;
        }
        if(!(attacker.hit((defender.getDefense() * 50) * (1-(attacker.getDefense() * (1/3))))))
        {
            units[attacker.getXPos()][attacker.getYPos()] = null;
        }
    }
    
    /**
     * Draws the current state of the map into the graphics object.
     */
    public void drawImage(Graphics g, BufferedImage image) {
        for (int i = 0; i < width*scale; i++) {
            for (int j = 0; j < height*scale; j++) {
                g.setColor(landscape[i][j].getColor());
                if (buildings[i][j] != null) {
                    g.setColor(buildings[i][j].getColor());
                }
                g.fillRect(size*i, size*j, size, size);
            }
        }
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (units[i][j] != null) {
                    g.setColor(units[i][j].getColor());
                    g.fillOval(size*scale*i, size*scale*j, size*scale, size*scale);
                }
            }
        }
    }
    
    /**
     * Updates the current conditions of the game map.
     */
    public void updateConditions() {
        
    }
    
}
