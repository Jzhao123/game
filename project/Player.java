
/**
 * Represents the player(s) in the game.
 */
public class Player {
    private int bar, morale;
    private Unit selected;

    /**
     * Constructor for objects of class Player
     */
    public Player() {
        bar = 0;
        morale = 100;
        selected = null;
    }
    
    /**
     * Tells a unit to be selected.
     */
    public void setSelected(Unit u) {
        selected = u;
    }
    
    /**
     * Returns the selected unit.
     */
    public Unit getSelected() {
        return selected;
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
        if (bar > 0) {
            bar--;
        }
    }
}
