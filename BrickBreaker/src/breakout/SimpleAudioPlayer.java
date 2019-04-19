package breakout;

import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;


public class SimpleAudioPlayer
{
    static String filePath;

    Long currentFrame;

    Clip clip;

    String status;

    AudioInputStream audioInputStream;


    public SimpleAudioPlayer()
        throws UnsupportedAudioFileException,
        IOException,
        LineUnavailableException
    {
        audioInputStream = AudioSystem.getAudioInputStream(
            Main.class.getResource( "/sound/NeonLights.wav" ) );
        clip = AudioSystem.getClip();
        clip.open( audioInputStream );
        clip.loop( Clip.LOOP_CONTINUOUSLY );
    }


    public void play()
    {
        clip.start();

    }

}