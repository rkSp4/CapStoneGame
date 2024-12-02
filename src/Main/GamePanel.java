package Main;

import Entity.Player;
import Tile.TileManager;

import java.awt.*;
import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable {
    final int originalTitleSize = 16;
    final int scale = 3;

    public final int tileSize = originalTitleSize * scale;
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol;
    public final int screenHeight = tileSize * maxScreenRow;


    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;
    public final int worldWidth = tileSize * maxWorldCol;
    public final int worldHeight = tileSize * maxWorldRow;

    int fps = 60;

    TileManager tileM = new TileManager(this);
    KeyHandler keyH = new KeyHandler();
    sound sound = new sound();
    Thread gameThread;
    public CollisionChecker cChecker = new CollisionChecker(this);
    //public AssetSetter aSetter = new AssetSetter(this);
    public Player player = new Player(this, keyH);
    //public SuperObject obj[] = new SuperObject[10];


    //player default pos
    int playx = 100;
    int playy = 100;
    int playerspeed = 4;
    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);

    }
    public void setUpGame(){
        playMusic(0);
    }
    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();

    }

//    public void setupGame() {
//        aSetter.setObject();
//    }
    @Override
    public void run(){

        double drawInterval = 1000000000/fps;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currTime;
        long timer = 0;
        long drawCount = 0;

        while(gameThread != null) {

            currTime = System.nanoTime();
            delta += (currTime - lastTime) / drawInterval;
            timer += (currTime - lastTime);
            lastTime = currTime;

            if(delta >= 1) {
                update();
                repaint();
                delta--;
                drawCount++;
            }

            if(timer >= 1000000000){
                System.out.println("FPS:" + drawCount);
                drawCount = 0;
                timer = 0;
            }
        }

        /*double nextdrawTime = System.nanoTime() + drawInterval;

        while(gameThread != null){

        update();
        repaint();

        try {
            double remTime = nextdrawTime - System.nanoTime();
            remTime = remTime/1000000;

            if(remTime < 0){
                remTime = 0;
            }

            Thread.sleep((long) remTime);
            nextdrawTime += drawInterval;
        }catch(InterruptedException e){
            e.printStackTrace();
        }

        } */
    }
    public void update(){
        player.update();
    }
    public void paintComponent(Graphics a) {
        super.paintComponent(a);
        Graphics2D a2 = (Graphics2D) a;
        tileM.draw(a2);
        player.draw(a2);
        a2.dispose();

    }
    public void playMusic(int i){
        sound.setFile(i);
        sound.play();
        sound.loop();
    }
    public void stopMusic(){
        sound.stop();
    }
    public void playSE(int i){
        sound.setFile(i);
        sound.play();

    }
}
