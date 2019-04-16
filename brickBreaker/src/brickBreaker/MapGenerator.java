package brickBreaker;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Random;


public class MapGenerator
{
    public int map[][];

    public int brickWidth;

    public int brickHeight;


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


    public void draw( Graphics2D g )
    {

        for ( int row = 0; row < map.length; row++ )
        {
            for ( int col = 0; col < map[0].length; col++ )
            {
                if ( map[row][col] > 0 )
                {
                    Random rand = new Random();

                    float red = rand.nextFloat();
                    float green = rand.nextFloat();
                    float blue = rand.nextFloat();

                    // g.setColor( new Color( red, green, blue ) );
                    g.setColor( Color.WHITE );
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


    public void setBrickValue( int value, int row, int col )
    {
        map[row][col] = value;
    }

}
