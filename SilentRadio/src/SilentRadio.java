/**
 *@author Jason Boyer
 *Date  :  12/16/15
 *Assignment: Extra Credit assignment
 *Program Function: Create a silent radio program that displays a 
 *news message. The message is read in from a file displayed 3 times
 *and then a new message is loaded. The speed can be adjusted and
 *the direction of the text can be changed as well.
 */




import java.awt.*;
import java.awt.event.*;
import java.util.Hashtable;

import javax.swing.*;
import javax.swing.event.*;


public class SilentRadio
{

    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(()-> new SilentRadio());
        
       
    }
    
    SilentRadio(){
        JFrame radio = new JFrame("Silent Radio");
        JLabel title = new JLabel("Silent Radio") 
        {
            public Font getFont() 
            {
                return new Font("Garamond",Font.PLAIN,70);
            }
        };
        
        Ticker newsDisplay = new Ticker();
        title.setHorizontalAlignment(JLabel.CENTER);
        radio.setSize(400,200);
        radio.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        radio.setLocationRelativeTo(null);
        radio.add(title, BorderLayout.NORTH);
        radio.add(newsDisplay, BorderLayout.CENTER);
        newsDisplay.setHorizontalAlignment(JLabel.CENTER);
        JButton direction = new JButton("Right");
        JButton startStop = new JButton("Stop");
        JSlider speed = new JSlider(100,1000,100);
        speed.setMajorTickSpacing(100);
        speed.setPaintTicks(true);
        Hashtable labelTable = new Hashtable();
        labelTable.put(new Integer(100), new JLabel("Fast"));
        labelTable.put(new Integer(1000), new JLabel("Slow"));
        speed.setLabelTable(labelTable);
        speed.setPaintLabels(true);
        JPanel ButtonPanel = new JPanel();
        ButtonPanel.setLayout(new GridLayout(1,3));
        ButtonPanel.add(speed);
        ButtonPanel.add(direction);
        ButtonPanel.add(startStop);
        speed.setToolTipText("<html><h4>Speed</h4>Change the scroll speed.</html>");
        direction.setToolTipText("<html><h4>Direction</h4>Change the scroll direction to left or right</html>");
        startStop.setToolTipText("<html><h4>Start</h4>Use this button to start or stop the program</html>");
        radio.add(ButtonPanel,BorderLayout.SOUTH);
             
        speed.addChangeListener(new ChangeListener() 
        {

            @Override
            public void stateChanged(ChangeEvent arg0)
            {
                newsDisplay.setSpeed(speed.getValue());
            }
            
        });
        
        
        ActionListener test = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0)
            {
                JButton temp = (JButton)arg0.getSource();
                if(temp.equals(direction))
                {
                    String name = direction.getText().equals("Right") ? "Left" : "Right";
                    direction.setText(name);
                    newsDisplay.changeDirection();
                }
                if(temp.equals(startStop))
                {
                    String name = startStop.getText().equals("Stop") ? "Start" : "Stop";
                    startStop.setText(name);
                    newsDisplay.startStop();
                }
                
            }
            
        };
        
        direction.addActionListener(test);
        startStop.addActionListener(test);
        
        radio.setVisible(true);
        newsDisplay.repaint();

    }
}
