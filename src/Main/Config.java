package Main;

import java.io.*;

public class Config {
    GamePanel gp;

    public Config(GamePanel gp){
         this.gp = gp;
    }

    public void saveConfig() {

        try{
            BufferedWriter bw = new BufferedWriter(new FileWriter("config.txt"));

            //FULLSCREEN
            if(gp.fullScreenOn){
                bw.write("ON");
            }
            if(!gp.fullScreenOn){
                bw.write("OFF");
            }
            bw.newLine();

            //MUSIC VOLUME
            bw.write(String.valueOf(gp.music.volumeScale));
            bw.newLine();

            //SE VOLUME
            bw.write(String.valueOf(gp.se.volumeScale));
            bw.newLine();

            bw.close();

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public void loadConfig(){

        try{
            BufferedReader br = new BufferedReader(new FileReader("config.txt"));
            String s = br.readLine();

            //FULLSCREEN
            if(s.equals("ON")){
                gp.fullScreenOn = true;
            }
            if(s.equals("OFF")){
                gp.fullScreenOn = false;
            }

            //MUSIC VOLUME
            s = br.readLine();
            gp.music.volumeScale = Integer.parseInt(s);

            //SE VOLUME
            s = br.readLine();
            gp.se.volumeScale = Integer.parseInt(s);

            br.close();

        }catch(Exception e){
            e.printStackTrace();
        }

    }
}
