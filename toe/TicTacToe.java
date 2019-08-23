/*  
    Student Name: Karishma Kapur
    Student ID: 0558326
    Date: 2/25/19
    Project: Assignment 17
    Description: tic tac toe
 */
package assignment.pkg17.tic.tac.toe;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TicTacToe extends JFrame implements ActionListener, MouseListener 
{

    // program settings =====================
    //constant for the GUI app title
    private static final String FRAME_TITLE = "Tic-Tac-Toe Game";

    //constant for the GUI app frame size
    private static final int FRAME_WIDTH = 300;
    private static final int FRAME_HEIGHT = 300;

    //constants for gridLayout
    private static final int ROWS = 3;
    private static final int COLUMNS = 3;
    private static final int H_GAP = 3;
    private static final int V_GAP = 3;

    //array size constants
    private static final int ARRAY_ROWS = 3;
    private static final int ARRAY_COLUMNS = 3;

    //constants for labels
    private static final String RESTART = "Restart";
    private static final String WELCOME_MSSG = "Welcome to Tic-Tac-Toe";

    //constant for consistent use throughout program.
    private static final String BLANK_SPACE = " ";
    private static final String PLAYER_1 = "X";
    private static final String PLAYER_2 = "O";

    //constants for color
    private static final Color YELLOW_COLOR = Color.YELLOW;
    private static final Color BLACK_COLOR = Color.BLACK;
    private static final Color WHITE_COLOR = Color.WHITE;
    private static final Color BLUE_COLOR = Color.BLUE;

    //constants for font
    private static final Font HEL_BOLD_12 = new Font("Helvetica", Font.BOLD, 12);
    private static final Font HEL_BOLD_32 = new Font("Helvetica", Font.BOLD, 32);
    
    //constants for my loops, putting it here because they are used multiple times in different functions
    private static final int START_LOOP = 0;
    private static final int END_LOOP = ARRAY_COLUMNS;
   

    // instance variables
    private static JLabel nameLabel;
    private static JLabel[][] grid;
    private static char[][] game;
    private static JButton restart;
    private static JPanel panel;
    private static JLabel status;
    
    int numClicks;
    boolean isDone = false;
    boolean isPlayerOneTurn = true;
    
    private static char currentPlayer = PLAYER_1.charAt(0);

    // constructor
    public TicTacToe() 
    {
        //setting my panel
        panel = this.setPanel();

        //set JLabel
        status = this.setLabel(WELCOME_MSSG, JLabel.CENTER, YELLOW_COLOR, HEL_BOLD_12, BLUE_COLOR);

        //create array
        game = new char[ARRAY_ROWS][ARRAY_COLUMNS];
        grid = new JLabel[ARRAY_ROWS][ARRAY_COLUMNS];

        //set arrays
        this.fillArray(grid);

        //set JButton
        restart = new JButton(RESTART);

        //add action listener to button
        restart.addActionListener(this);

        // add to frame
        add(status, BorderLayout.NORTH);
        add(panel, BorderLayout.CENTER);
        add(restart, BorderLayout.SOUTH);

        // set the Frame and display
        createFrame();
    }

    // initialize the JPanel
    private JPanel setPanel() 
    {
        //set panel
        panel = new JPanel();

        //setting layout
        panel.setLayout(new GridLayout(ROWS, COLUMNS, H_GAP, V_GAP));

        panel.setBackground(BLACK_COLOR);

        return panel;
    }

    // initialize the JLabel
    private JLabel setLabel(String message, int pos, Color bkgroundcolor, Font font, Color foregroundColor) 
    {
        // initialize the label
        nameLabel = new JLabel(message, pos);

        //making my label opaque
        nameLabel.setOpaque(true);

        //setting background color
        nameLabel.setBackground(bkgroundcolor);

        //set font
        nameLabel.setFont(font);

        //set foreground color
        nameLabel.setForeground(foregroundColor);

        return nameLabel;
    }

    private void fillGameArray(char[][] array, int row, int col) 
    {
        //looping through array to fill each position with a ' ' character.
        array[row][col] = BLANK_SPACE.charAt(0);

    }

    private void fillArray(JLabel[][] grid) 
    {
        //loopin through array to fill each position with a blank.
        for (int row = START_LOOP; row < END_LOOP; row++) 
        {
            for (int column = START_LOOP; column < END_LOOP; column++) 
            {
                
                //creating a JLabel and placing it in corresponding element in array
                grid[row][column] = this.setLabel(BLANK_SPACE, JLabel.CENTER, WHITE_COLOR, HEL_BOLD_32, BLUE_COLOR);

                //attaching a mouselistener to the JLabel
                grid[row][column].addMouseListener(this);

                //adding JLabel to panel
                this.setJLabelsInPanel(grid, row, column);

                //placing a space in the current element of the char array named game
                this.fillGameArray(game, row, column);
            }
        }
    }

    private void setJLabelsInPanel(JLabel[][] array, int row, int col) 
    {
        //adding the JLabel to the JPanel
        panel.add(array[row][col]);

    }

    // helper method to create the frame
    private void createFrame() 
    {
        //setting title of GUI app
        this.setTitle(FRAME_TITLE);

        //setting frame of GUI app
        this.setSize(FRAME_WIDTH, FRAME_HEIGHT);

        //make sure when I exit out, it completely exits out and is not in memory
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //display GUI App
        this.setVisible(true);
    }
    private void resetGame()
    {
        final int NO_CLICKS = 0;
        
        for(int row = START_LOOP; row < END_LOOP; row++)
        {
            for(int col = START_LOOP; col < END_LOOP; col++)
            {
                //creating a JLabel and placing it in corresponding element in array
                grid[row][col].setText(BLANK_SPACE);
                game[row][col] = BLANK_SPACE.charAt(0);

            }
        }
        
        numClicks = NO_CLICKS;
        isPlayerOneTurn = true;
    }
    private void gameOver()
    {
        //variable to hold winner player
        char winner = ' ';
        
        //constants
        final int POS_ZERO = 0;
        final int POS_ONE = 1;
        final int POS_TWO = 2;
        final int MAX_NUM_CLICKS = ARRAY_ROWS * ARRAY_COLUMNS;
        final String TIE_GAME = "Tie Game!";
        final String PLAYER_ONE_TURN = "X's Turn";
        final String PLAYER_TWO_TURN = "O's Turn";
        
        
        //booleans for if statements
        boolean diagonalLeft = ((game[POS_ZERO][POS_ZERO] == game[POS_ONE][POS_ONE]) && (game[POS_ZERO][POS_ZERO] == game[POS_TWO][POS_TWO]));
        boolean diagonalRight = ((game[POS_TWO][POS_ZERO] == game[POS_ONE][POS_ONE]) && (game[POS_TWO][POS_ZERO] == game[POS_ZERO][POS_TWO]));
       
        
        //checking diagonals
        if(diagonalLeft)
        {
            winner = game[POS_ZERO][POS_ZERO];
        }
        else if (diagonalRight)
        {
            winner = game[POS_TWO][POS_ZERO];
        }
        else //checking rows and columns
        {
            for(int row = START_LOOP; row < END_LOOP; row++)
            {
                //checking rows
                if((game[row][POS_ZERO] != BLANK_SPACE.charAt(0)) && (game[row][POS_ZERO] == game[row][POS_ONE] && game[row][POS_ZERO] == game[row][POS_TWO]))
                {
                    winner = game[row][POS_ZERO];
                }
                //checking columnns
                else if ((game[POS_ZERO][row] != BLANK_SPACE.charAt(0)) && (game[POS_ZERO][row] == game[POS_ONE][row]) && (game[POS_ZERO][row] == game[POS_TWO][row]))
                {
                    winner = game[POS_ZERO][row];
                }
            }
            
        }
        isDone = true;
        
        if((winner == BLANK_SPACE.charAt(0)) && (numClicks == MAX_NUM_CLICKS))
        {
            status.setText(TIE_GAME);
        }
        else if(winner != BLANK_SPACE.charAt(0))
        {
            status.setText(String.format("Game Over: %c won!!!", winner));
        }
        else
        {
            if(isPlayerOneTurn == true)
            {
                currentPlayer = PLAYER_1.charAt(0);
                status.setText(PLAYER_ONE_TURN);
                isDone = false;
            }
            else
            {
                currentPlayer = PLAYER_2.charAt(0);
                status.setText(PLAYER_TWO_TURN);
                isDone = false;
            }
            
        }

        
    }

    @Override
    public void actionPerformed(ActionEvent e) 
    {
        this.resetGame();
    }

    @Override
    public void mouseClicked(MouseEvent e) 
    {
        //constants
        final String INVALID = "Invalid Move";
        final Color RED = Color.RED;
        final Color BLUE = Color.BLUE;
        
        if (this.isDone) 
        {
            this.resetGame();
        }

        JLabel clicked = (JLabel) e.getSource();

        next:
        for (int row = START_LOOP; row < END_LOOP; row++) 
        {
            for (int col = START_LOOP; col < END_LOOP; col++) 
            {
                if (clicked == grid[row][col]) 
                {
                    if (clicked.getText() != BLANK_SPACE) 
                    {
                        status.setText(INVALID);
                        break next;
                    } 
                    else if ((clicked.getText() == BLANK_SPACE) && (isPlayerOneTurn == true)) 
                    {
                        clicked.setText(PLAYER_1);
                        clicked.setForeground(RED);
                        this.game[row][col] = PLAYER_1.charAt(0);

                    } 
                    else 
                    {
                        clicked.setText(PLAYER_2);
                        clicked.setForeground(BLUE);
                        this.game[row][col] = PLAYER_2.charAt(0);
                    }
                    // Toggle isXTurn, incrment numClicks
                    currentPlayer = ((currentPlayer == PLAYER_1.charAt(0)) ? PLAYER_2.charAt(0) : PLAYER_1.charAt(0));
                    this.isPlayerOneTurn = !this.isPlayerOneTurn;
                    this.numClicks++;
                    // Check to see if game is over after each click
                    this.gameOver();
                }
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {}
    @Override
    public void mouseReleased(MouseEvent e) {}
    @Override
    public void mouseEntered(MouseEvent e) {}
    @Override
    public void mouseExited(MouseEvent e) {}

    public static void main(String[] args) {
        TicTacToe ticTacToe = new TicTacToe();
    }
}
