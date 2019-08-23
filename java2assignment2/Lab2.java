/*  
    Student Name: Karishma Kapur
    Student ID: 0558326
    Date: 3/25/19
    Project: Assignment 2
    Description: swing applications
*/
package java2assignment2;

import java.awt.*;
import java.awt.event.*;
import java.util.Scanner;
import javax.swing.*;

public class Lab2 extends JFrame implements ActionListener {

    //instance variables
    private JLabel lbl3DigitNum;
    private JLabel lblSum;
    private JLabel lblReverse;
    private JLabel lblArray;
    private JLabel lblTextColor;
    private JLabel lblSumOutput;
    private JLabel lblReverseOutput;
    private JLabel lblArrayOutput;
    private JTextField numtf;
    private JRadioButton redRB;
    private JRadioButton greenRB;
    private JRadioButton blueRB;
    private JButton computeButton;
    
    private class RadioButton implements ActionListener
    {
        
        @Override
        public void actionPerformed(ActionEvent e) 
        {
            
            if(e.getSource() == redRB)
            {
                lblSumOutput.setForeground(Color.RED);
                lblReverseOutput.setForeground(Color.RED);
                lblArrayOutput.setForeground(Color.RED);
            }
            else if(e.getSource() == greenRB)
            {
                lblSumOutput.setForeground(Color.GREEN);
                lblReverseOutput.setForeground(Color.GREEN);
                lblArrayOutput.setForeground(Color.GREEN);
            }
            else if(e.getSource() == blueRB)
            {
                lblSumOutput.setForeground(Color.BLUE);
                lblReverseOutput.setForeground(Color.BLUE);
                lblArrayOutput.setForeground(Color.BLUE);
            }
            
        }
    }
    private class Compute implements ActionListener
    {
       
        @Override
        public void actionPerformed(ActionEvent e) 
        {
            int num = Integer.parseInt(numtf.getText());

            int sumNum = sumNums(num);
            String reverseNum = reverseNums(num);
            int[] arrayNum = getArray(num);

            lblSumOutput.setText(Integer.toString(sumNum));

            lblReverseOutput.setText(reverseNum);
            
            StringBuilder builder = new StringBuilder();
            for(int i = 0; i < 3; i++)
            {
                builder.append(Integer.toString(arrayNum[i]));
                
                builder.append("             ");
            }
           
            String arrayString = builder.toString();
            
            lblArrayOutput.setText(arrayString);
        }
    }
    public Lab2(String title, int width, int height)
    {
        
        lbl3DigitNum = new JLabel("Enter a 3 Digit Number:");
        lblSum = new JLabel("Sum:");
        lblReverse = new JLabel("Reverse:");
        lblArray = new JLabel("Array:");
        lblTextColor = new JLabel("Text Color");
        redRB = new JRadioButton("Red");
        greenRB = new JRadioButton("Green");
        blueRB = new JRadioButton("Blue");
        numtf = new JTextField(10);
        computeButton = new JButton("Compute");
        
        lblSumOutput = new JLabel();
        lblReverseOutput = new JLabel();
        lblArrayOutput = new JLabel();
        
        this.createFrame(title, width, height);
        
    }
    public Lab2()
    {
        this("Lab 2", 450, 450);
    }
    
