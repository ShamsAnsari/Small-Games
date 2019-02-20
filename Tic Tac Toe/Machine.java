import java.util.Arrays.*;
import java.util.Scanner;


public class Machine
{
    /**
     * Draws Tic Tac toe board
     * 
     * @param board
     *            A 2d array of type char, which has to be initialized
     */
    public void drawBoard( char[][] board )
    {
        System.out.println( "" );

        for ( byte r = 1; r <= 9; r++ )
        {
            if ( ( r == 3 || r == 6 ) )
            {
                System.out.println( "_______|_______|_______" );
            }
            else if ( r == 1 || r == 4 || r == 7 || r == 9 )
            {
                System.out.println( "       |       |       " );
            }
            else if ( r == 2 )
            {
                System.out.println( "   " + board[0][0] + "   |   " + board[0][1] + "   |   "
                    + board[0][2] + "   " );
            }
            else if ( r == 5 )
            {
                System.out.println( "   " + board[1][0] + "   |   " + board[1][1] + "   |   "
                    + board[1][2] + "   " );
            }
            else if ( r == 8 )
            {
                System.out.println( "   " + board[2][0] + "   |   " + board[2][1] + "   |   "
                    + board[2][2] + "   " );
            }

        }
        System.out.println( "" );

        // System.out.println(" ......|.......|...... ");//1
        // System.out.println(" ......|...... |...... ");//2
        // System.out.println("_______|_______|_______");//3
        // System.out.println(" ......| ......| ......");//4
        // System.out.println(" ......| ......| ......");//5
        // System.out.println("_______|_______|_______");//6
        // System.out.println(" ......| ......| ......");//7
        // System.out.println(" ......|...... |...... ");//8
        // System.out.println(" ......|...... |...... ");//9
        // 1234567 1234567 1234567
        // [0][0] [0][1] [0][2]
        // [1][0] [1][1] [1][2]
        // [2][0] [2][1] [2][2]
    }


    /**
     * Draws a numbered (1-9) TTT board
     */
    public void drawBoardNum()
    {
        System.out.println( "" );
        char[][] board = { { '1', '2', '3' }, { '4', '5', '6' }, { '7', '8', '9' } };
        drawBoard( board );

    }


    /**
     * Gets the input for which section the user wants to enter a X or O
     * 
     * @param board
     *            The 3X3 board of Xs and Os
     * @param player
     *            The Player who is entering the number
     * @return if the number X or O is sucessfully entered in the board, return
     *         true. Else return false.
     */
    public boolean getInput( char[][] board, Player player )
    {
        Scanner input = new Scanner( System.in );
        System.out.print( "Enter a number " + player.getName() + " (" + player.getType() + "): " );
        byte num = input.nextByte();
        switch ( num )
        {
            case 1:
                checkBoard( board, 0, 0, player );
                break;
            case 2:
                checkBoard( board, 0, 1, player );
                break;
            case 3:
                checkBoard( board, 0, 2, player );
                break;
            case 4:
                checkBoard( board, 1, 0, player );
                break;
            case 5:
                checkBoard( board, 1, 1, player );
                break;
            case 6:
                checkBoard( board, 1, 2, player );
                break;
            case 7:
                checkBoard( board, 2, 0, player );
                break;
            case 8:
                checkBoard( board, 2, 1, player );
                break;
            case 9:
                checkBoard( board, 2, 2, player );
                break;
            default:
                return false;
        }
        return true;
    }


    /**
     * 
     * Checks if board is filled ' '
     * 
     * @param board
     *            a 2d array
     * @return true if it is empty, false if not
     */
    public boolean isEmpty( char[][] board )
    {
        for ( char[] r : board )
        {
            for ( char c : r )
            {
                if ( !( c == ' ' ) )
                {
                    return false;
                }
            }
        }
        return true;
    }


    /**
     * 
     * Checks the TTT board to see if there is a winner
     * 
     * @param board
     *            A 2d array of Xs and Os
     * @return true if there is a winner, false otherwise
     */
    public boolean checkWinner( char[][] board )
    {
        for ( int i = 0; i < 3; i++ )
        {
            if ( checkWinnerHelperHorizontal( board, i ) )
            {
                return true;
            }

            if ( checkWinnerHelperVertical( board, i ) )
            {
                return true;
            }

        }
        if ( ( board[0][0] == board[1][1] ) && ( board[0][0] == board[2][2] )
            && ( board[0][0] == 'X' || board[0][0] == 'O' ) )
        {
            return true;
        }
        if ( ( board[0][2] == board[1][1] ) && ( board[0][2] == board[2][0] )
            && ( board[0][2] == 'X' || board[0][2] == 'O' ) )
        {
            return true;
        }
        return false;
    }


    // =========================================================================
    // *************************PRIVATE HELPER METHODS**************************
    // =========================================================================

    /**
     * Private helper method for getInput method, simplifies the checkBoard
     * process
     * 
     * @param board
     *            The 2d Array of chars holding X and O
     * @param n1
     *            array row index
     * @param n2
     *            array column index
     * @param player
     *            The player who has played
     * @return true if the spot is empty and a type has been filled in, false
     *         otherwise
     */
    private static boolean checkBoard( char[][] board, int n1, int n2, Player player )
    {
        if ( board[n1][n2] != ' ' )
        {
            return false;
        }
        board[n1][n2] = player.getType();
        return true;
    }


    private boolean checkWinnerHelperHorizontal( char[][] board, int r )
    {
        return ( board[r][0] == board[r][1] ) && ( board[r][0] == board[r][2] )
            && ( board[r][0] == 'X' || board[r][0] == 'O' );
    }


    private boolean checkWinnerHelperVertical( char[][] board, int c )
    {
        return ( board[0][c] == board[1][c] ) && ( board[0][c] == board[2][c] )
            && ( board[0][c] == 'X' || board[0][c] == 'O' );
    }
}
