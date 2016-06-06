import java.awt.*;
import java.awt.image.*;
import java.util.*;

/**
 * Represents a map.
 */
public class Map {
    //private Terrain[][] landscape;
    
    private Building[][] buildings;
    private Unit[][] units; //map array, use to find adjacent squares
    private Player player, computer;
    private int width, height;
    private final static int scale = 8;
    private GameWindow window;
    private boolean won, who;

    /**
     * Constructor for objects of class Map
     */
    public Map(int mapWidth, int mapHeight, GameWindow gameWindow) {
        width = mapWidth;
        height = mapHeight;
        //landscape = new Terrain[width][height];
        buildings = new Building[width][height];
        units = new Unit[width][height];
        player = new Player();
        computer = new Player();
        window = gameWindow;
    }
    
    /**
     * Prints the buildings.
     */
    public void printBuildings() {
        int num = 0;
        System.out.println(buildings.length);
        System.out.println(buildings[0].length);
        for (int i = 0; i < buildings.length; i++) {
            for (int j = 0; j < buildings[0].length; j++) {
                if (buildings[i][j] != null) {
                    num++;
                }
            }
        }
        System.out.println(num);
    }

    /**
     * Generates basic map obeject
     */
    public static Map generateMap(int mapWidth, int mapLength, GameWindow window) {
        Map map = new Map(mapWidth/scale, mapLength/scale, window);
        System.out.println("created map");
        //map.generateBasic();
        map.addBuilding(Buildings.getCamp(2, 2, 3, 3));
        System.out.println("done with first camp");
        map.addBuilding(Buildings.getCamp(mapWidth/scale - 5, mapLength/scale - 5, 3, 3));
        System.out.println("added buildings");
        map.printBuildings();
        map.addCompUnit(mapWidth/scale - 8, mapLength/scale - 16);
        map.addCompUnit(mapWidth/scale - 16, mapLength/scale - 8);
        map.addCompUnit(mapWidth/scale - 12, mapLength/scale - 12);
        return map;
    }

    /**
     * Gets the unit at the said location *on the window*.
     */
    public Unit getUnit(int w, int h) {
        return units[w/scale][h/scale];
    }
    
    /**
     * Returns if the selected square is in the spawn zone.
     */
    public boolean canSpawn(int w, int h) {
        return (units[w/scale][h/scale] == null && buildings[w/scale][h/scale] == null);
    }

    /**
     * Returns the actual player of the game (not the computer).
     */
    public Player getHumanPlayer() {
        return player;
    }

    /**
     * Returns the computer player of the game.
     */
    public Player getCompPlayer() {
        return computer;
    }

    /**
     * Adds a unit to the map.
     */
    public void addUnit(int xPos, int yPos) {
        if (player.getBar() > 5) {
            player.spawnUnit();
            Unit u = Units.getLegionnaire();
            u.setPlayer(player);
            u.setXPos(xPos/scale);
            u.setYPos(yPos/scale);
            u.setTarget(computer.getCamp().getXPos(), computer.getCamp().getYPos());
            System.out.println(u.getTargetX() + " " + u.getTargetY());
            units[xPos/(scale)][yPos/(scale)] = u;
        }
    }
    
    /**
     * Adds a computer-controlled unit to the map.
     */
    public void addCompUnit(int x, int y) {
        Unit u = Units.getBarbarian();
        u.setPlayer(computer);
        u.setXPos(x);
        u.setYPos(y);
        u.setTarget(player.getCamp().getXPos(), player.getCamp().getYPos());
        System.out.println(u.getTargetX() + " " + u.getTargetY());
        units[x][y] = u;
    }

    /**
     * Adds a building to the map.
     */
    public void addBuilding(Building b) {
        int x = b.getXPos();
        int y = b.getYPos();
        int xlen = b.getXLen();
        int ylen = b.getYLen();
        System.out.println(x + " " + y + " " + xlen + " " + ylen + " ");
        for (int i = x; i < x + xlen; i++) {
            for (int j = y; j < y + ylen; j++) {
                buildings[i][j] = b;
                System.out.println("ta");
            }
        }
        if (player.getCamp() == null) {
            player.setCamp(b);
        } else if (computer.getCamp() == null) {
            computer.setCamp(b);
        }
    }

