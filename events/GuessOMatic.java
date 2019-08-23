/*  
    Student Name: Karishma Kapur
    Student ID: 0558326
    Date: 2/26/19
    Project: Assignment 18
    Description: Guess O' Matic
 */
package assignment.pkg18.more.events;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;

public class GuessOMatic extends JFrame implements ActionListener, KeyListener 
{

    // program settings =====================
    //constant for the GUI app title
    private static final String FRAME_TITLE = "Guess O'Matic";

    //constant for the GUI app frame size
    private static final int FRAME_WIDTH = 500;
    private static final int FRAME_HEIGHT = 400;

    //constants for color
    private static final Color YELLOW_COLOR = Color.YELLOW;
    private static final Color WHITE_COLOR = Color.WHITE;

    //constants
    private static final String INITIAL_AMT_OF_ZIPOIDS = "100.00 Zipoids";
    private static final String NEW_PLAYER_STR = "New Player";
    private static final String NEW_NUMBER_STR = "New Number";
    private static final String EMPTY_STR = "";
    private static final int THE_GUESS_FIELD_WIDTH = 10;
    private static final int THE_PLAYER_FIELD_WIDTH = 20;
    private static final int MAX = 100;
    private static final int MIN = 1;
    private static final int RANGE = ((MAX - MIN) + 1);

    // instance variables
    private static JLabel nameLabel;
    private static JTextField usersGuessField;
    private static JLabel zipoidsAmtLabel;
    private static JButton newPlayerButton;
    private static JButton newNumberButton;
    private static JTextField playerNameField;
    private static JTextArea outputTextArea;
    private static JPanel panelOne;
    private static JPanel panelTwo;

    //non-visible attributes 
    private static String playerName = "";
    private static int randomNumber = 20;
    private static int numTries = 0;
    private static int numGames = 0;
    private static double amtRemaining = 100.0;
    Random randomizer = new Random();

    private GuessOMatic() 
    {
        //constants for local variables
        final String MAKE_GUESS_STR = "Make your Guess : ";
        final String TWO_SPACES = "  ";

        //initializing instance variables
        usersGuessField = new JTextField(THE_GUESS_FIELD_WIDTH);
        zipoidsAmtLabel = setLabel(INITIAL_AMT_OF_ZIPOIDS);
        newPlayerButton = new JButton(NEW_PLAYER_STR);
        newNumberButton = new JButton(NEW_NUMBER_STR);
        playerNameField = new JTextField(THE_PLAYER_FIELD_WIDTH);
        outputTextArea = new JTextArea();
        panelOne = new JPanel();
        panelTwo = new JPanel();

        //local variables
        JLabel GuessLabel = setLabel(MAKE_GUESS_STR);
        JLabel spacingLabelOne = setLabel(TWO_SPACES);
        JLabel spacingLabelTwo = setLabel(TWO_SPACES);
        JScrollPane scrollBar = new JScrollPane(outputTextArea);

        //adding to panelOne
        panelOne.add(GuessLabel);
        panelOne.add(usersGuessField);
        panelOne.add(zipoidsAmtLabel);

        //adding to panelTwo
        panelTwo.add(newPlayerButton);
        panelTwo.add(playerNameField);
        panelTwo.add(newNumberButton);

        //adding to frame
        add(panelOne, BorderLayout.NORTH);
        add(panelTwo, BorderLayout.SOUTH);
        add(spacingLabelOne, BorderLayout.EAST);
        add(spacingLabelTwo, BorderLayout.WEST);
        add(scrollBar, BorderLayout.CENTER);

        //attaching actionListener
        newPlayerButton.addActionListener(this);
        newNumberButton.addActionListener(this);

        //attaching actionKeyListener
        playerNameField.addKeyListener(this);
        usersGuessField.addKeyListener(this);

        //getting player info
        this.newPlayer();

        // set the Frame and display
        this.createFrame();

    }

    // initialize the JLabel
    private JLabel setLabel(String message) 
    {
        // initialize the label
        nameLabel = new JLabel(message);

        return nameLabel;
    }

