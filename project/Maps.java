
/**
 * Contains unused map generation methods.
 */
public class Maps {
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
}
