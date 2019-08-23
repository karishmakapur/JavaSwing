/*  
    Student Name: Karishma Kapur
    Student ID: 0558326
    Date: 2/20/19
    Project: Assignment 19
    Description: concentration program
 */
package assignment.pkg19.more.objects;

import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
import java.util.Random;
import javax.swing.*;

public class Concentration extends JFrame implements ActionListener, MouseListener {

    //constant for the GUI app title
    private static final String FRAME_TITLE = "Concentration Program";

    //constant for the GUI app frame size
    private static final int FRAME_WIDTH = 300;
    private static final int FRAME_HEIGHT = 225;

    //constants
    private static final String NEW_GAME_BUTTON_STR = "New Game";
    private static final String ZERO_GUESSES_STR = "0";
    private static final String NUM_OF_TRIES_STR = "Number of Tries:";

    private static final int ROWS = 4;
    private static final int COLS = 4;
    private static final int HGAP = 5;
    private static final int VGAP = 5;
    private static final String EMPTY_STR = "";
    private static final String SPACE = " ";
    
    //field variables
    private JLabel[] boardArray;
    private int[] numArray;
    private int firstChoice = -1;
    private int numOfTries = 0;
    private JLabel firstLabelSelected;
    private JButton newGameButton;
    private JLabel numOfGuessesLabel;
    private JPanel controlPanel;
    private JPanel boardPanel;

    //default constructor
    public Concentration() 
    {
        //constant
        final int SIZE_OF_BOARD = 16;

        //initializing instance variables
        newGameButton = new JButton(NEW_GAME_BUTTON_STR);
        numOfGuessesLabel = new JLabel(ZERO_GUESSES_STR);
        controlPanel = new JPanel();
        boardPanel = new JPanel();
        JLabel informationalLabel = new JLabel(NUM_OF_TRIES_STR, JLabel.RIGHT);

        //setting the size of the arrays
        boardArray = new JLabel[SIZE_OF_BOARD];
        numArray = new int[SIZE_OF_BOARD];

        //filling the numArray
        this.fillArray();

        //creating JLabels
        this.createLabels();

        //shuffling the array
        this.shuffleBoard();

        //setting layout to flowlayout
        controlPanel.setLayout(new FlowLayout());

        //attaching action listener
        newGameButton.addActionListener(this);
                
        //add button to control panel
        controlPanel.add(newGameButton);

        //adding informational label to control panel
        controlPanel.add(informationalLabel);

        //add numOfGuessesLabel to control panel
        controlPanel.add(numOfGuessesLabel);

        //adding panels to frame
        add(boardPanel, BorderLayout.NORTH);
        add(controlPanel, BorderLayout.SOUTH);

        //creating the frame
        createFrame();
    }

    // helper method to create the frame
    private void createFrame() 
    {
        //setting title of GUI app
        setTitle(FRAME_TITLE);

        //setting frame of GUI app
        setSize(FRAME_WIDTH, FRAME_HEIGHT);

        //make sure when I exit out, it completely exits out and is not in memory
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //display GUI App
        setVisible(true);
    }

    private void fillArray() 
    {
        //constants
        final int START_POS = 1;
        final int REPEATS = 2;
        final int LOWEST_NUM = 1;
        final int HIGHEST_NUM = 8;
        //variable to hold index of where to add number
        int index = 0;

        //for loop to make sure each number gets inputted twice.
        for (int countOfNum = START_POS; countOfNum <= REPEATS; countOfNum++) 
        {
            //for loop to make sure each number 1-8 is put into the array
            for (int num = LOWEST_NUM; num <= HIGHEST_NUM; num++) 
            {
                this.numArray[index] = num;
                index++;
            }
        }
    }

