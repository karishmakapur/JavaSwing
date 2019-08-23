/*  
    Student Name: Karishma Kapur
    Student ID: 0558326
    Date: 2/20/19
    Project: Assignment 16
    Description: create a calculator to determine monthly payment and total cost using GUI.
 */
package assignment.pkg16.jbuttons.textfields;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class HowMuch extends JFrame implements ActionListener 
{

    // program settings =====================
    
    //constant for the GUI app title
    private static final String FRAME_TITLE = "Karishma's GUI App";

    //constants for the GUI app frame size
    private static final int FRAME_WIDTH = 350;
    private static final int FRAME_HEIGHT = 300;

    //constants for layout
    private static final int ROWS = 0;
    private static final int COLUMNS = 2;
    private static final int PIXEL_VGAP = 5;
    private static final int PIXEL_HGAP = 5;

    //constants for labels displayed on GUI app
    private static final String AMOUNT_OF_PURCHASE = "Amount Of Purchase ";
    private static final String INTEREST_RATE = "Interest Rate [like 7.5] ";
    private static final String YEARS_TO_PAY = "Years To Pay ";
    private static final String MONTHLY_PAYMENT = "Monthly Payment";
    private static final String TOTAL_PURCHASE_COST = "Total Purchase Cost";

    //instance variables
    JLabel nameLabel;
    JButton calculateButton;
    JTextField amtOfPurchaseText;
    JTextField interestRateText;
    JTextField yearsToPayText;
    JLabel monthlyPaymentOutput;
    JLabel totalPurchaseCostOutput;

    // constructor
    public HowMuch() 
    {
        //constants 
        final int FIELD_SIZE = 10;
        
        //initializing the instance variables
        calculateButton = new JButton("Calculate");
        amtOfPurchaseText = new JTextField(FIELD_SIZE);
        interestRateText = new JTextField(FIELD_SIZE);
        yearsToPayText = new JTextField(FIELD_SIZE);
        monthlyPaymentOutput = new JLabel();
        totalPurchaseCostOutput = new JLabel();

        // set the components to add
        JLabel amountOfPurchaseLabel = setNameLabel(AMOUNT_OF_PURCHASE, JLabel.LEFT);
        JLabel interestRateLabel = setNameLabel(INTEREST_RATE, JLabel.LEFT);
        JLabel yearsToPayLabel = setNameLabel(YEARS_TO_PAY, JLabel.LEFT);
        JLabel monthlyPaymentLabel = setNameLabel(MONTHLY_PAYMENT, JLabel.LEFT);
        JLabel totalPurchaseCostLabel = setNameLabel(TOTAL_PURCHASE_COST, JLabel.LEFT);

        // add the labels to the frame to display
        add(amountOfPurchaseLabel);
        add(amtOfPurchaseText);
        add(interestRateLabel);
        add(interestRateText);
        add(yearsToPayLabel);
        add(yearsToPayText);
        add(monthlyPaymentLabel);
        add(monthlyPaymentOutput);
        add(totalPurchaseCostLabel);
        add(totalPurchaseCostOutput);
        add(calculateButton);

        //activiating the JButton object
        calculateButton.addActionListener(this);

        // set the Frame and display
        createFrame();

    }

    // helper method to create the frame
    private void createFrame() 
    {
        //setting the layout for the GUI app
        setLayout(new GridLayout(ROWS, COLUMNS, PIXEL_VGAP, PIXEL_HGAP));
        
        //setting the title for the GUI app
        setTitle(FRAME_TITLE);
        
        //setting the size for the window of GUI app
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        
        //ensuring that when I exit the GUI app it is taken out of memory as well
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //displaying GUI app.
        setVisible(true);
    }

    // initialize the JLabel
    private JLabel setNameLabel(String text, int pos) 
    {
        // initialize the label
        nameLabel = new JLabel(text, pos);

        return nameLabel;
    }

    @Override
    public void actionPerformed(ActionEvent e) 
    {
        //constants for mathmatical operations
        final int DIVIDE_BY = 100;
        final int MONTHS_IN_YEAR = 12;
        final int ONE = 1;
        
        //taking the string and getting the text from string, putting into 
        //Object Double to them put into primitive double.
        //This is converting the output.
        double amtOfPurchase = Double.parseDouble(amtOfPurchaseText.getText());
        double interestRate = Double.parseDouble(interestRateText.getText());
        double yearsToPay = Double.parseDouble(yearsToPayText.getText());

        //calculations to determine the interest rate
        interestRate = ((interestRate / DIVIDE_BY) / MONTHS_IN_YEAR);
        
        //splitting the equation for monthly payment into 2 parts 
        double topOfEquation = (amtOfPurchase * interestRate);
        double bottomOfEquation = (ONE - (Math.pow(ONE / (ONE + interestRate), yearsToPay * MONTHS_IN_YEAR)));
        
        //calculating monthly payment
        double monthlyPayment = (topOfEquation / bottomOfEquation);
        
        //calculating total cost of the purchase
        double totalPurchaseCost = monthlyPayment * MONTHS_IN_YEAR * yearsToPay;

        
        //displaying the text, by setting it into its label.
        monthlyPaymentOutput.setText(String.format("$%.2f", monthlyPayment));
        totalPurchaseCostOutput.setText(String.format("$%.2f", totalPurchaseCost));
    }

    public static void main(String[] args) {
        //creating instance of HowMuch (as said in assignment to do so) to create the GUI app and display.
        HowMuch guiApp = new HowMuch();
    }

}
