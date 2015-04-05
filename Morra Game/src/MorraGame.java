//Morra Game - Patka - 3/31/2014
//Play a game of Morra against the computer.

import javax.swing.JFrame;

public class MorraGame
{
    public static void main(String[] args)
    {
        //create and display the main frame
        JFrame morraFrame = new JFrame("Morra Game");
        morraFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        morraFrame.setResizable(false);
        morraFrame.getContentPane().add(new MorraPanel());
        morraFrame.pack();
        morraFrame.setVisible(true);
    }
}
