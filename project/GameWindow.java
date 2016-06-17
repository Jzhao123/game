import java.awt.*;
import javax.swing.JFrame;
import java.awt.image.*;

/**
 * Creates the game window.
 */
public class GameWindow extends JFrame{
	private static final long serialVersionUID = 1L;
	private final int width = 600;
    private final int height = 400;
    private Map map;
    private boolean running;
    private final int fps = 15;
    
    private BufferedImage buffer;
    private Insets insets;
    private Input input;
    
    /**
     * Runs the game.
     */
    public static void main(String[] args) {
        GameWindow window = new GameWindow();
        window.run();
        //System.exit(0);
    }
    
    /**
     * Constructs a new game window.
     */
    public GameWindow() {
        //Do nothing
    }
    
    /**
     * Tells this whether victory conditions have been met.
     */
    public void won() {
        running = false;
        draw();
    }
    
    /**
     * Gets the left margin.
     */
    public int getLeft() {
        return insets.left;
    }
    
    /**
     * Gets the right margin.
     */
    public int getTop() {
        return insets.top;
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
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        insets = getInsets();
        System.out.println("\n" + insets.top);
        setSize(width + insets.left + insets.right, height + insets.top + insets.bottom);
        buffer = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        map = Map.generateMap(width, height, this);
        running = true;
        setInput(new Input(this, map));
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

	public Input getInput() {
		return input;
	}

	public void setInput(Input input) {
		this.input = input;
	}
}