    /**
     * Automoves a single unit on the map (does not initiate conflict). 
     */
    private void autoMove(Unit u) {
        System.out.println("Moving a unit");
        int x = u.getTargetX();
        int y = u.getTargetY();
        int finalX = u.getXPos();
        int finalY = u.getYPos();
        System.out.println(x + " " + y + " " + finalX + " " + finalY);
        if (x > finalX) {
            finalX++;
        } else if (x < finalX) {
            finalX--;
        }
        if (y > finalY) {
            finalY++;
        } else if (y < finalY) {
            finalY--;
        }
        if (units[finalX][finalY] == null) {
            units[u.getXPos()][u.getYPos()] = null;
            u.setXPos(finalX);
            u.setYPos(finalY);
            units[finalX][finalY] = u;
            if (buildings[finalX][finalY] != null) {
                if (u.getPlayer() == computer && buildings[finalX][finalY] == player.getCamp()) {
                    window.won();
                    won = true;
                    who = false;
                } else if (u.getPlayer() == player && buildings[finalX][finalY] == computer.getCamp()) {
                    window.won();
                    won = true;
                    who = true;
                }
            }
        } else {
            combat(u, units[finalX][finalY]);
        }
    }

    /**
     * Determines whether or not a unit is in range; if so, returns that unit.
     */
    private Unit getUnitInRange(Unit u) {
        System.out.println(u.getXPos());
        System.out.println(u.getYPos());
        for (int i = -3; i <= 3; i++) {
            for (int j = -3; j <= 3; j++) {
                try {
                    if (i != 0 || j != 0) {
                        //System.out.println("testing testing " + i2 + " " + j2 + " " + width + " " + height);
                        Unit u2 = units[u.getXPos() + i][u.getYPos() + j];
                        //System.out.println("at least here?" + u2 == null);
                        if (u2 != null) {
                            System.out.println("found a unit in range woooooooohoooooo");
                            return u2;
                        }
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
        return null;
    }

    /**
     * Simulates combat between two units.
     */
    private void combat(Unit attacker, Unit defender) {
        units[defender.getXPos()][defender.getYPos()] = null;
        units[attacker.getXPos()][attacker.getYPos()] = null;
    }

    /**
     * Draws the current state of the map into the graphics object.
     */
    public void drawImage(Graphics g, BufferedImage image) {
        //System.out.println(Arrays.toString(buildings));
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                //g.setColor(landscape[i][j].getColor());
                if (buildings[i][j] != null) {
                    g.setColor(buildings[i][j].getColor());
                } else {
                    g.setColor(Color.white);
                }
                //System.out.println(g.getColor());
                g.fillRect(scale*i, scale*j, scale, scale);
            }
        }
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (units[i][j] != null) {
                    g.setColor(units[i][j].getColor());
                    g.fillOval(i*scale, j*scale, scale, scale);
                } else {
                    g.setColor(Color.white);
                }
            }
        }
        if (won) {
            g.setColor(Color.black);
            if (who) {
                System.out.println("called");
                g.drawString("You've Won!", width*scale/2 - 40, height*scale/2 - 2);
            } else {
                g.drawString("You've lost.", width*scale/2 - 40, height*scale/2 - 2);
            }
        }
    }

    /**
     * Updates the current conditions of the game map. 
     */
    public void updateConditions() {
        player.incrementBar();
        //sometime in the future this needs to take buildings into account as well
        boolean[][] fought = new boolean[units.length][units[0].length];
        Unit u = null;
        Unit other = null;
        for (int i = 0; i < units.length; i++) {
            for (int j = 0; j < units[0].length; j++) {
                if (!fought[i][j]) {
                    u = units[i][j];
                    if (u != null) {
                        System.out.println("finding units in range");
                        other = getUnitInRange(u);
                        if (other != null) {
                            System.out.println("fighting");
                            combat(u, other);
                            //fought[u.getXPos()][u.getYPos()] = true;
                            //fought[other.getXPos()][other.getYPos()] = true;
                        }
                    }
                }
            }
        }
        for (int i = 0; i < units.length; i++) {
            for (int j = 0; j < units[0].length; j++) {
                if (units[i][j] != null && !units[i][j].hasMoved() && !fought[i][j]) {
                    units[i][j].updateStam(true);
                    autoMove(units[i][j]);
                }
            }
        }
        for (int i = 0; i < units.length; i++) {
            for (int j = 0; j < units[0].length; j++) {
                if (units[i][j] != null) {
                    units[i][j].updateStam(false);
                }
            }
        }
    }
    
