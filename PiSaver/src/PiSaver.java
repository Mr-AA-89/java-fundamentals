//PiSaver Class - Patka - 4/27/2014
//A java screensaver about pi.

import java.io.*;
import javax.swing.JFrame;

public class PiSaver
{
    static JFrame frame;
    
    public static void main(String[] args) throws IOException
    {
        //create frame
        frame = new JFrame("Screensaver");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setUndecorated(true);
        
        PiSaverPanel panel = new PiSaverPanel();
        frame.getContentPane().add(panel);
        frame.pack();
        frame.setResizable(false);
        frame.setVisible(true);
    }
    
    public static int getFrameWidth()
    {
        return frame.getWidth();
    }
    
    public static int getFrameHeight()
    {
        return frame.getHeight();
    }
}
