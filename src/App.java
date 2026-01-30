

import javax.swing.JFrame;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");

        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("boilerplate");

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);

        window.pack(); //make the same size as gamepanel

        window.setLocationRelativeTo(null); //makes it at center 
        window.setVisible(true);
    }
}