    private void newPlayer() 
    {

        //clearing the JTextArea object
        outputTextArea.setText(EMPTY_STR);

        //disabling components
        outputTextArea.setEnabled(false);
        usersGuessField.setEnabled(false);
        newPlayerButton.setEnabled(false);
        newNumberButton.setEnabled(false);

        //setting background of the Guess to white
        usersGuessField.setBackground(WHITE_COLOR);

        //activating and clearing thePlayer
        playerNameField.setEnabled(true);
        playerNameField.setText(EMPTY_STR);

        //focus users attention on thePlayer field
        playerNameField.requestFocus();

        //setting background of thePlayer field
        playerNameField.setBackground(YELLOW_COLOR);
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

    private void addPlayer() 
    {
        //constants
        final String MUST_ENTER_NAME = "You need to enter a name to play.";

        //getting player name from textfield and putting it into the string that holds the players name
        playerName = playerNameField.getText();

        //checking to see if the player has entered his/her name
        if (playerName.equals(EMPTY_STR)) 
        {
            //if they have not, then display message
            outputTextArea.append(MUST_ENTER_NAME);
        } else 
        {
            //if they have entered their name, then go to function to start the game.
            this.startGame();
        }

        //create the game
        this.newGame();
    }

    //helper function to set the game up, and focus onto the userGuessFiled to get their guess.
    private void startGame() 
    {
        //constants
        final int MAX_START_AMT = 100;
        final int NO_GAMES = 0;
        
        //setting the amtRemaining and numGames field
        amtRemaining = MAX_START_AMT;
        numGames = NO_GAMES;

        //disabling thePlayer field
        playerNameField.setEnabled(false);

        //setting the background color to white
        playerNameField.setBackground(WHITE_COLOR);

        //enabling fields
        newPlayerButton.setEnabled(true);
        newNumberButton.setEnabled(true);
        usersGuessField.setEnabled(true);

        //putting focus back on theGuess
        usersGuessField.requestFocus();

        //setting the background
        usersGuessField.setBackground(YELLOW_COLOR);
    }

    //function to create the game set up
    private void newGame() 
    {
        //constants
        final int NO_TRIES = 0;
        
        //incrementing number of games
        numGames++;
        System.out.println("New game");

        //calculate a new random number
        randomNumber = randomizer.nextInt(RANGE) + MIN;

        //set number of tries
        numTries = NO_TRIES;

        //calling method to display the instructions of the game.
        displayInstructions();

        //subtracting 1 from amount remaining
        amtRemaining--;

        //calling method that will change the playerNameField to display certain info
        //and zipoidsAmtLabel to display certain info.
        this.updateScore();

    }

    //function to display instructions
    private void displayInstructions() 
    {
        //constant
        final String INSTRUCTIONS = "I'm thinking of a number between 1 and 100.\n"
                + "If you guess the number in fewer than 9 tries, you'll earn\n"
                + "\t1 try  \t2.00 Zipoids\n"
                + "\t2 tries\t1.75 Zipoids\n"
                + "\t3 tries\t1.50 Zipoids\n"
                + "\t4 tries\t1.25 Zipoids\n"
                + "\t5 tries\t1.00 Zipoids\n"
                + "\t6 tries\t.75 Zipoids\n"
                + "\t7 tries\t.50 Zipoids\n"
                + "\t8 tries\t.25 Zipoids\n";

        outputTextArea.append(INSTRUCTIONS);
    }

    //function to update textField and Label when game starts
    private void updateScore() 
    {
        playerNameField.setText(String.format("%s's %d game", playerName, numGames));
        zipoidsAmtLabel.setText(String.format("%.2f Zipoids", amtRemaining));
    }

    private void newGuess() 
    {
        //string to hold the usersGuess
        String currentString = usersGuessField.getText();

        //getting the integers from the string.
        int usersCurrentGuess = Integer.parseInt(currentString);

        //incrementing the number of tries
        numTries++;

        //displaying to output area their current amount of tries and the guess they just inputted.
        outputTextArea.append(String.format("Tries: %d\tYour currentGuess is: %d\t", numTries, usersCurrentGuess));

        //checking the users guess
        this.checkGuess(usersCurrentGuess);
    }

    //helper function to check if user guessed correctly
    private void checkGuess(int usersCurrentGuess) 
    {
        //constants
        final String GUESS_HIGHER = "Guess Higher\n";
        final String GUESS_LOWER = "Guess Lower\n";
        
        //if the user guessed the randomNumber
        if (usersCurrentGuess == randomNumber) 
        {
            //then they won. Go to function.
            this.gameWon();
        } 
        else 
        {
            //if the user did not guess the randomNumber, check to see if their 
            //guess was less than the randomNumber, is to then:
            if (usersCurrentGuess < randomNumber) 
            {
                //output to display area that they need to guess higher.
                outputTextArea.append(GUESS_HIGHER);
            } 
            else 
            {
                //if the guess is not less, then the randomNumber, 
                //then it is greater than the randomNumber
                //display to user they need to guess lower.
                outputTextArea.append(GUESS_LOWER);
            }

            //get user attention to the guess text field
            usersGuessField.requestFocus();

            //set the field to the color yellow
            usersGuessField.setBackground(YELLOW_COLOR);

            //select whatever is inside the text field, so when user enters a 
            //new guess, it will erase the old one
            usersGuessField.selectAll();
        }
    }

    private void gameWon() 
    {
        //constant
        final String WINNER_MESSAGE = "*******WINNER*******\n";
        final String INSTRUCT_USER_STR = "Please press the New Number Button to continue\n";
        final int MIN_AMT_TRIES = 1;
        final int MAX_AMT_TRIES_FOR_ZIPOIDS = 9;
        final double MAX_AMT_WON = 2.00;
        final double AMOUNT_DEDUCTED_MULTIPLIER = .25;
        final double NO_AMT_WON = 0.0;

        //messages for screen
        outputTextArea.setText(EMPTY_STR);
        outputTextArea.append(WINNER_MESSAGE);
        outputTextArea.append(String.format("The remaining amount of Zipoids are %.2f.\n", amtRemaining));
        outputTextArea.append(String.format("It took you %d tries to guess correctly.\n", numTries));

        //initializing variable to use later 
        double amountWon = NO_AMT_WON;

        //calculating the amount the user has won
        if (numTries > MIN_AMT_TRIES && numTries < MAX_AMT_TRIES_FOR_ZIPOIDS) 
        {
            //calculates the correct zipoids won between the tries of 1 and 8 (because its not equal to 9).
            amountWon = MAX_AMT_WON - ((numTries - MIN_AMT_TRIES) * AMOUNT_DEDUCTED_MULTIPLIER);
        } 
        else if (numTries == MIN_AMT_TRIES) 
        {
            //the player only took one try, so there is nothing deducted from the max amount.
            amountWon = MAX_AMT_WON;
        } 
        else 
        {
            //the player took more than 9 tries, they don't win any zipoids
            amountWon = NO_AMT_WON;
        }

        //printing to output area how much they won.
        outputTextArea.append(String.format("You won %.2f\n", amountWon));

        //determining the amtRemaining
        amtRemaining += amountWon;

        //printing to output area the players name and how much they have remaining.
        outputTextArea.append(String.format("%s has %.2f Zipoids\n", playerName, amtRemaining));

        //printing to output area that they should press new number button to continue playing
        outputTextArea.append(INSTRUCT_USER_STR);

        //go to update score function, which will change the playerNameField 
        //to display certain info and zipoidsAmtLabel to display certain info.
        this.updateScore();

        //setting the user guess text field area to a blank str
        usersGuessField.setText(EMPTY_STR);

        //setting the background color of the field to white
        usersGuessField.setBackground(WHITE_COLOR);

        //disabling the field
        usersGuessField.setEnabled(false);

        //requesting users attention to the newNumberButton
        newNumberButton.requestFocus();

    }

    @Override
    public void actionPerformed(ActionEvent e) 
    {
        //reference variable
        JButton button = (JButton) e.getSource();

        //if / else if statement that determines the action
        //if the user presses the newPlayerButton, then go to newplayer function,
        //which will add a new player.
        if (button == newPlayerButton) 
        {
            this.newPlayer();
        } 
        else 
        {
            //if the user presses the newNumber button then let them enter a number.

            //enabling the field to get user input for a guess
            usersGuessField.setEnabled(true);

            //requesting focus to the textfield
            usersGuessField.requestFocus();

            //setting the fields background color to yellow
            usersGuessField.setBackground(YELLOW_COLOR);
            
            //generate a new game
            this.newGame();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) 
    {
        //reference variable
        JTextField textField = (JTextField) e.getSource();

        //determine if key released was enter key
        int key = e.getKeyCode();

        //if the user presses the enter button on keyboard
        if (key == KeyEvent.VK_ENTER) 
        {
            //then check if they pressed enter in the textfield for the player name
            if (textField == playerNameField) 
            {
                //if so, they add a new player
                this.addPlayer();

            } 
            else 
            {
                //if the user presses enter in the guess textfield then
                //let go to newGuess function.
                this.newGuess();
            }
        }
    }

    public static void main(String[] args) 
    {
        GuessOMatic GOM = new GuessOMatic();
    }

}
