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
    private GameWindow window;

    /**
     * Constructor for objects of class Map
     */
    public Map(int mapWidth, int mapHeight, GameWindow gameWindow) {
        width = mapWidth;
        height = mapHeight;
        landscape = new Terrain[width*scale][height*scale];
        buildings = new Building[width*scale][height*scale];
        units = new Unit[width*scale][height*scale];
        players = new Player[2];
        window = gameWindow;
    }
    
    /**
     * Gets the unit at the said location *on the window*.
     */
    public Unit getUnit(int w, int h) {
        Unit u = null;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                u = units[w/scale + i][w/scale + j];
                if (u != null) {
                    return u;
                }
            }
        }
        return u;
    }
    /*
    *Creates preset map
    */
    public void presetMap()
    {
    //set all to null.
    for(int a=0; a<landscape.length();a++)
    {
        for(int b=0; b<landscape[0].length();b++)
        {
            landscape[a][b]=null
        }
    }
    //set water border
    //set road
    //set hospital
    //set mountain
    //setsfield for remaining portions
    for(int c=0; c<landscape.length();c++)
    {
        for(int c=0; c<landscape[0].length();c++)
        {
            if(landscape[a][b]=null)
            {
                landscape[a][b]== getField();
            }
        }
    }
    
    }
    
    /**
     * Returns if the selected square is in the spawn zone.
     */
    public boolean canSpawn(int w, int h) {
        Unit u = null;
        for (int i = -(size/2); i < (size/2); i++) {
            for (int j = -(size/2); j < (size/2); j++) {
                u = units[w/scale + i][w/scale + j];
                if (u != null) {
                    return false;
                }
            }
        }
        return true;
    }
    
    /**
     * Returns the actual player of the game (not the computer).
     */
    public Player getHumanPlayer() {
        return players[0];
    }
    
    /**
     * Returns the computer player of the game.
     */
    public Player getCompPlayer() {
        return players[1];
    }
    
    /**
     * Adds a unit to the map.
     */
    public void addUnit(int xPos, int yPos) {
        Unit u = Units.getLegionnaire();
        u.setPlayer(players[0]);
        units[xPos/(size*scale)][yPos/(size*scale)] = u;
        players[0].spawnUnit();
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
     * Moves a unit at (ox, oy) towards a destination at (nx, ny)
     */
    private void moveUnit(int ox, int oy, int nx, int ny) {
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
     * Automoves a single unit on the map (does not initiate conflict).
     * [Must account for other units, terrain, buildings, etc. -- not an easy method]
     */
    private void autoMove(Unit u) {
        //help pls
    }
    
    /**
     * Determines whether or not a unit is in range; if so, returns that unit.
     */
    private Unit getUnitInRange(Unit u) {
        //too tired for this
        return null;
    }
    
    /**
     * Simulates combat between two units.
     */
    private void combat(Unit attacker, Unit defender) {
        //Sometime in the future this needs to take buildings into account as well
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
        for (int i = 0; i < width*scale; i++) {
            for (int j = 0; j < height*scale; j++) {
                if (units[i][j] != null) {
                    g.setColor(units[i][j].getColor());
                    g.fillOval(size*scale*(i-scale/2), size*scale*(j-scale/2), size*scale, size*scale);
                }
            }
        }
    }
    
    /**
     * Updates the current conditions of the game map.
     */
    public void updateConditions() {
        //sometime in the future this needs to take buildings into account as well
        for (int i = 0; i < units.length; i++) {
            for (int j = 0; j < units[0].length; j++) {
                if (units[i][j] != null) {
                    autoMove(units[i][j]);
                }
            }
        }
        boolean[][] fought = new boolean[units.length][units[0].length];
        Unit u, other;
        for (int i = 0; i < units.length; i++) {
            for (int j = 0; j < units[0].length; j++) {
                if (!fought[i][j]) {
                    u = units[i][j];
                    if (u != null) {
                        other = getUnitInRange(u);
                        if (other != null) {
                            combat(u, other);
                            fought[other.getXPos()][other.getYPos()] = true;
                        }
                    }
                }
            }
        }
    }
    
}
