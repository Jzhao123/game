import java.awt.*;
import javax.swing.JFrame;
import java.awt.image.*;
import java.awt.event.*;

/**
 * Creates the game window.
 */
public class GameWindow extends JFrame{
    private final int width = 600;
    private final int height = 400;
    private Map map;
    private boolean running;
    private final int fps = 2;
    
    private BufferedImage buffer;
    private Insets insets;
    private Input input;
    
    /**
     * Runs the game.
     */
    public static void main(String[] args) {
        GameWindow window = new GameWindow();
        System.out.println("\n\n\n");
        window.init();
        System.out.println("initialized");
        window.run();
        //System.exit(0);
    }
    
    /**
     * Constructs a new game window.
     */
    public GameWindow() {
        map = new Map(width/6, height/6, this);
    }
    
    /**
     * Tells this whether victory conditions have been met.
     */
    public void won() {
        running = false;
        draw();
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
                    System.out.println(e.getMessage());
                }
            }
        }
        //setVisible(false);
    }
    
    /**
     * Initializes game circumstances
     */
    public void init() {
        setTitle("APCS RTS Project");
        insets = getInsets();
        setSize(width + insets.left + insets.right, height + insets.top + insets.bottom);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        buffer = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        System.out.println("initialized window");
        map = Map.generateMap(width, height, this);
        System.out.println("initialized map");
        running = true;
        input = new Input(this, map);
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
        g.drawImage(buffer, insets.left, insets.top, this);
    }
}