    private void createLabels() 
    {
        //constants
        final int START_POS = 0;
        final Color PINK = Color.PINK;
        final Color WHITE = Color.WHITE;
        final Font HELVETICA_FONT = new Font("Helvetica", Font.BOLD, 24);
        

        //setting layout to gridlayout
        boardPanel.setLayout(new GridLayout(ROWS, COLS, HGAP, VGAP));

        for (int i = START_POS; i < boardArray.length; i++) 
        {
            boardArray[i] = new JLabel(EMPTY_STR, JLabel.CENTER);

            //setting opacity to true
            boardArray[i].setOpaque(true);

            //setting color of each JLabel
            boardArray[i].setBackground(PINK);

            //setting foreground color
            boardArray[i].setForeground(WHITE);

            //setting font
            boardArray[i].setFont(HELVETICA_FONT);

            //attaching addMouseListener
            boardArray[i].addMouseListener(this);

            //setting the name of the label to its index
            boardArray[i].setText(SPACE);

            //adding each label to the boardPanel
            boardPanel.add(boardArray[i]);
        }
    }

    private void shuffleBoard() 
    {
        //constants
        final int START_POS = 0;
        final int END_POS = 500;

        //variable for swaping
        int randomIndex1;
        int randomIndex2;
        int temp;

        //object of Random class to generate a random number for the index.
        Random rand = new Random();

        //for loop to swap
        for (int i = START_POS; i < END_POS; i++) {
            //setting the random indexes
            randomIndex1 = rand.nextInt(numArray.length);
            randomIndex2 = rand.nextInt(numArray.length);

            //swapping the values in the indexes
            temp = numArray[randomIndex1];
            numArray[randomIndex1] = numArray[randomIndex2];
            numArray[randomIndex2] = temp;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) 
    {
        //constants
        final int START_POS = 0;

        JButton button = (JButton) e.getSource();

        //shuffling the board
        this.shuffleBoard();

        //setting the first Choice variable to -1
        firstChoice = -1;

        //setting each JLabels in concentrationBoard to an empty string.
        for (int i = START_POS; i < boardArray.length; i++) {
            boardArray[i].setText(SPACE);
        }

        //variables tries set to 0
        numOfTries = 0;

        String numOfTriesStr = Integer.toString(numOfTries);

        //setText message to numOfTries
        numOfGuessesLabel.setText(numOfTriesStr);
        

    }

    @Override
    public void mouseClicked(MouseEvent e) 
    {
        //constants
        final int PARAMETER = 0;
        final int MILLISECONDS = 250;

        //reference variable to JLabel
        JLabel label = (JLabel) e.getSource();

        //variable to hold index
        int theNumber = Arrays.asList(boardArray).indexOf(label);

        //if statement that checks to see if firstChoice = -1
        if (firstChoice == -1) 
        {
            //setting the text of the label
            label.setText(EMPTY_STR + numArray[theNumber]);

            //setting instance field label to local label
            firstLabelSelected = label;

            //setting variable to index number
            firstChoice = theNumber;
        } 
        else if (numArray[theNumber] != numArray[firstChoice]) 
        {
            //setting the text of the label
            label.setText(EMPTY_STR + numArray[theNumber]);

            //forcing the panel and controls contained in it to be painted
            boardPanel.paintImmediately(PARAMETER, PARAMETER, boardPanel.getWidth(), boardPanel.getHeight());

            //try catch block
            try 
            {
                //if the two cards do not match then turn them both back after 250
                Thread.sleep(MILLISECONDS);
            } 
            catch (InterruptedException ie){}

            //setting the text to an empty str
            firstLabelSelected.setText(EMPTY_STR);

            //setting the text of local label
            label.setText(EMPTY_STR);

            //setting the label to null
            firstLabelSelected.setText(null);

            //setting firstchoice back to -1
            firstChoice = -1;

            //incrementing the tries counter
            numOfTries++;
        } 
        else 
        {
            //setting text
            label.setText(EMPTY_STR + numArray[theNumber]);

            //setting variable
            firstChoice = -1;

            //incrementing tries counter
            numOfTries++;
        }
        //setting the next of the label to the number of tries
        String numOfTriesString = Integer.toString(numOfTries);

        numOfGuessesLabel.setText(numOfTriesString);
    }

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

}
