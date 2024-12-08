package Main;

//import Object.OBJ_KEY;
import javax.swing.*;

public class
Main {

    public static void main(String[] args) {
    JFrame window = new JFrame();
    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    window.setResizable(false);
    window.setTitle("2D Kitty Adventure");
    GamePanel gamePanel = new GamePanel();
    window.add(gamePanel);
    window.pack();
    window.setLocationRelativeTo(null);
    window.setVisible(true);
    gamePanel.setUpGame();


    gamePanel.startGameThread();
    }
}
