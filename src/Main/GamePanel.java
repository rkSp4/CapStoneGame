package Main;

import Entity.Entity;
import Entity.Player;
import Object.SuperObject;
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


    int fps = 60;

    TileManager tileM = new TileManager(this);
    public KeyHandler keyH = new KeyHandler(this);

    sound music = new sound();
    sound se = new sound();

    public UI ui = new UI(this);

    public CollisionChecker cChecker = new CollisionChecker(this);
    public AssetSetter aSetter = new AssetSetter(this);

    //public EventHandler eHandler = new EventHandler(this);
    Thread gameThread;

    //PLAYER & ENTITY
    public Player player = new Player(this, keyH);
    public SuperObject[] obj = new SuperObject[10];
    public Entity[] npc = new Entity[10];

    //GAME STATE
    public int gameState;
    public final int titleState = 0;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int dialogueState = 3;
    public final int overState = 4;

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
        aSetter.setObject();
        aSetter.setNPC();
        gameState = titleState;
    }
    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run(){

        double drawInterval = 1000000000.0/fps;
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
        if(gameState == playState) {
            //PLAYER
            player.update();
            //NPC
            for(int i = 0; i <npc.length; i++) {
                if(npc[i] != null) {
                    npc[i].update();
                }
            }
        }
        if(gameState == pauseState){
            //nothing
        }
    }

    public void paintComponent(Graphics a) {
        super.paintComponent(a);
        Graphics2D a2 = (Graphics2D) a;

        //TITLE
        if(gameState == titleState){
            ui.draw(a2);
        }else{
            //TILE
            tileM.draw(a2);

            //OBJECT
            for(int i = 0; i < obj.length; i++){
                if(obj[i] != null)
                {
                    obj[i].draw(a2, this);
                }
            }

            //NPC
            for(int i = 0; i<npc.length; i++){
                if(npc[i]!=null){
                    npc[i].draw(a2);
                }
            }

            //PLAYER
            player.draw(a2);

            //UI
            ui.draw(a2);
        }

        a2.dispose();
    }
    public void playMusic(int i){
        music.setFile(i);
        music.play();
        music.loop();
    }
    public void stopMusic()
    {
        music.stop();
    }

    public void playSE(int i)
        {
        se.setFile(i);
        se.play();
    }
}
