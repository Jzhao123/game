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
        System.out.println("hey you clicked the mouse");
        int w = e.getX();
        int h = e.getY();
        System.out.println(w + " " + h);
        Unit u = map.getUnit(w, h);
        if (e.getButton() == e.BUTTON1) {
            System.out.println("hey you clicked the left button");
            if (u != null) {
                System.out.println("selected");
                map.getHumanPlayer().setSelected(u);
            } else {
                System.out.println("lesse if map is good");
                if (map.canSpawn(w, h)) {
                    System.out.println("spawning");
                    map.addUnit(w, h);
                }
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
