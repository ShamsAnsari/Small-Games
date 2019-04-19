package breakout;
import javax.swing.JFrame;
import javax.swing.WindowConstants;


// TODO: Auto-generated Javadoc
/**
 * The Class Main.
 *
 * @author Shams
 */
public class Main
{

    /**
     * The main method.
     *
     * @param args the arguments
     */
    public static void main( String[] args )
    {
        JFrame obj = new JFrame();
        Gameplay gamePlay = new Gameplay();
        obj.setBounds( 356, 147, 700, 600 );
        obj.setTitle( "Breakout Ball" );
        obj.setResizable( false );
        obj.setDefaultCloseOperation( WindowConstants.EXIT_ON_CLOSE );
        obj.add( gamePlay );
        obj.setVisible( true );
        initMusic();
    }


    /**
     * Inits the music.
     */
    public static void initMusic()
    {
        try
        {
            SimpleAudioPlayer audioPlayer = new SimpleAudioPlayer();
            audioPlayer.play();

            while ( true )
            {

            }
        }

        catch ( Exception ex )
        {
            System.out.println( "Error with playing sound." );
            ex.printStackTrace();

        }
    }

}
