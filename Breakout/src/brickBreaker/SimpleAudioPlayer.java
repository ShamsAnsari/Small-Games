package brickBreaker;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

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


    public SimpleAudioPlayer( String filePath )
        throws UnsupportedAudioFileException,
        IOException,
        LineUnavailableException
    {
        audioInputStream = AudioSystem
            .getAudioInputStream( new File( filePath ).getAbsoluteFile() );
        clip = AudioSystem.getClip();
        clip.open( audioInputStream );
        clip.loop( Clip.LOOP_CONTINUOUSLY );
    }


    public void play()
    {
        clip.start();

    }


    public void stop()
    {
        clip.start();
    }

}