//Mastermind Class - Patka - 4/19/2014
//Play a game of Mastermind against the computer

import java.io.*;
import javax.swing.JFrame;

public class Mastermind
{
    public static void main(String[] args) throws IOException, InterruptedException
    {
        //create the main frame
        JFrame mastermindFrame = new JFrame("Mastermind");
        mastermindFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mastermindFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        mastermindFrame.setUndecorated(true);
        
        //add the mastermind panel and display the main frame
        MastermindPanel mastermindPanel = new MastermindPanel();
        mastermindFrame.getContentPane().add(mastermindPanel);
        mastermindFrame.setJMenuBar(MastermindPanel.myMenuBar);
        mastermindFrame.pack();
        mastermindFrame.setResizable(false);
        mastermindFrame.setVisible(true);
    }
}
