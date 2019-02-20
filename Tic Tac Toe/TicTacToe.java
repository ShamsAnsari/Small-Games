import java.util.Arrays;
import java.util.Scanner;


/**
 * Tic Tac Toe Game, Player-Player.
 * 
 * @version 2/18/2019
 * @author Shams Ansari
 */
public class TicTacToe
{
    // ASK FULK, How to access a method in a static way?
    public static void main( String[] args )
    {
        Scanner input = new Scanner( System.in );
        Player p1 = new Player( null, 'X' );
        Player p2 = new Player( null, 'O' );
        Machine mac = new Machine();
        char[][] board = new char[3][3];

        for ( char[] ch : board )
        {
            Arrays.fill( ch, ' ' );
        }
        // mac.drawBoard( board );
        mac.drawBoardNum();

        System.out.print( "Enter Player1's name(X): " );
        p1.setName( input.next() );
        System.out.print( "Enter player2's name(O): " );
        p2.setName( input.next() );

        do
        {
            System.out.println( "Your move, " + p1.getName() );
            if ( !mac.getInput( board, p1 ) )
            {
                System.err.println( "***ERROR***" );
            }
            if ( mac.checkWinner( board ) )
            {
                System.out.println( "YOU WIN, " + p1.getName() );
                mac.drawBoard( board );
                System.exit( 1 );
            }
            mac.drawBoard( board );
            System.out.println( "=========================" );

            System.out.println( "Your move, " + p2.getName() );
            if ( !mac.getInput( board, p2 ) )
            {
                System.err.println( "***ERROR***" );
            }
            if ( mac.checkWinner( board ) )
            {
                System.out.println( "YOU WIN, " + p2.getName() );
                mac.drawBoard( board );
                System.exit( 1 );
            }
            mac.drawBoard( board );
            System.out.println( "=========================" );

        } while ( !mac.isEmpty( board ) );
        input.close();
    }

}
