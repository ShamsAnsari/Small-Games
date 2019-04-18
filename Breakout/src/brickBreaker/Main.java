package brickBreaker;

import java.util.Scanner;

import javax.swing.JFrame;


public class Main
{

    public static void main( String[] args )
    {
        JFrame obj = new JFrame();
        Gameplay gamePlay = new Gameplay();

        obj.setBounds( 356, 147, 700, 600 );
        obj.setTitle( "Breakout Ball" );
        obj.setResizable( false );
        obj.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        obj.add( gamePlay );

        obj.setVisible( true );
        
        try
        {
            String filePath = "/Users/Shams/Documents/GitHub/Small-Games/Breakout/music/NeonLights.wav";
            SimpleAudioPlayer audioPlayer = new SimpleAudioPlayer( filePath );

            audioPlayer.play();
            Scanner sc = new Scanner( System.in );

            while ( true )
            {

                System.out.println( "4. stop" );
                int c = sc.nextInt();
                if ( c == 4 )
                    break;
            }
            sc.close();
        }

        catch ( Exception ex )
        {
            System.out.println( "Error with playing sound." );
            ex.printStackTrace();

        }
    }

}
