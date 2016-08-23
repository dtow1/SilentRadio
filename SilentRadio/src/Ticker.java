/**
 *@author Jason Boyer
 *Date  :  12/16/15
 *Assignment: Extra Credit assignment
 *Program Function: Create a silent radio program that displays a 
 *news message. The message is read in from a file displayed 3 times
 *and then a new message is loaded. The speed can be adjusted and
 *the direction of the text can be changed as well.
 */

import java.awt.Font;
import java.awt.event.*;
import java.io.*;
import java.util.*;

import javax.swing.JLabel;
import javax.swing.Timer;


public class Ticker extends JLabel
{
    private ArrayDeque<Character> displayText = new ArrayDeque<>();
    private ArrayDeque<Character> tempText = new ArrayDeque<>();
    private Font tickerFont;
    private boolean shiftRight;
    private int speed;
    private Timer tickerTimer;
    private ArrayList<String> newsFeed;
    private int lineLength, count;
    
    Ticker()
    {
        shiftRight=true;
        speed=10;
        newsFeed = new ArrayList<String>();
        File file = new File("tickernews.txt");
        Scanner fileInput;
        count = 0;
        try
        {
            fileInput = new Scanner(file);
            while(fileInput.hasNext())
            {
                newsFeed.add(fileInput.nextLine());
            }
        } catch (FileNotFoundException e1)
        {
            // TODO Auto-generated catch block
            //e1.printStackTrace();
        	System.out.println("Error loading file, default text playing");
        }


        tickerFont = new Font("Garamond",Font.PLAIN,25);
        this.setFont(tickerFont);
        ActionListener advanceTimer = new ActionListener() 
        {

            @Override
            public void actionPerformed(ActionEvent e)
            {
                shiftText();
                if(count>lineLength*3)
                {
                    updateString();
                }
                count++;
            }
            
        };
        updateString();
        tickerTimer = new Timer(100,advanceTimer);
        tickerTimer.start();
    }
    
    public void updateString() {
        char testString[];
        if(!newsFeed.isEmpty())
        {
            testString = newsFeed.remove(0).toCharArray();
        }
        else
        {
            testString = "No data left in file/no data file".toCharArray();
        }
        lineLength=testString.length;
        displayText.clear();
        count=0;
        for(int i=0; i<testString.length;i++)
        {
            displayText.add(testString[i]);
        }
    }
    public void shiftText()
    {
        if(shiftRight)
        {
            displayText.addFirst(displayText.removeLast());
        }
        else
        {
            displayText.addLast(displayText.removeFirst());
        }
        
        tempText=displayText.clone();
        StringBuilder outputString = new StringBuilder();
        while(!tempText.isEmpty())
        {
            outputString.append(tempText.removeFirst());            //.add((String)displayText.removeFirst());
        }
        this.setText(outputString.toString());
    }

    public void setSpeed(int newSpeed)
    {
        tickerTimer.setDelay(newSpeed);
    }
    
    public void changeDirection() 
    {
        shiftRight = shiftRight ? false : true;
    }
    
    public void startStop() {
        if(tickerTimer.isRunning())
            tickerTimer.stop();
        else
            tickerTimer.start();
    }

    
    public void setText()
    {
        
    }
}
