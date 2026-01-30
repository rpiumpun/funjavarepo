
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class GamePanel extends JPanel implements Runnable{
    //Screen settings
    final int originalFileSize = 16; //16x16 tile
    final int scale = 3; //multilier of file size

    final int tileSize = originalFileSize * scale; // we use 48x48 tile
    final int maxScreenColumn = 16;
    final int maxScreenRow = 12;
    final int screenWidth = tileSize * maxScreenColumn; //768
    final int screenHeight = tileSize * maxScreenRow; //576

    //FPS
    int FPS = 60;

    KeyHandler keyH = new KeyHandler();
    Thread gameThread;

    //Setplayers Default position
    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 4;

    public GamePanel() {

        this.setPreferredSize(new Dimension(screenWidth, screenHeight)); //makes run faster dont complain
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true); //gamepanel can be focused to any input

    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();// run the run method
    }

    @Override
    /* sleep method
    public void run() {
        //When gameThread is used, it will automatically call this run function

        double drawInterval = 1000000000/FPS; //0.0166666 seconds per frame
        double nextDrawTime = System.nanoTime() + drawInterval;

        //Game loop is in this funcion
        while(gameThread != null){

            //todo in this: 1 update information, 2 draw screen which is done in functions below
            update();
            repaint();//call paintComponent function
            
            try{
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime / 1000000;

                if(remainingTime < 0){
                    remainingTime = 0;
                }

                Thread.sleep((long) remainingTime);
                nextDrawTime += drawInterval;

            } catch (InterruptedException e){
                e.printStackTrace();
            }
            
        }
    }
    */
    public void run(){

        //delta way of including FPS
        double drawInterval = 1000000000/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        long drawCount = 0;

        while(gameThread != null){

            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            timer += currentTime - lastTime;
            lastTime = currentTime;

            if(delta >= 1){
                update();
                repaint();
                delta--;
                drawCount++;
            }

            //displaying FPS
            if(timer >= 1000000000){
                System.out.println("FPS" + drawCount);
                drawCount = 0;
                timer = 0;
            }
        }
    }

    public void update(){

        if(keyH.upPressed == true){
            playerY -= playerSpeed;
        }
        else if(keyH.downPressed == true){
            playerY += playerSpeed;
        }
        else if(keyH.leftPressed == true){
            playerX -= playerSpeed;
        }
        else if(keyH.rightPressed == true){
            playerX += playerSpeed;
        }
    }

    public void paintComponent(Graphics g){

        super.paintComponent(g);// super = use parent stuff aka Jpanel
        
        Graphics2D g2 = (Graphics2D)g; //graphic2d extending graphics give more stuff todo :D

        g2.setColor(Color.white);
        g2.fillRect(playerX, playerY, tileSize, tileSize);
        g2.dispose();//dispose of this graphic content and release any system resource that is using
    }
}