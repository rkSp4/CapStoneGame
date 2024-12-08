package Main;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;
import Object.SuperObject;
import Object.OBJ_KEY;
import Object.OBJ_TIME;
import Object.OBJ_HEART;

public class UI {

    GamePanel gp;
    Graphics2D g2;
    BufferedImage hfull,hhalf,hblank;
    BufferedImage keyI;
    BufferedImage timeI;
    int messagectr = 0;

    Font arial_40, arial_80B;
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    public boolean gameFinished = false;
    public String currentDialogue = "";
    public int commandNum = 0;
    public int titleScreenState = 0;


   double playTime;
   // DecimalFormat dFormat = new DecimalFormat("#0.00");

    public UI(GamePanel gp) {
        this.gp = gp;

        arial_40 = new Font("Arial", Font.PLAIN, 40);
        arial_80B = new Font("Arial", Font.BOLD, 80);
        OBJ_KEY key = new OBJ_KEY(gp);
        OBJ_TIME time = new OBJ_TIME(gp);
        timeI = time.image;
        keyI = key.image;


        //CREATE HUB OBJECT
        SuperObject heart = new OBJ_HEART(gp);
        hfull = heart.image;
        hhalf = heart.image2;
        hblank = heart.image3;
    }

    public void showMessage(String text){

        message = text;
        messageOn = true;
    }

    public void draw(Graphics2D g2){
        this.g2 = g2;
        g2.setFont(arial_40);
        g2.setColor(Color.white);


        //TITLE STATE
        if(gp.gameState == gp.titleState){
            drawTitleScreen();
        }
        //PLAY STATE
        if(gp.gameState == gp.playState){
            //KEY UI
            g2.drawImage(keyI, gp.tileSize/2, gp.tileSize/2 + 60, gp.tileSize, gp.tileSize, null);
            g2.drawString("x " + gp.player.haskey, 74, 125);
            //TIME
            playTime += (double)1/60;
            DecimalFormat dFormat = new DecimalFormat("#0.00");
            g2.setColor(Color.black);
            g2.fillRoundRect(gp.tileSize * 11, 18, gp.tileSize * 4, 63,35, 35);
            g2.setColor(Color.white);
            g2.drawString(":"+dFormat.format(playTime), gp.tileSize*12, 65);
            g2.drawImage(timeI, gp.tileSize*11 + 7, gp.tileSize/2, gp.tileSize, gp.tileSize, null);

            drawPlayerLife();
        }//PAUSE STATE
        if(gp.gameState == gp.pauseState){
            drawPlayerLife();
            drawPauseScreen();

        }
        if(gp.gameState == gp.overState){
            gp.stopMusic();
            //implement game over music
            drawOverScreen();
        }

        //DIALOGUE
        if(gp.gameState == gp.dialogueState) {
            drawPlayerLife();
            drawDialogueScreen();
        }

        if(messageOn)
        {
            g2.setFont(g2.getFont().deriveFont(30F));
            g2.drawString(message, gp.tileSize/2, gp.tileSize*5 );

            messagectr++;

            if(messagectr > 120)
            {
                messagectr = 0;
                messageOn = false;
            }
        }
    }
    //PLAYER LIFE
    public void drawPlayerLife(){

        int x = gp.tileSize/2;
        int y = gp.tileSize/2;
        int i = 0;

        // draw max life
        while(i < gp.player.maxLife/2)
        {
            g2.drawImage(hblank, x, y, null);
            i++;
            x += gp.tileSize;
        }
        //RESET
        x = gp.tileSize/2;
        y = gp.tileSize/2;
        i = 0;
        // draw current life
        while(i < gp.player.life)
        {
            g2.drawImage(hhalf, x,y,null);
            i++;
            if(i < gp.player.life)
            {
                g2.drawImage(hfull, x, y ,null);
            }
            i++;
            x += gp.tileSize;
        }
    }

    public void drawTitleScreen(){
        if(titleScreenState == 0){
            g2.setColor(new Color(70, 120 , 80));
            g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
            //NAME
            g2.setFont(g2.getFont().deriveFont(Font.BOLD,96F));
            String text = "GAME TITLE";
            int x = getXtoCenter(text);
            int y = gp.tileSize*3;

            //SHADOW
            g2.setColor(Color.black);
            g2.drawString(text, x+5, y+5);
            //MAIN COLOR
            g2.setColor(Color.white);
            g2.drawString(text, x, y);

            //CAT IMAGE
            x = gp.screenWidth/2 - gp.tileSize*2/2;
            y += gp.tileSize*2;
            g2.drawImage(gp.player.down1, x, y, gp.tileSize*2, gp.tileSize*2, null);

            //MENU
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 48F));

            text = "NEW GAME";
            x = getXtoCenter(text);
            y += gp.tileSize*4;
            g2.drawString(text, x, y);
            if(commandNum==0){
                g2.drawString(">", x-gp.tileSize, y);
            }

