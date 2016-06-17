import java.awt.*;
import java.awt.image.*;

/**
 * Represents a map.
 */
public class Map {
    //private Terrain[][] landscape;
    private Unit selected;
    private Building[][] buildings;
    private Unit[][] units; //map array, use to find adjacent squares
    private Player player, computer;
    private int width, height;
    public final static int scale = 2;
    private GameWindow window;
    private boolean won, who;
    
    /**
     * Proxy methods for window.
     */
    public int getLeft() {
        return window.getLeft();
    }
    
    /**
     * Proxy methods for window.
     */
    public int getTop() {
        return window.getTop();
    }
    
    /**
     * Constructor for objects of class Map
     */
    public Map(int mapWidth, int mapHeight, GameWindow gameWindow) {
        width = mapWidth;
        height = mapHeight;
        //landscape = new Terrain[width][height];
        buildings = new Building[width][height];
        units = new Unit[width][height];
        player = new Player();
        computer = new Player();
        window = gameWindow;
    }

    /**
     * Generates basic map obeject
     */
    public static Map generateMap(int mapWidth, int mapLength, GameWindow window) {
        Map map = new Map(mapWidth/scale, mapLength/scale, window);
        map.addBuilding(Buildings.getCamp(5, 5, 15, 15));
        map.addBuilding(Buildings.getCamp(mapWidth/scale - 20, mapLength/scale - 20, 15, 15));
        map.addCompUnit(mapWidth/scale - 30, mapLength/scale - 40);
        map.addCompUnit(mapWidth/scale - 40, mapLength/scale - 30);
        map.addCompUnit(mapWidth/scale - 35, mapLength/scale - 35);
        return map;
    }
    
    /**
     * Sets the current unit selected.
     */
    public void setSelected(Unit u) {
        if (selected != null) {
            selected.select(false);
        }
        selected = u;
        if (u != null) {
            selected.select(true);
        }
    }
    
    /**
     * Gets the current unit selected.
     */
    public Unit getSelected() {
        return selected;
    }

    /**
     * Gets the unit at the said location.
     */
    public Unit getUnit(int w, int h) {
        return getUnitInRange(w, h, scale + 1);
    }
    
    /**
     * Returns if the selected square is in the spawn zone.
     */
    public boolean canSpawn(int w, int h) {
        boolean ans = (getUnitInRange(w, h, 3) == null && buildings[w][h] == null);
        return ans && (w < width/4) && (h < height/4);
    }

    /**
     * Returns the actual player of the game (not the computer).
     */
    public Player getHumanPlayer() {
        return player;
    }

    /**
     * Returns the computer player of the game.
     */
    public Player getCompPlayer() {
        return computer;
    }

    /**
     * Adds a unit to the map.
     */
    public void addUnit(int xPos, int yPos) {
        if (player.getBar() > 5) {
            player.spawnUnit();
            Unit u = Units.getLegionnaire();
            u.setPlayer(player);
            u.setXPos(xPos);
            u.setYPos(yPos);
            u.setTarget(computer.getCamp().getXPos(), computer.getCamp().getYPos());
            units[xPos][yPos] = u;
        }
    }
    
    /**
     * Adds a computer-controlled unit to the map.
     */
    public void addCompUnit(int x, int y) {
        Unit u = Units.getBarbarian();
        u.setPlayer(computer);
        u.setXPos(x);
        u.setYPos(y);
        u.setTarget(player.getCamp().getXPos(), player.getCamp().getYPos());
        units[x][y] = u;
    }

    /**
     * Adds a building to the map.
     */
    public void addBuilding(Building b) {
        int x = b.getXPos();
        int y = b.getYPos();
        int xlen = b.getXLen();
        int ylen = b.getYLen();
        for (int i = x; i < x + xlen; i++) {
            for (int j = y; j < y + ylen; j++) {
                buildings[i][j] = b;
            }
        }
        if (player.getCamp() == null) {
            player.setCamp(b);
        } else if (computer.getCamp() == null) {
            computer.setCamp(b);
        }
    }
    
    /**
     * Calculates the next position the unit will take.
     */
    private int[] calcMove(int x, int y, int tx, int ty) {
        int[] ans = new int[2];
        if (x < tx) {
            ans[0] = x + 1;
        } else if (x > tx) {
            ans[0] = x - 1;
        } else {
            ans[0] = x;
        }
        if (y < ty) {
            ans[1] = y + 1;
        } else if (y > ty) {
            ans[1] = y - 1;
        } else {
            ans[1] = y;
        }
        return ans;
    }
    
