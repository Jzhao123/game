import java.awt.*;
import javax.swing.JFrame;
import java.awt.image.*;
import java.awt.event.*;

/**
 * Creates the game window.
 */
public class GameWindow extends JFrame{
    private final int width = 900;
    private final int height = 600;
    private Map map;
    private boolean running;
    private final int fps = 30;
    
    private BufferedImage buffer;
    private Insets insets;
    
    /**
     * Constructs a new game window.
     */
    public GameWindow() {
        map = new Map(width/6, height/6, this);
    }
    
    /**
     * Runs the game
     */
    public void run() {
        init();
        while (running) {
            long time = System.currentTimeMillis();
            update();
            draw();
            time = (1000/fps) - (System.currentTimeMillis() - time);
            if (time > 0) {
                try {
                    Thread.sleep(time);
                } catch (Exception e) {
                }
            }
        }
        setVisible(false);
    }
    
    /**
     * Initializes game circumstances
     */
    public void init() {
        setTitle("El Proyecto de AP");
        insets = getInsets();
        setSize(width + insets.left + insets.right, height + insets.top + insets.bottom);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        buffer = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        map = Map.generateMap(width, height, this);
    }
    
    /**
     * Updates every frame
     */
    public void update() {
        map.updateConditions();
    }
    
    /**
     * Draws things
     */
    public void draw() {
        Graphics g = getGraphics();
        Graphics bbg = buffer.getGraphics();
        map.drawImage(bbg, buffer);
        g.drawImage(buffer, 0, 0, this);
    }
    
}
