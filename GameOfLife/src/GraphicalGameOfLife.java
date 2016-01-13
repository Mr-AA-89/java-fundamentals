//Graphical Game Of Life Class - Patrick Kubiak - 10/15/2014
//Graphical version of the Game of Life.

import javax.swing.JFrame;

public class GraphicalGameOfLife
{
    public static void main(String[] args)
    {
        //create the main frame
        JFrame gameOfLifeFrame = new JFrame("Game of Life");
        gameOfLifeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameOfLifeFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        gameOfLifeFrame.setUndecorated(true);
        
        //add the game of life panel and display the main frame
        GameOfLifePanel gameOfLifePanel = new GameOfLifePanel();
        gameOfLifeFrame.getContentPane().add(gameOfLifePanel);
        gameOfLifeFrame.setJMenuBar(gameOfLifePanel.myMenuBar);
        gameOfLifeFrame.pack();
        gameOfLifeFrame.setResizable(false);
        gameOfLifeFrame.setVisible(true);
    }
}
