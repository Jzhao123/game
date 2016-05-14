import java.awt.Color;

/**
 * Contains the basic attributes of a single soldier.
 * 
 */
public class Unit {
    private int maxhp, hp;
    private double atk, def;
    private double exp;
    private int xPos, yPos;
    private int maxStam, curStam;
    private Color color;

    /**
     * Constructor for objects of class Unit
     */
    public Unit(int health, double attack, Map gameMap, int stamina, Color c) {
        hp = maxhp = health;
        atk = attack;//Stock Attack is 50, increases as exp goes up
        def = 0;//Precondition: Defense must be between 0 and 1
        exp = 0;
        curStam = maxStam = stamina;
        color = c;
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
     * Returns the defense stat of the unit
     */
    public double getDefense() {
        return def;
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
     * Takes damage. Returns whether or not the unit is still alive.
     */
    public boolean hit(double damage) {
        hp -= damage;
        return (hp > 0);
    }
    
    /**
     * Returns the remaining stamina of the object.
     */
    public int getStamina() {
        return curStam;
    }
    
    /**
     * Updates the stamina of the object. Returns true if the object still has stamina.
     */
    public boolean updateStam(int usedStam) {
           curStam = curStam - usedStam;
           return (curStam > 0);
    }
    
}
