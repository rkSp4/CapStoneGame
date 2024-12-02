package Main;
import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class sound {
    Clip clip;
    URL soundURL[] = new URL[30];
    public sound(){

        soundURL[0] = getClass().getResource("/sound/bkgMusic.wav");
    }
    public void setFile(int i){
        try{
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);
        }catch(Exception e){
        }
    }
    public void play(){
        clip.start();
    }
    public void loop(){
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stop(){
        clip.stop();
    }
    public void playMusic(int i){
        this.setFile(i);
        this.play();
        this.loop();
    }
    public void stopMusic(){
        this.stop();
    }
    public void playSE(int i){
        this.setFile(i);
        this.play();
    }
}