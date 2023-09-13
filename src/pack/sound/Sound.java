package pack.sound;

import javax.sound.sampled.*;
import java.io.*;

/**
 * this class represents an audio which is playable
 */
public class Sound {

    private Clip clip;

    public Sound(String name, boolean loop ,int count) {
        try {
            clip = AudioSystem.getClip();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
        playSound(name, loop,count);
    }

    private void playSound(String name, boolean loop,int count) {
        try {
            File file =new File(name);
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(file);
            clip.open(audioIn);
            clip.start();
            if (loop)
                clip.loop(count);
        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
            e.printStackTrace();
        }
    }

    public Clip getClip() {
        return clip;
    }
}
