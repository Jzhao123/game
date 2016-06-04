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
        Unit u = map.getUnit(w, h);
        if (e.getButton() == e.BUTTON1) {
            if (u != null) {
                map.getHumanPlayer().setSelected(u);
            } else if (map.canSpawn(w, h)) {
                map.addUnit(w, h);
            }
        } else if (e.getButton() == e.BUTTON2) {
            Unit u2 = map.getHumanPlayer().getSelected();
            if (u != null) {
                u2.setTarget(u.getXPos(), u.getYPos());
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