    /**
     * Automoves a single unit on the map (does not initiate conflict). 
     */
    private void autoMove(Unit u) {
        if (u.getXPos() == u.getTargetX() && u.getYPos() == u.getTargetY()) {
            u.setTarget(computer.getCamp().getXPos(), computer.getCamp().getYPos());
        }
        int[] move = calcMove(u.getXPos(), u.getYPos(), u.getTargetX(), u.getTargetY());
        Unit u2 = getUnitInRange(move[0],move[1],2);
        if (u2 == null || u2 == u) {
            units[u.getXPos()][u.getYPos()] = null;
            u.setXPos(move[0]);
            u.setYPos(move[1]);
            units[move[0]][move[1]] = u;
            if (buildings[move[0]][move[1]] != null) {
                if (u.getPlayer() == computer && buildings[move[0]][move[1]] == player.getCamp()) {
                    window.won();
                    won = true;
                    who = false;
                } else if (u.getPlayer() == player && buildings[move[0]][move[1]] == computer.getCamp()) {
                    window.won();
                    won = true;
                    who = true;
                }
            }
        }
    }

    /**
     * Determines whether or not a unit is in range; if so, returns that unit.
     */
    private Unit getUnitInRange(int x, int y, int range) {
        for (int i = -range; i <= range; i++) {
            for (int j = -range; j <= range; j++) {
                try {
                    if (i != 0 || j != 0) {
                        Unit u2 = units[x + i][y + j];
                        if (u2 != null) {
                            return u2;
                        }
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    //System.out.println(e.getMessage());
                }
            }
        }
        return null;
    }

    /**
     * Simulates combat between two units.
     */
    private void combat(Unit attacker, Unit defender) {
        units[defender.getXPos()][defender.getYPos()] = null;
        units[attacker.getXPos()][attacker.getYPos()] = null;
    }

    /**
     * Draws the current state of the map into the graphics object.
     */
    public void drawImage(Graphics g, BufferedImage image) {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (buildings[i][j] != null) {
                    g.setColor(buildings[i][j].getColor());
                } else {
                    g.setColor(Color.white);
                }
                g.fillRect(scale*i, scale*j, scale, scale);
            }
        }
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (units[i][j] != null) {
                    if (units[i][j].isSelected()) {
                        g.setColor(Color.blue);
                        g.fillOval((i-3)*scale, (j-3)*scale, scale*7, scale*7);
                    }
                    g.setColor(units[i][j].getColor());
                    g.fillOval((i-2)*scale, (j-2)*scale, scale*5, scale*5);
                } else {
                    g.setColor(Color.white);
                }
            }
        }
        g.setColor(Color.lightGray);
        g.drawRect(1, 1, width*scale/4, height*scale/4);
        g.drawRect(width*scale*3/4, height*scale*3/4, width*scale - 1, height*scale -1);
        if (won) {
            g.setColor(Color.black);
            if (who) {
                g.drawString("You've Won!", width*scale/2 - 40, height*scale/2 - 2);
            } else {
                g.drawString("You've lost.", width*scale/2 - 40, height*scale/2 - 2);
            }
        }
    }

    /**
     * Updates the current conditions of the game map. 
     */
    public void updateConditions() {
        player.incrementBar();
        boolean[][] fought = new boolean[units.length][units[0].length];
        Unit u = null;
        Unit other = null;
        for (int i = 0; i < units.length; i++) {
            for (int j = 0; j < units[0].length; j++) {
                if (!fought[i][j]) {
                    u = units[i][j];
                    if (u != null) {
                        other = getUnitInRange(u.getXPos(), u.getYPos(), 10);
                        if (other != null && other.getPlayer() != u.getPlayer()) {
                            combat(u, other);
                            //fought[u.getXPos()][u.getYPos()] = true;
                            //fought[other.getXPos()][other.getYPos()] = true;
                        }
                    }
                }
            }
        }
        for (int i = 0; i < units.length; i++) {
            for (int j = 0; j < units[0].length; j++) {
                if (units[i][j] != null && !units[i][j].hasMoved() && !fought[i][j]) {
                    units[i][j].updateStam(true);
                    autoMove(units[i][j]);
                }
            }
        }
        for (int i = 0; i < units.length; i++) {
            for (int j = 0; j < units[0].length; j++) {
                if (units[i][j] != null) {
                    units[i][j].updateStam(false);
                }
            }
        }
    }
    
    /*
    public void addTerrains(Terrain[][] terrains) {
        landscape = terrains;
    }
    */

}
