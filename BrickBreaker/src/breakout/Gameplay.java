package breakout;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.Timer;


// TODO: Auto-generated Javadoc
/**
 * The Class Gameplay.
 *
 * @author Shams
 */
public class Gameplay extends JPanel implements KeyListener, ActionListener
{

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** The play. */
    private boolean play = false;

    /** The score. */
    private int score = 0;

    /** The total bricks. */
    private int totalBricks = 21;

    /** The timer. */
    private Timer timer;

    /** The delay. */
    private int delay = 8;

    /** The player X. */
    private int playerX = 310;

    /** The player y. */
    private final int PLAYER_Y = 550;

    /** The paddle width. */
    private final int PADDLE_WIDTH = 120;

    /** The paddle height. */
    private final int PADDLE_HEIGHT = 10;

    /** The ball pos X. */
    private int ballPosX = 120;

    /** The ball pos Y. */
    private int ballPosY = 350;

    /** The ball xdir. */
    private int ballXdir = -1;

    /** The ball ydir. */
    private int ballYdir = -2;

    /** The map. */
    private MapGenerator map;


    /**
     * Instantiates a new gameplay.
     */
    public Gameplay()
    {
        map = new MapGenerator( 3, 7 );
        addKeyListener( this );
        setFocusable( true );
        setFocusTraversalKeysEnabled( false );
        timer = new Timer( delay, this );
        timer.start();

    }


    /**
     * Paint.
     *
     * @param g the g
     */
    @Override
    public void paint( Graphics g )
    {

        // Background
        g.setColor( Color.ORANGE );
        g.fillRect( 0, 0, 700, 600 );

        // Borders
        g.setColor( Color.BLACK );
        g.fillRect( 0, 0, 10, 600 );
        g.fillRect( 0, 0, 700, 10 );
        g.fillRect( 690, 0, 10, 600 );

        // Bricks
        map.draw( (Graphics2D)g );

        // Score
        g.setColor( new Color( 128, 0, 0 ) );
        g.setFont( new Font( "Monospaced", Font.BOLD, 25 ) );
        g.drawString( "" + score, 650, 30 );

        // Paddle
        g.setColor( Color.PINK );
        g.fillRoundRect( playerX, PLAYER_Y, PADDLE_WIDTH, PADDLE_HEIGHT, 5, 5 );
        g.setColor( Color.BLACK );
        g.drawRoundRect( playerX, PLAYER_Y, PADDLE_WIDTH, PADDLE_HEIGHT, 5, 5 );

        // Ball
        g.setColor( Color.PINK );
        g.fillOval( ballPosX, ballPosY, 20, 20 );
        g.setColor( Color.BLACK );
        g.drawOval( ballPosX, ballPosY, 20, 20 );

        if ( totalBricks < 1 )
        {
            play = false;
            ballXdir = ballYdir = 0;
            g.setColor( Color.RED );
            g.setFont( new Font( "Monospaced", Font.BOLD, 33 ) );
            g.drawString( "You Won", 290, 300 );
            g.setColor( Color.WHITE );
            g.setFont( new Font( "Monospaced", Font.BOLD, 25 ) );
            g.drawString( "" + score, 350, 325 );
            g.setColor( Color.RED );
            g.setFont( new Font( " Monospaced", Font.BOLD, 20 ) );
            g.drawString( "Press Enter to Restart: ", 240, 350 );
            g.setFont( new Font( " Monospaced", Font.BOLD, 15 ) );
            g.setColor( Color.GRAY );
            g.drawString( "by Shams Ansari", 295, 375 );

        }
        if ( ballPosY > 570 )
        {
            play = false;
            ballXdir = ballYdir = 0;
            g.setColor( Color.RED );
            g.setFont( new Font( "Monospaced", Font.BOLD, 33 ) );
            g.drawString( "Game Over", 270, 300 );
            g.setColor( Color.WHITE );
            g.setFont( new Font( "Monospaced", Font.BOLD, 25 ) );
            g.drawString( "" + score, 350, 325 );
            g.setColor( Color.RED );
            g.setFont( new Font( " Monospaced", Font.BOLD, 20 ) );
            g.drawString( "Press Enter to Restart: ", 240, 350 );
            g.setFont( new Font( " Monospaced", Font.BOLD, 15 ) );
            g.setColor( Color.GRAY );
            g.drawString( "by Shams Ansari", 295, 375 );

        }

        g.dispose();

    }


