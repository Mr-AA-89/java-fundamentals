//Game Of Life Panel Class - Patrick Kubiak - 10/15/2014
//Graphical components of the Game Of Life.

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GameOfLifePanel extends JPanel
{
    //menu
    public static JMenuBar myMenuBar;
    public JMenu menuFile;
    public JMenuItem menuNewGame, menuExit;
    
    //controls
    private JButton btnNewGame, btnNextGen;
    
    //graphics
    private Polygon[][] myBoard = new Polygon[10][20];
    private Polygon[][] myPegs = new Polygon[10][20];
    private Boolean[][] bBoard = new Boolean[10][20];
    
    /**
     * Constructor.
     */
    public GameOfLifePanel()
    {
        //create the menu bar
        myMenuBar = new JMenuBar();
        
        //file >
        menuFile = new JMenu("File");
        menuFile.setMnemonic(KeyEvent.VK_F);
        menuFile.getAccessibleContext().setAccessibleDescription("File...");
        myMenuBar.add(menuFile);
        
        //file > new game
        menuNewGame = new JMenuItem("New Game");
        menuNewGame.setMnemonic(KeyEvent.VK_N);
        menuNewGame.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F2, ActionEvent.CTRL_MASK));
        menuNewGame.getAccessibleContext().setAccessibleDescription("New Game...");
        menuNewGame.addActionListener(new MenuListener());
        menuFile.add(menuNewGame);
        menuFile.addSeparator();
        
        //file > exit
        menuExit = new JMenuItem("Exit");
        menuExit.setMnemonic(KeyEvent.VK_E);
        menuExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, ActionEvent.CTRL_MASK));
        menuExit.getAccessibleContext().setAccessibleDescription("Quit...");
        menuExit.addActionListener(new MenuListener());
        menuFile.add(menuExit);
        
        //game board
        int i, j;
        for (i = 0; i < myBoard.length; i++)
        {
            for (j = 0; j < myBoard[i].length; j++)
            {
                myBoard[i][j] = makePeg((50 * j) + 100, (50 * i) + 100, 25, 0);
            }
        }
        
        //game pegs
        for (i = 0; i < myPegs.length; i++)
        {
            for (j = 0; j < myPegs[i].length; j++)
            {
                myPegs[i][j] = makePeg((50 * j) + 100, (50 * i) + 100, 25, 1);
            }
        }
        
        //buttons
        btnNewGame = new JButton("New Game");
        btnNextGen = new JButton("Show Next Generation");
        
        //action listeners
        SelectionListener selListener = new SelectionListener();
        btnNewGame.addActionListener(selListener);
        btnNextGen.addActionListener(selListener);
        
        MouseActionListener MouseListener = new MouseActionListener();
        addMouseListener(MouseListener);
        
        //design
        resetBoard();
        setLayout(null);
        
        btnNewGame.setBounds((50 * (myPegs[0].length - 1)), (50 * myPegs.length) + 100, 100, 25);
        btnNextGen.setBounds((50 * (myPegs[0].length/2)), (50 * myPegs.length) + 100, 200, 25);
        add(btnNewGame);
        add(btnNextGen);
    }
    
    /**
     * Paint graphics on the screen.
     */
    public void paintComponent(Graphics thing)
    {
        int i, j;
        super.paintComponent(thing);
        
        //game board        
        thing.setColor(new Color(0, 0, 0));
        for (i = 0; i < myBoard.length; i++)
        {
            for (j = 0; j < myBoard[i].length; j++)
            {
                thing.drawPolygon(myBoard[i][j]);
            }
        }
        
        //game pegs
        thing.setColor(new Color(0, 255, 0));
        for (i = 0; i < myPegs.length; i++)
        {
            for (j = 0; j < myPegs[i].length; j++)
            {
                if (bBoard[i][j])
                {
                    thing.fillPolygon(myPegs[i][j]);    //peg is alive so fill it
                }
            }
        }
        
        //rules
        thing.setColor(Color.blue);
        thing.setFont(new Font("TimesRoman", Font.BOLD, 20)); //myBoard[0].length
        thing.drawString("The Game of Life", 350, 20);
        thing.drawString("Click on the cells to change their state from dead to alive and visa-versa.", 150, 40);
        thing.drawString("Once statisfied with the configuration, use the button to show the next generation.", 150, 60);
    }
    
    /**
     * Menu action events.
     */
    private class MenuListener implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
            Object source = event.getSource();
            
            if (source == menuNewGame)
            {
                resetBoard();
                repaint();
            }
            else if (source == menuExit)
            {
                System.exit(0);
            }
        }
    }
    
    /**
     * Button action events.
     */
    private class SelectionListener implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
            Object source = event.getSource();
            
            if (source == btnNewGame)
            {
                resetBoard();
                repaint();
            }
            
            if (source == btnNextGen)
            {
                Boolean[][] tempBoard = GameOfLife.computeNextGen(bBoard);
                bBoard = tempBoard;
                repaint();
            }
        }
    }
    
    /**
     * Mouse action events
     */
    private class MouseActionListener extends MouseAdapter
    {
        public void mouseClicked (MouseEvent event)
        {
            Point mousePos = event.getPoint();
            
            if (mousePos != null)
            {
                for (int i = 0; i < myPegs.length; i++)
                {
                    for (int j = 0; j < myPegs[i].length; j++)
                    {
                        if (myPegs[i][j].contains(mousePos))
                        {
                            bBoard[i][j] = !bBoard[i][j]; //user clicked on peg so change its state
                        }
                    }
                }
                
                repaint();
            }
        }
    }
    
    /**
     * Reset the game board.
     */
    public void resetBoard()
    {
        for (int i = 0; i < bBoard.length; i++)
        {
            for (int j = 0; j < bBoard[i].length; j++)
            {
                bBoard[i][j] = false;
            }
        }
    }
    
    /**
	 * Make a game peg.
	 *
	 * @param centerX   		Integer center x coordinate.
	 * @param centerY   		Integer center y coordinate.
	 * @param size   			Integer length of a side.
	 * @param offset			Integer offset from size.
	 * @return                  Polygon game peg.
	 */
    public Polygon makePeg(int centerX, int centerY, int size, int offset)
    {
        int half = (int)(size / 2);
        int[] xPeg = {centerX - half + offset,      centerX + half,    centerX + half - offset,    centerX - half + offset};
        int[] yPeg = {centerY - half + offset,      centerY - half,    centerY + half,             centerY + half};
        
        return new Polygon(xPeg, yPeg, xPeg.length);
    }
}
