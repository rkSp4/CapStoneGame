package Main;

import Entity.Entity;
import Entity.Player;
import Object.SuperObject;
import Tile.TileManager;

import java.awt.*;
import java.awt.image.BufferedImage;
//import java.sql.SQLOutput;
import javax.swing.JPanel;


public class GamePanel extends JPanel implements Runnable {
    //SCREEN SETTINGS
    final int originalTitleSize = 16;
    final int scale = 3;
    public final int tileSize = originalTitleSize * scale;
    public final int maxScreenCol = 20;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol;
    public final int screenHeight = tileSize * maxScreenRow;

    //WORLD SETTINGS
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 100;
    //public final int maxMap = 0;
    //public int currentMap = 0;
    //public int currentMap = 1;

    //FULLSCREEN
    int ScreenWidth2 = screenWidth;
    int ScreenHeight2 = screenHeight;
    BufferedImage tempScreen;
    Graphics2D g2;
    public boolean fullScreenOn = false;

    //FPS
    int fps = 60;

    //SYSTEM
    TileManager tileM = new TileManager(this);
    public KeyHandler keyH = new KeyHandler(this);
    sound music = new sound();
    sound se = new sound();
    public UI ui = new UI(this);
    public CollisionChecker cChecker = new CollisionChecker(this);
    public AssetSetter aSetter = new AssetSetter(this);
    public EventHandler eHandler = new EventHandler(this);
    Thread gameThread;

    //PLAYER & ENTITY PS:para nis mga NPC'S kaning gi comment out,rason kay para dili mu overlap ang npc sa lain map
    public Player player = new Player(this, keyH);
    public SuperObject[]/*[]*/ obj = new SuperObject[10];
    public Entity[]/*[]*/ npc = new Entity/*[maxMap]*/[10];
    public boolean devMode = false;

    //GAME STATE
    public int gameState;
    public final int titleState = 0;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int dialogueState = 3;
    public final int overState = 4;
    public final int optionState = 5;

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

        tempScreen = new BufferedImage(screenWidth, screenHeight, BufferedImage.TYPE_INT_ARGB);
        g2 = (Graphics2D)tempScreen.getGraphics();

        setFullScreen();
    }

    public void setFullScreen(){
        //GET LOCAL SCREEN DEVICE
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd = ge.getDefaultScreenDevice();
        gd.setFullScreenWindow(Main.window);

        //GET FULL SCREEN WIDTH AND HEIGHT
        ScreenWidth2 = Main.window.getWidth();
        ScreenHeight2 = Main.window.getHeight();
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
                drawToTempScreen();
                drawToScreen();
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
            for(int i = 0; i <npc/*[i]*/.length; i++) {
                if(npc/*[currentMap]*/[i] != null) {
                    npc/*[currentMap]*/[i].update();
                }
            }
        }
        if(gameState == pauseState){
            //nothing
        }


    }

    public void drawToTempScreen(){
        //DEBUG
        long drawStart = 0;
        if (keyH.checkDrawTime) {
            drawStart = System.nanoTime();
        }
        //TITLE
        if (gameState == titleState) {
            ui.draw(g2);
        } else {
            //TILE
            tileM.draw(g2);

            //OBJECT
            for (int i = 0; i < obj.length; i++) {
                if (obj[i] != null) {
                    obj[i].draw(g2, this);
                }
            }

            //NPC
            for (int i = 0; i < npc.length; i++) {
                if (npc[i] != null) {
                    npc[i].draw(g2);
                }
            }

            //PLAYER
            player.draw(g2);

            //UI
            ui.draw(g2);
        }
//        if(keyH.checkDrawTime == true)
//        {
//            long drawEnd = System.nanoTime();
//            long passed = drawEnd - drawStart;
//            a2.setColor(Color.red);
//            a2.drawString("Draw Time:" + passed, 10, 400);
//            System.out.println("Draw time: " + passed);
//        }
        if (keyH.checkDrawTime) {
            long drawEnd = System.nanoTime();
            long passed = drawEnd - drawStart;

            g2.setColor(Color.red);
            String drawTimeStr = "Draw Time: " + passed;
            FontMetrics fm = g2.getFontMetrics(g2.getFont());
            int strWidth = fm.stringWidth(drawTimeStr);
            int strHeight = fm.getHeight();
            int pad = 10;
            int boxX = 10 - pad;
            int boxY = 400 - strHeight - pad;
            int boxWidth = strWidth + 2 * pad;
            int boxHeight = strHeight + 2 * pad;
            int textX = boxX + (boxWidth - strWidth) / 2;
            int textY = boxY + strHeight + pad;
            ui.drawSubWindow(boxX, boxY, boxWidth, boxHeight);
            g2.setColor(Color.red);
            g2.drawString(drawTimeStr, textX, textY-10);

            System.out.println("Draw time: " + passed);
        }
    }

    public void drawToScreen(){

        Graphics g = getGraphics();
        g.drawImage(tempScreen, 0, 0, ScreenWidth2, ScreenHeight2, null);
        g.dispose();
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