    // helper method to create the frame
    private void createFrame(String title, int width, int height) 
    {
        //setting layout
        SpringLayout layout = new SpringLayout();
        this.setLayout(layout);
        
        //setting title of GUI app
        this.setTitle(title);

        //setting frame of GUI app
        this.setSize(width, height);

        //make sure when I exit out, it completely exits out and is not in memory
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //display GUI App
        this.setVisible(true);
        
        //adding label
        this.add(lbl3DigitNum);
        this.add(lblSum);
        this.add(lblReverse);
        this.add(lblArray);
        this.add(lblTextColor);
        this.add(numtf);
        this.add(redRB);
        this.add(greenRB);
        this.add(blueRB);
        this.add(computeButton);
        this.add(lblSumOutput);
        this.add(lblReverseOutput);
        this.add(lblArrayOutput);
        
        //putConstraint 3 digits & its text field
        layout.putConstraint(SpringLayout.WEST, lbl3DigitNum, 15, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, lbl3DigitNum, 15, SpringLayout.NORTH, this);
           
        layout.putConstraint(SpringLayout.WEST, numtf, 90, SpringLayout.EAST, lbl3DigitNum);
        layout.putConstraint(SpringLayout.NORTH, numtf, 0, SpringLayout.NORTH, lbl3DigitNum);
        
        //putConstraint sum
        layout.putConstraint(SpringLayout.WEST, lblSum, 15, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, lblSum, 30, SpringLayout.SOUTH, lbl3DigitNum);
        
        layout.putConstraint(SpringLayout.WEST, lblSumOutput, 90, SpringLayout.EAST, lbl3DigitNum);
        layout.putConstraint(SpringLayout.NORTH, lblSumOutput, 30, SpringLayout.SOUTH, numtf);
        
        //putConstraint reverse
        layout.putConstraint(SpringLayout.WEST, lblReverse, 15, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, lblReverse, 30, SpringLayout.SOUTH, lblSum);
        
        layout.putConstraint(SpringLayout.WEST, lblReverseOutput, 90, SpringLayout.EAST, lbl3DigitNum);
        layout.putConstraint(SpringLayout.NORTH, lblReverseOutput, 30, SpringLayout.SOUTH, lblSumOutput);
        
        //putConstraint array
        layout.putConstraint(SpringLayout.WEST, lblArray, 15, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, lblArray, 30, SpringLayout.SOUTH, lblReverse);
        
        layout.putConstraint(SpringLayout.WEST, lblArrayOutput, 140, SpringLayout.EAST, lblArray);
        layout.putConstraint(SpringLayout.NORTH, lblArrayOutput, 0, SpringLayout.NORTH, lblArray);
        
        //putConstraint text color & buttons
        layout.putConstraint(SpringLayout.WEST, lblTextColor, 15, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, lblTextColor, 30, SpringLayout.SOUTH, lblArray);
        
        layout.putConstraint(SpringLayout.WEST, redRB, 35, SpringLayout.EAST, lblTextColor);
        layout.putConstraint(SpringLayout.NORTH, redRB, 0, SpringLayout.NORTH, lblTextColor);
        
        layout.putConstraint(SpringLayout.WEST, greenRB, 30, SpringLayout.EAST, redRB);
        layout.putConstraint(SpringLayout.NORTH, greenRB, 0, SpringLayout.NORTH, lblTextColor);
        
        layout.putConstraint(SpringLayout.WEST, blueRB, 30, SpringLayout.EAST, greenRB);
        layout.putConstraint(SpringLayout.NORTH, blueRB, 0, SpringLayout.NORTH, lblTextColor);
     
        //putConstraint computeButton
        layout.putConstraint(SpringLayout.WEST, computeButton, 170, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, computeButton, 30, SpringLayout.SOUTH, lblTextColor);
        
       
        redRB.addActionListener(this);
        greenRB.addActionListener(this);
        blueRB.addActionListener(this); 
        computeButton.addActionListener(this);

    }
     //sum of numbers
    public int sumNums(int num)
    {
        int sumNum = 0;
        
        for(int i = 0; i < 3; i++)
        {
            sumNum += num % 10;
            num = num / 10;
        }
        
        return sumNum;
    }
    //reverse the number into a string
    public String reverseNums(int num)
    {
        StringBuilder builder = new StringBuilder();
        
        for(int i = 0; i < 3; i++)
        {
            builder.append(num % 10);
            num = num / 10;
        }
        
        String returnString = builder.toString();
        
        return returnString;
        
    }
    //put each individual digit into an array
    public int[] getArray(int num)
    {
        int numArray[] = new int[3];
         for(int i = 2; i >= 0; i--)
        {
            numArray[i] = num % 10;
            num = num / 10;
        }
         
        return numArray;
    } 
    @Override
    public void actionPerformed(ActionEvent e) 
    {
        Compute computeClass = new Compute();
        computeClass.actionPerformed(e);
        
        RadioButton radio = new RadioButton();
        radio.actionPerformed(e);
    }
    public static void main(String[] args) 
    {
        //object of scanner class, to ask for input later
       Scanner in = new Scanner(System.in);
       
       //object of class, to use class methods
       Lab2 console = new Lab2("Karishma Lab 2", 450, 400);
 
    }

   
    
}
