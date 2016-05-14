
/**
 * Represents the player(s) in the game.
 */
public class Player {
    private int bar, morale;
    private Map map;
    private Unit selected;

    /**
     * Constructor for objects of class Player
     */
    public Player(Map gameMap) {
        bar = 0;
        morale = 100;
        map = gameMap;
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
}