    /**
     * Action performed.
     *
     * @param e the e
     */
    @Override
    public void actionPerformed( ActionEvent e )
    {
        timer.start();

        if ( play )
        {
            if ( new Rectangle( ballPosX, ballPosY, 20, 20 )
                .intersects( new Rectangle( playerX,
                    PLAYER_Y,
                    PADDLE_WIDTH,
                    PADDLE_HEIGHT ) ) )
            {
                ballYdir = -ballYdir;
            }

            A: for ( int row = 0; row < map.map.length; row++ )
            {
                for ( int col = 0; col < map.map[0].length; col++ )
                {
                    if ( map.map[row][col] > 0 )
                    {
                        int brickX = col * map.brickWidth + 80;
                        int brickY = row * map.brickHeight + 50;

                        Rectangle brickRect = new Rectangle( brickX,
                            brickY,
                            map.brickWidth,
                            map.brickHeight );
                        Rectangle ballRect = new Rectangle( ballPosX,
                            ballPosY,
                            20,
                            20 );

                        if ( ballRect.intersects( brickRect ) )
                        {
                            map.setBrickValue( 0, row, col );
                            totalBricks--;
                            score += 5;

                            // Ball has touched the brick from left or right
                            if ( ballPosX + 19 <= brickRect.x || ballPosX
                                + 1 >= brickRect.x + brickRect.width )
                            {
                                ballXdir = -ballXdir;
                            }
                            // Ball has touched the brick from up or down.
                            else
                            {
                                ballYdir = -ballYdir;
                            }
                            break A;

                        }
                    }

                }

            }

            ballPosX += ballXdir;
            ballPosY += ballYdir;

            // Left wall
            if ( ballPosX < 10 )
            {
                ballXdir = -ballXdir;
            }
            // Top wall
            if ( ballPosY < 10 )
            {
                ballYdir = -ballYdir;
            }
            // Right wall
            if ( ballPosX > 670 )
            {
                ballXdir = -ballXdir;
            }

        }
        repaint();
    }


    /**
     * Key pressed.
     *
     * @param e the e
     */
    @Override
    public void keyPressed( KeyEvent e )
    {
        if ( e.getKeyCode() == KeyEvent.VK_RIGHT )
        {
            if ( playerX >= 570 )
            {
                playerX = 570;

            }
            else
            {
                moveRight();
            }
        }

        if ( e.getKeyCode() == KeyEvent.VK_LEFT )
        {
            if ( playerX <= 10 )
            {
                playerX = 10;

            }
            else
            {
                moveLeft();
            }

        }

        if ( e.getKeyCode() == KeyEvent.VK_ENTER )
        {
            if ( !play )
            {
                play = true;
                ballPosX = 120;
                ballPosY = 350;
                ballXdir = -1;
                ballYdir = -2;
                playerX = 310;
                score = 0;
                totalBricks = 21;
                map = new MapGenerator( 3, 7 );

                repaint();
            }
        }

    }


    /**
     * Decrements the paddle by 20 pixels (X).
     */
    private void moveLeft()
    {
        play = true;
        playerX -= 20;

    }


    /**
     * Increments the paddle by 20 pixels (X).
     */
    private void moveRight()
    {
        play = true;
        playerX += 20;

    }


    /**
     * Key typed.
     *
     * @param e the e
     */
    @Override
    public void keyTyped( KeyEvent e )
    {
    }


    /**
     * Key released.
     *
     * @param e the e
     */
    @Override
    public void keyReleased( KeyEvent e )
    {
    }
}