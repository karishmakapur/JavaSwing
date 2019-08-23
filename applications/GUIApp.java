/*  
    Student Name: Karishma Kapur
    Student ID: 0558326
    Date: 2/20/19
    Project: Assignment 15
    Description: make GUI App with labels for name and date. Assign colors to name and date. Display
 */
package assignment.pkg15.gui.applications;

import javax.swing.*;
import java.awt.*;

public class GUIApp extends JFrame
{

     // program settings =====================

    //constant for the GUI app title
    private static final String FRAME_TITLE = "First GUI App";
    
    //constant for the GUI app frame size
    private static final int FRAME_WIDTH = 300;
    private static final int FRAME_HEIGHT = 300;

    //constants for layout
    private static final int ROW = 3;
    private static final int COLUMN = 1;
    
    // constants for color and font for GUI app
    private static final Font NAME_FONT = new Font("sansserif", Font.PLAIN, 30);
    private static final Font DATE_FONT = new Font("sansserif", Font.ITALIC, 15);
    private static final Color NAME_COLOR = new Color (195, 89, 233);
    private static final Color DATE_COLOR = new Color (0.94F, 0.45F, 0.45F);

    
   //constants for labels displayed on GUI app
   private static final String FIRST_NAME = "Karishma";
   private static final String LAST_NAME = "Kapur";
   private static final String DATE = "February 20th, 2019";
    
    // instance variables
    JLabel nameLabel;

    // constructor
    public GUIApp() 
    {
        // set the components to add
	JLabel firstName = setLabel(FIRST_NAME, NAME_FONT, NAME_COLOR);
        JLabel lastName = setLabel(LAST_NAME, NAME_FONT, NAME_COLOR);
        JLabel date = setLabel(DATE, DATE_FONT, DATE_COLOR);
		
	// add the label to the frame to display
	add(firstName);
        add(lastName);
        add(date);

        // set the Frame and display
        createFrame();
    }

    // helper method to create the frame
    private void createFrame() 
    {
        //setting the layout of frame
        setLayout(new GridLayout(ROW, COLUMN));
        
        //setting the title of the GUI app
        setTitle(FRAME_TITLE);
        
        //setting the size of the GUI App
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        
        //ensuring that the app closes and is not on memory after exiting
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    // initialize the JLabel
    private JLabel setLabel(String text, Font fontType, Color textColor) 
    {
        // initialize the label
        nameLabel = new JLabel(text);

        //setting the font of the label
        nameLabel.setFont(fontType);
        
        //setting the color of the label
        nameLabel.setForeground(textColor);
        
        return nameLabel;
    }
    
    public static void main(String[] args) 
    {
        
        //creating an instance of GUIApp, so I can create the app and display the app.
        GUIApp guiApp = new GUIApp();
    }
    
}
