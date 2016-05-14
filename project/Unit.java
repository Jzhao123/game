
/**
 * Contains the basic attributes of a single soldier.
 * 
 */
public class Unit {
    private double maxhp, hp;
    private double atk, def;
    private double exp;
    private double xPos, yPos;
    private int curStam;

    /**
     * Constructor for objects of class Unit
     */
    public Unit(double health, double attack, Map gameMap,int stamina) {
        hp = maxhp = health;
        atk = attack;
        def = 0;
        exp = 0;
        curStam = stamina;
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
    public double getXPos()
    {
        return xPos;
    }
    public double getYPos()
    {
        return yPos;
    }
    /**
     * Takes damage. Returns whether or not the unit is still alive.
     */
    public boolean hit(double damage) {
        hp -= damage;
        return (hp > 0);
    }
    public int getStamina()
    {
        return curStam;
    }
    public void updateStam(int usedStam)
    {
           curStam = curStam - usedStam;
    }
    }
}
