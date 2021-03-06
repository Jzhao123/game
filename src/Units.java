import java.awt.Color;

/**
 * Contains methods for some default units.
 */
public class Units {
    /**
     * Returns a default leigonnaire. 
     */
    public static Unit getLegionnaire() {
        return new Unit(100, 20, 10, Color.red);
    }
    
    /**
     * Returns a default barbarian. 
     */
    public static Unit getBarbarian() {
        return new Unit(100, 20, 10, Color.darkGray);
    }
}