            text = "LOAD GAME";
            x = getXtoCenter(text);
            y += gp.tileSize;
            g2.drawString(text, x, y);
            if(commandNum==1){
                g2.drawString(">", x-gp.tileSize, y);
            }

            text = "QUIT";
            x = getXtoCenter(text);
            y += gp.tileSize;
            g2.drawString(text, x, y);
            if(commandNum==2){
                g2.drawString(">", x-gp.tileSize, y);
            }
        }
        else if(titleScreenState == 1){
            //CAT SELECTION
            g2.setColor(Color.WHITE);
            g2.setFont(g2.getFont().deriveFont(42F));

            String text = "Select your Cat!";
            int x = getXtoCenter(text);
            int y = gp.tileSize*3;
            g2.drawString(text, x, y);

            text = "CAT1";
            x = gp.tileSize*4;
            y = gp.tileSize*9;
            g2.drawString(text, x, y);
            g2.drawImage(gp.player.tab, x - gp.tileSize, y-gp.tileSize*5, gp.tileSize*4, gp.tileSize*4, null);
            if(commandNum == 0){
                g2.drawString(">", x-gp.tileSize, y);
            }

            text = "CAT2";
            x += gp.tileSize*6;
            //y = gp.tileSize*9;
            g2.drawImage(gp.player.white, x - gp.tileSize, y-gp.tileSize*5, gp.tileSize*4, gp.tileSize*4, null);
            g2.drawString(text, x, y);
            if(commandNum == 1){
                g2.drawString(">", x-gp.tileSize, y);
            }

            text = "BACK";
            x = getXtoCenter(text);
            y += gp.tileSize*2;
            g2.drawString(text, x, y);
            if(commandNum == 2){
                g2.drawString(">", x-gp.tileSize, y);
            }
        }

    }
    public void drawPauseScreen(){

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 80F));
        String text = "PAUSED";
        int x = getXtoCenter(text);
        int y = gp.screenHeight/2;

        g2.drawString(text, x, y);
        //commandNum = 0;

        g2.setColor(Color.WHITE);
        g2.setFont(g2.getFont().deriveFont(42F));
        //CONTINUE GAME
        text = "CONTINUE";
        x = getXtoCenter(text);
        y = gp.tileSize*9;
        g2.drawString(text, x, y);
        if(commandNum == 0){
            g2.drawString(">", x-gp.tileSize, y);
        }
        //TITLE SCREEN
        text = "TITLE SCREEN";
        x = getXtoCenter(text);
        y += gp.tileSize;
        g2.drawString(text, x, y);
        if(commandNum == 1){
            g2.drawString(">", x-gp.tileSize, y);
        }

        //QUIT GAME
        text = "EXIT GAME";
        x = getXtoCenter(text);
        y += gp.tileSize;
        g2.drawString(text, x, y);
        if(commandNum == 2){
            g2.drawString(">", x-gp.tileSize, y);
        }
    }

    public void drawOverScreen(){

        g2.setColor(new Color(0, 0 , 0));
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
        //NAME
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,96F));
        String text = "GAME OVER";
        int x = getXtoCenter(text);
        int y = gp.tileSize*3;

        //SHADOW
        g2.setColor(Color.black);
        g2.drawString(text, x+5, y+5);
        //MAIN COLOR
        g2.setColor(Color.white);
        g2.drawString(text, x, y);

        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 36F));
        text = "YOU'VE BEEN CAUGHT!";
        x = getXtoCenter(text);
        y += gp.tileSize;
        g2.drawString(text, x, y);

        //MENU
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 48F));

        text = "RETRY";
        x = getXtoCenter(text);
        y += gp.tileSize*4;
        g2.drawString(text, x, y);
        if(commandNum==0){
            g2.drawString(">", x-gp.tileSize, y);
        }

        text = "QUIT GAME";
        x = getXtoCenter(text);
        y += gp.tileSize;
        g2.drawString(text, x, y);
        if(commandNum==1){
            g2.drawString(">", x-gp.tileSize, y);
        }
    }

    public void drawDialogueScreen() {
        //WINDOW
        int x = gp.tileSize*2;
        int y = gp.tileSize/2;
        int width = gp.screenWidth - (gp.tileSize*4);
        int height = gp.tileSize*4;
        drawSubWindow(x, y, width, height);

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 28));
        x += gp.tileSize;
        y += gp.tileSize;

        for(String line : currentDialogue.split("\n")) {
            g2.drawString(line, x, y);
            y += 40;
        }
    }
    public void drawSubWindow(int x, int y, int width, int height) {
        Color c = new Color(0, 0, 0, 140);
        g2.setColor(c);
        g2.fillRoundRect(x, y, width, height, 35, 35);

        c = new Color(255, 255, 0);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x+5, y+5, -10, -10, 25, 25);
    }
    public int getXtoCenter(String text){
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.screenWidth/2 - length/2;
        return x;
    }
}

