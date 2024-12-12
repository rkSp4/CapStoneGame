package Main;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;
//import Object.SuperObject;
import Entity.Entity;
import Object.OBJ_KEY;
import Object.OBJ_TIME;
import Object.OBJ_HEART;
import Object.OBJ_PAW;

public class UI {

    GamePanel gp;
    Graphics2D g2;
    BufferedImage hfull,hhalf,hblank;
    BufferedImage keyI;
    BufferedImage timeI;
    BufferedImage pawI;
    int messagectr = 0;

    Font arial_40, arial_80B;
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    public boolean gameFinished = false;
    public String currentDialogue = "";
    public int commandNum = 0;
    public int titleScreenState = 0;
    public int substate = 0;
    public boolean newGame = true;

   double playTime;
   // DecimalFormat dFormat = new DecimalFormat("#0.00");

    public UI(GamePanel gp) {
        this.gp = gp;

        arial_40 = new Font("Arial", Font.PLAIN, 40);
        arial_80B = new Font("Arial", Font.BOLD, 80);
        OBJ_KEY key = new OBJ_KEY(gp);
        OBJ_TIME time = new OBJ_TIME(gp);
        OBJ_PAW paw = new OBJ_PAW(gp);
        timeI = time.image;
        keyI = key.image;
        pawI = paw.image;

        //CREATE HUB OBJECT
        Entity heart = new OBJ_HEART(gp);
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
//            g2.setColor(Color.black);
//            g2.fillRoundRect(gp.tileSize * 11, 18, gp.tileSize * 4, 63,35, 35);
            drawSubWindow(gp.tileSize*15, 18, gp.tileSize*4, 63);
            g2.setColor(Color.white);
            g2.drawString(":"+dFormat.format(playTime), gp.tileSize*16, 65);
            g2.drawImage(timeI, gp.tileSize*15 + 7, gp.tileSize/2, gp.tileSize, gp.tileSize, null);

            drawPlayerLife();
            drawClaw();
            drawSprint();
        }//PAUSE STATE
        if(gp.gameState == gp.pauseState){
            drawPauseScreen();

        }
        if(gp.gameState == gp.overState){
            gp.stopMusic();
            //implement game over music
            drawOverScreen();
            gp.keyH.enterPressed = false;
        }

        //DIALOGUE
        if(gp.gameState == gp.dialogueState) {
            drawPlayerLife();
            drawClaw();
            drawSprint();
            drawDialogueScreen();
        }

