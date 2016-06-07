import java.awt.Component;
import java.awt.event.*;

/**
 * Handles player actions
 */
public class Input implements MouseListener {
    private Map map;
    
    /**
     * Constructor for objects of class Input
     */
    public Input(Component c, Map gameMap) {
        c.addMouseListener(this);
        map = gameMap;
    }

    /**
     * Registers when the mouse is clicked.
     */
    public void mouseClicked(MouseEvent e) {
        int w = e.getX();
        int h = e.getY();
        w = (w - map.getLeft())/Map.scale;
        h = (h - map.getTop())/Map.scale;
        if (e.getButton() == e.BUTTON1) {
            Unit u = map.getUnit(w, h);
            if (u != null) {
                if (u.getPlayer() == map.getHumanPlayer()) {
                    map.setSelected(u);
                }
            } else {
                if (map.canSpawn(w, h)) {
                    map.addUnit(w, h);
                    map.setSelected(null);
                }
            }
        } else if (e.getButton() == e.BUTTON3) {
            System.out.println("right click registered");
            Unit u2 = map.getSelected();
            if (u2 != null) {
                u2.setTarget(w, h);
                System.out.println("target reset");
            }
        }
    }
    
    //Dummy methods
    
    public void mouseEntered(MouseEvent e) {
        
    }
    
    public void mouseExited(MouseEvent e) {
        
    }
    
    public void mousePressed(MouseEvent e) {
        
    }
    
    public void mouseReleased(MouseEvent e) {
        
    }
}
