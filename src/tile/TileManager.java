package tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import main.GamePanel;

public class TileManager {

    GamePanel gp;
    Tile[] tile;
    int mapTileNum[][];

    public TileManager(GamePanel gp){

        this.gp = gp;

        tile = new Tile[10];
        mapTileNum = new int[gp.maxScreenColumn][gp.maxScreenRow];

        getTileImage();
        loadMap("/maps/map01.txt");
    }

    public void getTileImage(){
        try{
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/grass.png"));

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/wall.png"));

            tile[2] = new Tile();
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/water.png"));

        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public void loadMap(String filePath){
        try{
            InputStream is = getClass().getResourceAsStream(filePath);
            if (is == null) {
                throw new RuntimeException("Cannot find /maps/map01.txt (check resources path)");
            }
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while(col < gp.maxScreenColumn && row < gp.maxScreenRow){

                String line = br.readLine();

                while(col < gp.maxScreenColumn){
                    String numbers[] = line.split(" ");

                    int num = Integer.parseInt(numbers[col]);
                    System.out.println(num);

                    mapTileNum[col][row] = num;
                    col++;
                } 
                if(col == gp.maxScreenColumn){
                    col = 0;
                    row++;
                }
            }
            br.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2){
        
        int col = 0;
        int row = 0;
        int x = 0;
        int y = 0;

        while(col < gp.maxScreenColumn && row < gp.maxScreenRow){

            int tileNum = mapTileNum[col][row]; 

            //System.out.println(tileNum);
            g2.drawImage(tile[tileNum].image, x, y, gp.tileSize, gp.tileSize, null);
            col++;
            x += gp.tileSize;

            if(col == gp.maxScreenColumn){
                col = 0;
                x = 0;
                row++;
                y += gp.tileSize;
            }
        } 
    }
}
