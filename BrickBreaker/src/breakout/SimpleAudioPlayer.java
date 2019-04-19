package breakout;

import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;


// TODO: Auto-generated Javadoc
/**
 * The Class SimpleAudioPlayer.
 *
 * @author Shams
 */
public class SimpleAudioPlayer
{
    
    /** The file path. */
    static String filePath;

    /** The current frame. */
    Long currentFrame;

    /** The clip. */
    Clip clip;

    /** The status. */
    String status;

    /** The audio input stream. */
    AudioInputStream audioInputStream;


    /**
     * Instantiates a new simple audio player.
     *
     * @throws UnsupportedAudioFileException the unsupported audio file exception
     * @throws IOException Signals that an I/O exception has occurred.
     * @throws LineUnavailableException the line unavailable exception
     */
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


    /**
     * Play.
     */
    public void play()
    {
        clip.start();

    }

}