
/**
 * Contains the basic attributes of a single soldier.
 * 
 */
public class Unit {
    private double maxhp, hp;
    private double atk, def;
    private double exp;
    private double xPos, yPos;

    /**
     * Constructor for objects of class Unit
     */
    public Unit(double health, double attack, Map gameMap) {
        hp = maxhp = health;
        atk = attack;
        def = 0;
        exp = 0;
    }
    /**
     * Returns the amount of remaining health 
     */
    public double getHealth() {
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
     * Takes damage. Returns whether or not the unit is still alive.
     */
    public boolean hit(double damage) {
        hp -= damage;
        return (hp > 0);
    }
}