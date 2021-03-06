
/**
 * Represents the player(s) in the game.
 */
public class Player {
    private int bar;
    private Building camp;

    /**
     * Constructor for objects of class Player
     */
    public Player() {
        bar = 0;
    }
    
    /**
     * Adds a camp.
     */
    public void setCamp(Building acamp) {
        camp = acamp;
    }
    
    /**
     * Returns the camp.
     */
    public Building getCamp() {
        return camp;
    }
    
    /**
     * Increments the unit spawn bar (updates)
     */
    public void incrementBar() {
        if (bar < 100) {
            bar++;
        }
    }
    
    /**
     * Returns the charge level of the unit spawn bar.
     */
    public int getBar() {
        return bar;
    }
    
    /**
     * Decrements the unit spawn bar (unit spawned)
     */
    public void spawnUnit() {
        if (bar > 4) {
            bar -= 5;
        }
    }
}
