import java.awt.Color;

/**
 * Contains the basic attributes of a single soldier.
 * 
 */
public class Unit {
    private int maxhp, hp, xPos, yPos, tX, tY;
    private double atk;
    private boolean moved, selected;
    private Color color;
    private Player player;
    private Map map;

    /**
     * Constructor for objects of class Unit
     */
    public Unit(int health, double attack, int stamina, Color c) {
        hp = maxhp = health;
        atk = attack;
        color = c;
    }
    
    /**
     * Adds a map to the unit.
     */
    public void setMap(Map gameMap) {
        map = gameMap;
    }
    
    /**
     * Assigns the unit to a player.
     */
    public void setPlayer(Player p) {
        player = p;
    }
    
    /**
     * Returns the player object the unit is associated with.
     */
    public Player getPlayer() {
        return player;
    }
    
    /**
     * Returns the color of the unit.
     */
    public Color getColor() {
        return color;
    }
    
    /**
     * Returns the amount of remaining health 
     */
    public int getHealth() {
        return hp;
    }
    
    /**
     * Returns the attack stat of the unit
     */
    public double getAttack() {
        return atk;
    }
    
    /**
     * Returns the x-position of the object.
     */
    public int getXPos() {
        return xPos;
    }
    
    /**
     * Returns the y-position of the object.
     */
    public int getYPos() {
        return yPos;
    }
    
    /**
     * Sets the x-position of the object to x.
     */
    public void setXPos(int x) {
        xPos = x;
    }
    
    /**
     * Sets the y-position of the object to y.
     */
    public void setYPos(int y) {
        yPos = y;
    }
    
    /**
     * Takes damage. Returns whether or not the unit is still alive.
     */
    public boolean hit(double damage) {
        hp -= damage;
        return (hp > 0);
    }
    
    /**
     * Returns the move status.
     */
    public boolean hasMoved() {
        return moved;
    }
    
    /**
     * Returns the current x-position of the target of the unit
     */
    public int getTargetX() {
        return tX;
    }
    
    /**
     * Returns the current y-position of the target of the unit
     */
    public int getTargetY() {
        return tY;
    }
    
    /**
     * Sets a new target for the unit
     */
    public void setTarget(int x, int y) {
        tX = x;
        tY = y;
    }
    
    /**
     * Resets the unit's move status.
     */
    public void updateStam(boolean x) {
        moved = x;
    }
    
    /**
     * Selects/deselects this unit.
     */
    public void select(boolean x) {
        selected = x;
    }
    
    /**
     * Gets the selction status of this unit.
     */
    public boolean isSelected() {
        return selected;
    }
}
