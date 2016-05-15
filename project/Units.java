import java.awt.Color;

/**
 * Contains methods for some default units.
 */
public class Units {
    /**
     * Returns a default leigonnaire. 
     */
    public static Unit getLegionnaire(Map map) {
        return new Unit(100, 20, map, 10, Color.yellow);
    }
}