        /*
    public void presetMap() {
        //set all to null.
        for(int a=0; a<landscape.length;a++) {
            for(int b=0; b<landscape[0].length;b++) {
                landscape[a][b]=null;
            }
        }
        //set water border
        int negCol = 0;
        int negRow = 0;
        for(int twice=0; twice<2;twice++) {
            for(int r=0; r< landscape.length;r++) {
                if(negCol!=0 && r>landscape[0].length/2){ // adds some more sporadic "natural" spawn patterns
                    landscape[r][negCol] = generator.getWater();
                } else {
                    landscape[r][negCol] = generator.getMountain(); 
                }
            }
            negCol = landscape[0].length-1;
        }
        for(int twicenice=0; twicenice<2;twicenice++) {
            for(int col=0; col < landscape[0].length;col++) {
                //if()
                landscape[negRow][col] = generator.getWater();
            }
            negRow = landscape.length -1;
        }
        //set river randomly
        int e=(int)(Math.random()*landscape.length-1)+1;
        int f=0;
        for(int rivercount=0;rivercount<3;rivercount++) {
            while(e<landscape.length&&f<landscape[0].length) {
                f=e/(landscape.length/scale);
                if(rivercount==2) {
                    f=landscape.length-f; // can someone pls check the logic of the river spawn.
                }
                landscape[e][f]=generator.getWater();
                e++;
            }
        }
        //set road (should I make the roads just a cross along the map like --|--)
        int yDiv = landscape[0].length/2;
        int xDiv = landscape.length/2; 
        int xLine=1;
        int yLine=1;
        while(xLine<landscape[0].length-1) {
            landscape[xDiv][xLine]=generator.getRoad();
            xLine++;
        }
        while(yLine<landscape.length-1) {
            landscape[yLine][yDiv]=generator.getRoad();
            yLine++;
        }
        //set hospital terrain
        int numHosp= ((int)Math.random()*4);
        int hospCount=0;
        while(hospCount<numHosp) {
            int x=((int)Math.random()*landscape[0].length-1)+1;
            int y=((int)Math.random()*landscape.length-1)+1;
            if(landscape[x][y]==null) {
                landscape[x][y]=generator.getHospital();
                hospCount++;
            }
        }
        //set mountain
        int chainLength=0;
        int mountCount=0; //:)
        int mountLimit=((int)Math.random()*6)+1;
        int directionM=0;
        while(mountCount<mountLimit) {
            int x=((int)Math.random()*(landscape[0].length-1))+1;
            int y=((int)Math.random()*landscape.length-1)+1;
            if(landscape[x][y]==null) {
                chainLength=((int)Math.random()*3)+1;
                directionM=((int)Math.random()*5)+1;
                for(int chainCount=0;chainCount<chainLength;chainCount++) {
                    if(chainCount==0) {
                        landscape[x][y]=generator.getMountain();
                    } else {
                        if(directionM>2) {
                            landscape[x][y+(directionM%2)]= generator.getMountain();
                        } else {
                            landscape[x+(directionM)][y]=generator.getMountain();
                        }   
                    }
                }
                mountCount++;
            }
        }
        //sets camps
        int numCamps= ((int)Math.random()*8)+1;
        int campsCount=0;
        while(campsCount<numCamps) {
            int x=((int)Math.random()*landscape[0].length-1)+1;
            int y=((int)Math.random()*landscape.length-1)+1;
            if(landscape[x][y]==null) {
                buildings[x][y]=gen.getCamp(1,1,x,y);
                campsCount++;
            }
        }
        //sets Castle
        int numCast= ((int)Math.random()*5)+1;
        int castCount=0;
        while(castCount<numCast) {
            int x=(int)(Math.random()*landscape[0].length-1)+1;
            int y=(int)(Math.random()*landscape.length-1)+1;
            if(landscape[x][y]==null) {
                buildings[x][y]=gen.getCastle(1,1,x,y);
                castCount++;
            }
        }
        //sets remaining null blocks to fields
        for(int a=0; a<landscape.length;a++) {
            for(int b=0; b<landscape[0].length;b++) {
                if(landscape[a][b] == null) {
                    landscape[a][b] = generator.getField();
                }
            }
        }
    }
    */
    
    /*
    public Terrain[][] generateBasic() {
        for(int a=0; a<landscape.length;a++) {
            for(int b=0; b<landscape[0].length;b++) {
                if(landscape[a][b] == null) {
                    landscape[a][b] = Terrains.getField();
                }
            }
        }
        return landscape;
    }
    */
   
    /*
    private void moveUnit(int ox, int oy, int nx, int ny) {
        if(units[ox][oy] != null) {
            if((Math.abs(ox-nx) + Math.abs(oy-ny))>units[ox][oy].getStamina()) {
                System.out.print("Unable to move forward.");
            } else {
                units[ox][oy].updateStam(Math.abs(ox-nx) + Math.abs(oy-ny));
                units[nx][ny] = units[ox][oy];
                units[ox][oy] = null;
            }
        } else {
            System.out.print("There is no unit in that location.");
        }
    }
    */
   
    /*
    public void addTerrains(Terrain[][] terrains) {
        landscape = terrains;
    }
    */

}