        //OPTIONS
        if(gp.gameState == gp.optionState){
            drawOptionsScreen();
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

    //ABILITIES
    public void drawClaw(){
        g2.setColor(Color.black);
        g2.drawRect(gp.tileSize/2, gp.tileSize/2+ 120, gp.tileSize+5, gp.tileSize+5);
        g2.setColor(Color.white);
        g2.fillRect(gp.tileSize/2, gp.tileSize/2 + 120, gp.tileSize+6, gp.tileSize+6);
        g2.drawImage(keyI, gp.tileSize/2, gp.tileSize/2 + 120, gp.tileSize, gp.tileSize, null);
        if(gp.player.clawCD){
            drawAbilityWindow(gp.tileSize/2, gp.tileSize/2 + 120, gp.tileSize+6, gp.tileSize+6);
        }

    }
    public void drawSprint(){
        g2.setColor(Color.black);
        g2.drawRect(gp.tileSize*2-10, gp.tileSize/2 + 120, gp.tileSize+5, gp.tileSize+5);
        g2.setColor(Color.white);
        g2.fillRect(gp.tileSize*2-10, gp.tileSize/2 + 120, gp.tileSize+6, gp.tileSize+6);
        g2.drawImage(pawI, gp.tileSize*2-7, gp.tileSize/2 + 123, gp.tileSize, gp.tileSize, null);
        if(gp.player.sprintCD){
            drawAbilityWindow(gp.tileSize*2-10, gp.tileSize/2 + 120, gp.tileSize+6, gp.tileSize+6);
        }
    }

    public void drawTitleScreen(){
        if(titleScreenState == 0){
            g2.setColor(new Color(70, 120 , 80));
            g2.fillRect(0, 0, gp.ScreenWidth2, gp.ScreenHeight2);
            //NAME
            g2.setFont(new Font("ROG Fonts", Font.BOLD, 69)); //ROG Fonts,
            String text = "Cat Collection";
            int x = getXtoCenter(text);
            int y = gp.tileSize*3;

            //SHADOW
            g2.setColor(Color.black);
            g2.drawString(text, x+5, y+5);
            //MAIN COLOR
            g2.setColor(Color.white);
            g2.drawString(text, x, y);

            //CAT IMAGE
            x = gp.screenWidth/2 - gp.tileSize*2;
            y += gp.tileSize;
            g2.drawImage(gp.player.down1, x, y, gp.tileSize*4, gp.tileSize*4, null);

            //MENU
            g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 48F));

            if(newGame) {
                text = "NEW GAME";
            }else{
                text = "LOAD GAME";
            }
            x = getXtoCenter(text);
            y += gp.tileSize*6;
            g2.drawString(text, x, y);
            if(commandNum==0){
                g2.drawString(">", x-gp.tileSize, y);
                if(gp.keyH.enterPressed){
                    gp.ui.titleScreenState = 1;
                    newGame = false;
                }
                gp.keyH.enterPressed = false;
            }

            text = "QUIT";
            x = getXtoCenter(text);
            y += gp.tileSize;
            g2.drawString(text, x, y);
            if(commandNum==1){
                g2.drawString(">", x-gp.tileSize, y);
                if(gp.keyH.enterPressed){
                    System.exit(0);
                }
            }
        }
        else if(titleScreenState == 1){
            g2.setColor(new Color(70, 120 , 80));
            g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

            //CAT SELECTION
            g2.setColor(Color.WHITE);
            g2.setFont(g2.getFont().deriveFont(42F));
            String text = "Select your Cat!";
            int x = getXtoCenter(text);
            int y = gp.tileSize*3;
            g2.drawString(text, x, y);

            text = "CAT1";
            x = gp.tileSize*5;
            y = gp.tileSize*9;
            g2.drawString(text, x, y);
            g2.drawImage(gp.player.tab, x - gp.tileSize, y-gp.tileSize*5, gp.tileSize*4, gp.tileSize*4, null);
            if(commandNum == 0){
                g2.drawString(">", x-gp.tileSize, y);
                if(gp.keyH.enterPressed){
                    //gp.player[0];
                    gp.gameState = gp.playState;
                    commandNum=0;
                }
                gp.keyH.enterPressed = false;
            }

            text = "CAT2";
            x += gp.tileSize*8;
            //y = gp.tileSize*9;
            g2.drawImage(gp.player.white, x - gp.tileSize, y-gp.tileSize*5, gp.tileSize*4, gp.tileSize*4, null);
            g2.drawString(text, x, y);
            if(commandNum == 1){
                g2.drawString(">", x-gp.tileSize, y);
                if(gp.keyH.enterPressed){
                    //gp.player[1];
                    gp.gameState = gp.playState;
                    commandNum=0;
                }
                gp.keyH.enterPressed = false;
            }

            text = "BACK";
            x = getXtoCenter(text);
            y += gp.tileSize*2;
            g2.drawString(text, x, y);
            if(commandNum == 2){
                g2.drawString(">", x-gp.tileSize, y);
                if(gp.keyH.enterPressed){
                    gp.ui.titleScreenState = 0;
                }
                gp.keyH.enterPressed = false;

            }
        }

    }
    public void drawPauseScreen(){

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 80F));
        String text = "PAUSED";
        int x = getXtoCenter(text);
        int y = gp.screenHeight/2;

        g2.drawString(text, x, y);

        g2.setColor(Color.WHITE);
        g2.setFont(g2.getFont().deriveFont(42F));

        //CONTINUE GAME
        text = "CONTINUE";
        x = getXtoCenter(text);
        y = gp.tileSize*9;
        g2.drawString(text, x, y);
        if(commandNum == 0){
            g2.drawString(">", x-gp.tileSize, y);
            if (gp.keyH.enterPressed) {
                gp.gameState = gp.playState;
                gp.playMusic(0);
                commandNum=0;
            }
            gp.keyH.enterPressed = false;
        }

        //RESTART
        text = "RESTART";
        x = getXtoCenter(text);
        y += gp.tileSize;
        g2.drawString(text, x, y);
        if(commandNum == 1){
            g2.drawString(">", x-gp.tileSize, y);
            if (gp.keyH.enterPressed) {
                gp.player.setDefaultValues();
                gp.setUpGame();
                playTime = 0;
                gp.gameState = gp.playState;
                gp.playMusic(0);
                commandNum=0;
            }
            gp.keyH.enterPressed = false;
        }

        //TITLE SCREEN
        text = "TITLE SCREEN";
        x = getXtoCenter(text);
        y += gp.tileSize;
        g2.drawString(text, x, y);
        if(commandNum == 2){
            g2.drawString(">", x-gp.tileSize, y);
            if (gp.keyH.enterPressed) {
                gp.gameState = gp.titleState;
                gp.ui.titleScreenState = 0;
                gp.playMusic(0);
                commandNum=0;
            }
            gp.keyH.enterPressed = false;
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
            if (gp.keyH.enterPressed) {
                gp.player.setDefaultValues();
                gp.setUpGame();
                playTime = 0;
                gp.gameState = gp.playState;
                gp.playMusic(0);
                commandNum=0;
            }
            gp.keyH.enterPressed = false;
        }

        text = "QUIT GAME";
        x = getXtoCenter(text);
        y += gp.tileSize;
        g2.drawString(text, x, y);
        if(commandNum==1){
            g2.drawString(">", x-gp.tileSize, y);
            if (gp.keyH.enterPressed) {
                System.exit(0);
            }
        }
    }

    public void drawDialogueScreen() {
        //WINDOW
        int x = gp.tileSize*2;
        int y = gp.tileSize/2;
        int width = gp.screenWidth - (gp.tileSize*4);
        int height = gp.tileSize*4;
        drawSubWindow(x, y, width, height);

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 42));
        x += gp.tileSize;
        y += gp.tileSize;

        for(String line : currentDialogue.split("\n")) {
            g2.drawString(line, getXtoCenter(line), y+gp.tileSize);
            y += 40;
        }
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 21));
        String str = "Press ENTER to continue";
        g2.drawString(str, getXtoCenter(str), y+gp.tileSize*2);
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

    public void drawAbilityWindow(int x, int y, int width, int height) {
        Color c = new Color(54, 46, 46, 238);
        g2.setColor(c);
        g2.fillRect(x, y, width, height);
    }

    public void drawOptionsScreen(){
        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(32F));
        //SUB WINDOW
        int frameX = gp.tileSize*6;
        int frameY = gp.tileSize;
        int frameWidth = gp.tileSize*8;
        int frameHeight = gp.tileSize*8;
        drawSubWindow(frameX, frameY, frameWidth, frameHeight);

        //gp.keyH.enterPressed = false;

        switch(substate){
            case 0:
                optionsTop(frameX, frameY);
                break;
            case 1:
                options_FullScreenNotif(frameX, frameY);
                break;
            case 2:
                break;
        }
    }

    public void optionsTop(int frameX, int frameY){
        int textX;
        int textY;
        g2.setFont(g2.getFont().deriveFont(29F));
        //TITLE
        String text = "OPTIONS";
        textX = getXtoCenter(text);
        textY = frameY + gp.tileSize;
        g2.drawString(text, textX, textY);

        //FULLSCREEN ON/OFF
        textX = frameX + gp.tileSize;
        textY +=gp.tileSize*2;
        g2.drawString("Full Screen", textX, textY);
        if(commandNum==0){
            g2.drawString(">", textX-25, textY);
            if(gp.keyH.enterPressed){
                if(!gp.fullScreenOn){
                    gp.fullScreenOn = true;
                }else if(gp.fullScreenOn){
                    gp.fullScreenOn = false;
                }
                substate=1;
                commandNum=0;
                gp.keyH.enterPressed = false;
            }
        }

        //MUSIC
        textY +=gp.tileSize;
        g2.drawString("Music", textX, textY);
        if(commandNum==1){
            g2.drawString(">", textX-25, textY);
        }

        //SE
        textY +=gp.tileSize;
        g2.drawString("Sound FX", textX, textY);
        if(commandNum==2){
            g2.drawString(">", textX-25, textY);
        }

        //BACK
        textY +=gp.tileSize*2;
        g2.drawString("Back", textX, textY);
        if(commandNum==3){
            g2.drawString(">", textX-25, textY);
            if(gp.keyH.enterPressed){
                gp.gameState = gp.playState;
                commandNum=0;
            }
            gp.keyH.enterPressed = false;
        }

        //FULLSCREEN BOX
        textX = frameX + gp.tileSize*5;
        textY = frameY + gp.tileSize*2 + 26;
        g2.setStroke(new BasicStroke(3));
        g2.drawRect(textX, textY, 24, 24);
        if(gp.fullScreenOn){
            g2.fillRect(textX, textY, 24, 24);
        }

        //MUSIC VOLUME
        textY += gp.tileSize;
        g2.drawRect(textX, textY, 120, 24);//width divide by volScale
        int volumeWidth = 24*gp.music.volumeScale;      //width divide by volScale
        g2.fillRect(textX, textY, volumeWidth, 24);

        //SFX VOLUME
        textY += gp.tileSize;
        g2.drawRect(textX, textY, 120, 24);
        volumeWidth = 24*gp.se.volumeScale;
        g2.fillRect(textX, textY, volumeWidth, 24);

        gp.config.saveConfig();
    }

    public void options_FullScreenNotif(int frameX, int frameY){
        int textX = frameX + gp.tileSize;
        int textY = frameY + gp.tileSize*3;

        currentDialogue = "The change will take \neffect after restarting \nthe game.";

        for(String line: currentDialogue.split("\n")){
            g2.drawString(line, textX, textY);
            textY+=40;
        }

        //BACK
        textY +=gp.tileSize*2;
        g2.drawString("Back", textX, textY);
        if(commandNum==0){
            g2.drawString(">", textX-25, textY);
            if(gp.keyH.enterPressed){
                substate=0;
            }
            gp.keyH.enterPressed=false;
        }

    }
    public int getXtoCenter(String text){
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        return gp.screenWidth/2 - length/2;
    }
}

