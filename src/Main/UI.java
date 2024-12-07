package Main;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;
import Object.OBJ_KEY;

public class UI {

    GamePanel gp;
    Graphics2D g2;
    BufferedImage keyI;
    int messagectr = 0;

    Font arial_40, arial_80B;
    // BufferedImage keyImage;
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    public boolean gameFinished = false;
    public int commandNum = 0;
    public int titleScreenState = 0;

    double playTime;
    DecimalFormat dFormat = new DecimalFormat("#0.00");

    public UI(GamePanel gp) {
        this.gp = gp;

        arial_40 = new Font("Arial", Font.PLAIN, 40);
        arial_80B = new Font("Arial", Font.BOLD, 80);
        OBJ_KEY key = new OBJ_KEY();
        keyI = key.image;

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
            g2.drawImage(keyI, gp.tileSize/2, gp.tileSize/2, gp.tileSize, gp.tileSize, null);
            g2.drawString("x " + gp.player.haskey, 74, 65);
        }//PAUSE STATE
        if(gp.gameState == gp.pauseState){
            drawPauseScreen();
        }
        if(gp.gameState == gp.overState){
            drawOverScreen();
        }

        if(messageOn == true)
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
        gp.stopMusic();
        //gp.playMusic(n); game over music
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
    public int getXtoCenter(String text){
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.screenWidth/2 - length/2;
        return x;
    }
}

