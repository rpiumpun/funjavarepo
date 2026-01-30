

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Dimension;

public class GamePanel extends JPanel{
    //Screen settings
    final int originalFileSize = 16; //16x16 tile
    final int scale = 3; //multilier of file size

    final int tilesize = originalFileSize * scale; // we use 48x48 tile
    final int maxScreenColumn = 16;
    final int maxScreenRow = 12;
    final int screenWidth = tilesize * maxScreenColumn; //768
    final int screenHeight = tilesize * maxScreenRow; //576

    public GamePanel() {

        this.setPreferredSize(new Dimension(screenWidth, screenHeight)); //makes run faster dont complain
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);

    }
}