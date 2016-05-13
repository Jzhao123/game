
/**
 * Contains the basic attributes of a single soldier.
 * 
 */
public class Unit {
    private int maxhp, hp;
    private int atk, def;
    private int exp;
    private int x, y;

    /**
     * Constructor for objects of class Unit
     */
    public Unit(int health, int attack, Map gameMap) {
        hp = maxhp = health;
        atk = attack;
        def = 0;
        exp = 0;
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
    public int getAttack() {
        return atk;
    }
    
    /**
     * Returns the defense stat of the unit
     */
    public int getDefense() {
        return def;
    }
    
    /**
     * Takes damage. Returns whether or not the unit is still alive.
     */
    public boolean hit(int damage) {
        hp -= damage;
        return (hp > 0);
    }
}
