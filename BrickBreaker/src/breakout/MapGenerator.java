package breakout;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Random;


// TODO: Auto-generated Javadoc
/**
 * The Class MapGenerator.
 */
public class MapGenerator
{
    
    /** The map. */
    public int map[][];

    /** The brick width. */
    public int brickWidth;

    /** The brick height. */
    public int brickHeight;


    /**
     * Instantiates a new map generator.
     *
     * @param row the row
     * @param col the col
     */
    public MapGenerator( int row, int col )
    {
        map = new int[row][col];

        for ( int r = 0; r < map.length; r++ )
        {
            for ( int c = 0; c < map[0].length; c++ )
            {
                map[r][c] = 1;

            }
        }

        brickWidth = 540 / col;
        brickHeight = 150 / row;

    }


    /**
     * Draw.
     *
     * @param g the g
     */
    public void draw( Graphics2D g )
    {

        for ( int row = 0; row < map.length; row++ )
        {
            for ( int col = 0; col < map[0].length; col++ )
            {
                if ( map[row][col] > 0 )
                {
                    Random rand = new Random();

                    float red = rand.nextFloat() / 2;
                    float green = rand.nextFloat();
                    float blue = rand.nextFloat();

                    g.setColor( new Color( red, green, blue ) );
                    g.fillRect( col * brickWidth + 80,
                        row * brickHeight + 50,
                        brickWidth,
                        brickHeight );

                    g.setStroke( new BasicStroke( 5 ) );
                    g.setColor( Color.BLACK );
                    g.drawRoundRect( col * brickWidth + 80,
                        row * brickHeight + 50,
                        brickWidth,
                        brickHeight,
                        12,
                        12 );

                }
            }

        }

    }


    /**
     * Sets the brick value.
     *
     * @param value the value
     * @param row the row
     * @param col the col
     */
    public void setBrickValue( int value, int row, int col )
    {
        map[row][col] = value;
    }

}
