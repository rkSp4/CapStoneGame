package Main;
import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class sound {
    Clip clip;
    URL[] soundURL = new URL[30];
    FloatControl fc;
    int volumeScale = 2;
    float volume;
    public sound(){

        soundURL[0] = getClass().getResource("/sound/village.wav");
        soundURL[1] = getClass().getResource("/sound/coin.wav");
        soundURL[2] = getClass().getResource("/sound/powerup.wav");
        soundURL[3] = getClass().getResource("/sound/unlock.wav");
        soundURL[4] = getClass().getResource("/sound/fanfare.wav");
        soundURL[5] = getClass().getResource("/sound/meow.wav");

        //this is for the sound effect for when to teleport to other map or dungeons
        //soundURL[4] = getClass().getResource("/sound/fanfare.wav");

    }
    public void setFile(int i){
        try{
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);
            fc = (FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN);
            checkVolume();
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
    public void checkVolume(){
        switch(volumeScale){
            case 0: volume = -60f;
                break;
            case 1: volume = -15f;
                break;
            case 2: volume = -8f;
                break;
            case 3: volume = -5f;
                break;
            case 4: volume = 1f;
                break;
            case 5: volume = 4f;
                break;
        }
        fc.setValue(volume);
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
