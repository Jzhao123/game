
/**
 * Represents a map.
 */
public class Map {
    private Terrain[][] landscape;
    private Building[][] buildings;
    private Unit[][] units;
    private int width, height;

    /**
     * Constructor for objects of class Map
     */
    public Map(Terrain[][] terrains) {
        landscape = terrains;
        width = landscape.length;
        height = landscape[0].length;
        buildings = new Building[width][height];
        units = new Unit[width][height];
    }
    
    /**
     * Adds a unit to the map.
     */
    public void addUnit(Unit u, int xPos, int yPos) {
        units[xPos][yPos] = u;
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
     * Moves a unit at (ox, oy) towards a destination at (nx, ny).
     * [Must account for other units, terrain, buildings, etc. -- not an easy method]
     */
    public void moveUnit(int ox, int oy, int nx, int ny) {
        if(units[ox][oy] != null)
        {
            if((Math.abs(ox-nx) + Math.abs(oy-ny))>units[ox][oy].getStamina)
            {
                //Message says can't move
            }
            else
            {
                units[ox][oy].updateStam(Math.abs(ox-nx) + Math.abs(oy-ny))
                units[nx][ny] = units[ox][oy];
                units[ox][oy] = null;
            }
        }
        else
        //Message says no unit on grid position
        
    }
    private void combat(Unit attacker,Unit defender)
    {
        defender.hit(attacker.getAttack * (1-(defender.getDefense * (3/4))));
        attacker.hit((defender.getDefense * 50) * (1-(attacker.getDefense * (1/3))));
    }
    
    
}
