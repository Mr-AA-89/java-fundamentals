//MastermindPanel - Patka - 4/19/2014
//GUI panel for the mastermind game

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Scanner;
import javax.imageio.ImageIO;
import javax.swing.*;

public class MastermindPanel extends JPanel
{
	//objects
	private MastermindControl MasterMind = new MastermindControl();
    private File saveFile = new File("mastermind-data/mastermind_save.txt");
    
	//menu and controls
    public static JMenuBar myMenuBar;
    public JMenu menuFile, subMenuFileGame;
    public JMenuItem menuExit, menuNewGame, menuSaveGame, menuLoadGame, menuAbout;
    private JButton btnGuess, btnNewGame;
	
	//colors
    private static Color[] gameColor = {new Color(255, 0, 0), new Color(255, 255, 0), new Color(0, 0, 255), new Color(50, 205, 50), new Color(255, 165, 0), new Color(255, 255, 255)};	//red, yellow, blue, green, orange, white
    private Color[] evalColor = {new Color(255, 0, 0), new Color(255, 255, 255)};	//red = correct color, correct position; white = correct color, wrong position
    
	//graphics
    private Polygon[][] myBoard = new Polygon[12][4];
    private Polygon[][] myProgress = new Polygon[11][4];
    private Polygon[][] myEval = new Polygon[11][4];
    private Polygon[] myGuess = new Polygon[4];
    private Polygon[] mySecret = new Polygon[4];
    private Polygon[] myRules = new Polygon[6];
    private Polygon[] myAssess = new Polygon[2];
    
	//images
    private ImageIcon congratsIcon = new ImageIcon("mastermind-data/congrats.png", null);
    private ImageIcon gameOverIcon = new ImageIcon("mastermind-data/gameover.png", null);
    private BufferedImage backgroundImage, logoImage, boardImage;
    private Image scaledBackgroundImage, scaledBoardImage;
    private int g_iOldWidth, g_iOldHeight;
    
	//animation
    private int g_iLogoDeltaX = 550;
    private boolean g_bLogoRight;
	
	//data
	private String g_strEncryptionKey = "Q@G%k+l*so (s_#b";
    private int[][] g_iGuessColorIndex = new int[11][4];
    private int[][] g_iGuessResult = new int[11][2];
    
    private boolean g_bGameOver;
    private int g_iRound;
	
    /*
     * Constructor
     */
    public MastermindPanel() throws IOException, InterruptedException
    {
        //create the menu bar
        myMenuBar = new JMenuBar();
        
        //file >
        menuFile = new JMenu("File");
        menuFile.setMnemonic(KeyEvent.VK_F);
        menuFile.getAccessibleContext().setAccessibleDescription("File...");
        myMenuBar.add(menuFile);
        
        //file > game        
        subMenuFileGame = new JMenu("Game");
        subMenuFileGame.setMnemonic(KeyEvent.VK_G);
        subMenuFileGame.getAccessibleContext().setAccessibleDescription("Game...");
        menuFile.add(subMenuFileGame);
        
        //file > game > new game
        menuNewGame = new JMenuItem("New Game");
        menuNewGame.setMnemonic(KeyEvent.VK_N);
        menuNewGame.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, ActionEvent.ALT_MASK));
        menuNewGame.getAccessibleContext().setAccessibleDescription("New Game...");
        menuNewGame.addActionListener(new MenuListener());
        subMenuFileGame.add(menuNewGame);
        
        //file > game > save game
        menuSaveGame = new JMenuItem("Save Game");
        menuSaveGame.setMnemonic(KeyEvent.VK_S);
        menuSaveGame.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F2, ActionEvent.ALT_MASK));
        menuSaveGame.getAccessibleContext().setAccessibleDescription("Save Game...");
        menuSaveGame.addActionListener(new MenuListener());
        subMenuFileGame.add(menuSaveGame);
        
        //file > game > load game
        menuLoadGame = new JMenuItem("Load Game");
        menuLoadGame.setMnemonic(KeyEvent.VK_L);
        menuLoadGame.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F3, ActionEvent.ALT_MASK));
        menuLoadGame.getAccessibleContext().setAccessibleDescription("Load Game...");
        menuLoadGame.addActionListener(new MenuListener());
        subMenuFileGame.add(menuLoadGame);
        
        //file > exit
        menuFile.addSeparator();
        menuExit = new JMenuItem("Exit");
        menuExit.setMnemonic(KeyEvent.VK_E);
        menuExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, ActionEvent.CTRL_MASK));
        menuExit.getAccessibleContext().setAccessibleDescription("Quit...");
        menuExit.addActionListener(new MenuListener());
        menuFile.add(menuExit);
        
        //about
        menuAbout = new JMenuItem("About");
        menuAbout.setMnemonic(KeyEvent.VK_A);
        menuAbout.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.ALT_MASK));
        menuAbout.getAccessibleContext().setAccessibleDescription("About...");
        menuAbout.addActionListener(new MenuListener());
        myMenuBar.add(menuAbout);
        
        scaledBackgroundImage = null;
        scaledBoardImage = null;
        g_bLogoRight = false;
        g_bGameOver = false;
        g_iRound = 0;
        btnGuess = new JButton("Guess!");
        btnNewGame = new JButton("New Game");
        
        //images and error handling
        File imageFile;
        String strError = new String("The following files are missing from the program's directory:\n");
        String strFile[] = {"mastermind-data/background.jpg", "mastermind-data/board.jpg", "mastermind-data/logo.png", "mastermind-data/congrats.png", "mastermind-data/gameover.png"};
        boolean bError = false;
        
        for (int i = 0; i < strFile.length; i++)
        {
            imageFile = new File(strFile[i]);
            if (!imageFile.exists() && !imageFile.isDirectory())
            {
                bError = true;
                strError += strFile[i] + "\n";
            }
        }
        
        if (bError)
        {
            JOptionPane.showMessageDialog(null, strError, "ERROR", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
        
        try
        {
            backgroundImage = ImageIO.read(new File("mastermind-data/background.jpg"));
            boardImage = ImageIO.read(new File("mastermind-data/board.jpg"));
            logoImage = ImageIO.read(new File("mastermind-data/logo.png"));
        }
        catch(IOException e)
        {
            System.exit(0);
        }

        //logo
        Timer logoTimer = new Timer(10, new LogoTimerListener());
        logoTimer.start();
        
        //game board
        int i, j;
        for (i = 0; i < myBoard.length; i++)
        {
            for (j = 0; j < myBoard[i].length; j++)
            {
                myBoard[i][j] = makePeg((50 * j) + 100, (75 * i) + 100, 25, 0);
            }
        }
        
        for (i = 0; i < myProgress.length; i++)
        {
            for (j = 0; j < myProgress[i].length; j++)
            {
                myProgress[i][j] = makePeg((50 * j) + 100, (75 * i) + 175, 25, 1);
            }
        }
        
        for (i = 0; i < myEval.length; i++)
        {
            for (j = 0; j < myEval[i].length; j++)
            {
                myEval[i][j] = makePeg((50 * j) + 325, (75 * i) + 175, 25, 1);
            }
        }
        
        for (i = 0; i < myGuess.length; i++)
        {
            myGuess[i] = makePeg((50 * i) + 100, (75 * (myBoard.length - 1)) + 100, 25, 1);
        }
        
        for (i = 0; i < mySecret.length; i++)
        {
            mySecret[i] = makePeg((50 * i) + 100, 100, 25, 1);
        }
        
        for (i = 0; i < myRules.length; i++)
        {
            myRules[i] = makePeg((50 * i) + 925, 300, 25, 1);
        }
        
        for (i = 0; i < myAssess.length; i++)
        {
            myAssess[i] = makePeg(800, 650 + (50 * i), 25, 1);
        }
        
        for (i = 0; i < g_iGuessColorIndex.length; i++)
        {
            for (j = 0; j < g_iGuessColorIndex[i].length; j++)
            {
                g_iGuessColorIndex[i][j] = 0;
            }
        }
        
        for (i = 0; i < g_iGuessResult.length; i++)
        {
            for (j = 0; j < g_iGuessResult[i].length; j++)
            {
                g_iGuessResult[i][j] = 0;
            }
        }
        
        //action listeners
        SelectionListener selListener = new SelectionListener();
        btnGuess.addActionListener(selListener);
        btnNewGame.addActionListener(selListener);
        
        MouseActionListener MouseListener = new MouseActionListener();
        addMouseListener(MouseListener);
        
        //design
        setLayout(null);
        
        btnGuess.setBounds(300, 900, 100, 25);
        btnNewGame.setBounds(300, 950, 100, 25);
        add(btnGuess);
        add(btnNewGame);

        tryLoadGame(true);
    }
	 
	/*
	 * Make a game peg.
	 *
	 * @param defPointX   		Integer starting x coordinate.
	 * @param defPointY   		Integer starting y coordinate.
	 * @param size   			Integer length of a side.
	 * @param offset			Integer offset from size.
	 * @return                  Polygon game peg.
	 */
    public Polygon makePeg(int defPointX, int defPointY, int size, int offset)
    {
        int[] xPeg = {defPointX + offset,   defPointX + size,  defPointX + size,            defPointX + offset};
        int[] yPeg = {defPointY,            defPointY,         defPointY - size + offset,   defPointY - size + offset};
        
        return new Polygon(xPeg, yPeg, xPeg.length);
    }
    
    /*
     * Paint graphics on the screen
     */
    public void paintComponent(Graphics thing)
    {
        int i, j, count, redCount, whiteCount;
        super.paintComponent(thing);
        
        //background
        int newWidth = getWidth();
        int newHeight = getHeight();
        
        if (scaledBackgroundImage == null || newWidth != g_iOldWidth || newHeight != g_iOldHeight)
        {
            scaledBackgroundImage = backgroundImage.getScaledInstance(newWidth, newHeight, backgroundImage.TYPE_INT_ARGB);
            g_iOldWidth = newWidth;
            g_iOldHeight = newHeight;
        }
        thing.drawImage(scaledBackgroundImage, 0, 0, null);
        
        //logo
        thing.drawImage(logoImage, g_iLogoDeltaX, 0, null);
        
        //board
        thing.drawImage(boardImage, 75, 50, null);
        
        thing.setColor(new Color(0, 0, 0));
        for (i = 0; i < myBoard.length; i++)
        {
            for (j = 0; j < myBoard[i].length; j++)
            {
                thing.drawPolygon(myBoard[i][j]);
            }
        }
        
        //top divider
        thing.setColor(new Color(0, 0, 255));
        int[] xTopDivider = {100, 275};
        int[] yTopDivider = {125, 125};
        thing.drawPolyline(xTopDivider, yTopDivider, xTopDivider.length);
        
        //bottom divider
        thing.setColor(new Color(0, 0, 255));
        int[] xBottomDivider = {100, 275};
        int[] yBottomDivider = {875, 875};
        thing.drawPolyline(xBottomDivider, yBottomDivider, xBottomDivider.length);
        
        //right side divider
        thing.setColor(new Color(0, 0, 255));
        int[] xRightDivider = {300, 300};
        int[] yRightDivider = {150, 850};
        thing.drawPolyline(xRightDivider, yRightDivider, xRightDivider.length);
        
        //guess box
        for (i = 0; i < myGuess.length; i++)
        {
            thing.setColor(gameColor[g_iGuessColorIndex[g_iRound][i]]);
            thing.fillPolygon(myGuess[i]);
        }
        
        //secret code
        thing.setColor(Color.gray);
        for (i = 0; i < 4; i++)
        {
            thing.fillPolygon(mySecret[i]);
        }
        
        //reverse the progress array
        count = 0;
        Polygon[][] myReverseProgress = new Polygon[myProgress.length][4];
        for (i = myProgress.length - 2; i >= 0; i--)
        {
            for (j = 0; j < myProgress[i].length; j++)
            {
                myReverseProgress[count][j] = myProgress[i][j];
            }
            
            count++;
        }
        
        //display the progress
        for (i = 0; i < g_iRound; i++)
        {
            for (j = 0; j < myReverseProgress[i].length; j++)
            {
                thing.setColor(gameColor[g_iGuessColorIndex[i][j]]);
                thing.fillPolygon(myReverseProgress[i][j]);
            }
        }
        
        //reverse the eval array
        count = 0;
        Polygon[][] myReverseEval = new Polygon[myEval.length][4];
        for (i = myEval.length - 2; i >= 0; i--)
        {
            for (j = 0; j < myEval[i].length; j++)
            {
                myReverseEval[count][j] = myEval[i][j];
            }
            
            count++;
        }
        
        //display the evaluation
        for (i = 0; i < g_iRound; i++)
        {
            redCount = 0;
            whiteCount = 0;
            
            for (j = 0; j < myReverseEval[i].length; j++)
            {
                //red pegs
                if (redCount < g_iGuessResult[i][0])
                {
                    thing.setColor(evalColor[0]);
                    thing.fillPolygon(myReverseEval[i][j]);
                    redCount++;
                }
                else if (whiteCount < g_iGuessResult[i][1])
                {
                    thing.setColor(evalColor[1]);
                    thing.fillPolygon(myReverseEval[i][j]);
                    whiteCount++;
                }
            }
        }
        
        //display the rules
        thing.setColor(Color.black);
        thing.setFont(new Font("TimesRoman", Font.BOLD, 20));
        thing.drawString("How To Play Mastermind", 800, 250);
        thing.drawString("The Colors:", 800, 300);
        
        for (i = 0; i < myRules.length; i++)
        {
            thing.setColor(gameColor[i]);
            thing.fillPolygon(myRules[i]);
        }
        
        thing.setColor(Color.black);
        thing.drawString("The Rules:", 800, 350);
        thing.setFont(new Font("TimesRoman", Font.PLAIN, 18));
        thing.drawString("The computer has randomly generated a secret code using any combination of four of the above colors.", 800, 400);
        thing.drawString("The object of the game is to guess the computer's secret code in 10 or less tries.", 800, 450);
        
        thing.drawString("To guess the code, click on the bottom four pegs to change their colors.", 800, 500);
        thing.drawString("Once satisfied with the combination, click the guess button to cast your guess.", 800, 550);
        
        thing.drawString("After each guess, the computer assesses the guess using the following pegs:", 800, 600);
        thing.drawString("is given for each correct color guessed in the correct position.", 850, 650);
        thing.drawString("is given for each correct color guessed in an incorrect position.", 850, 700);
        
        for (i = 0; i < myAssess.length; i++)
        {
            thing.setColor(evalColor[i]);
            thing.fillPolygon(myAssess[i]);
        }
        
        //show the answer
        if (g_bGameOver)
        {
            int[] secretCode = MasterMind.getSecretCode();
            
            for (i = 0; i < secretCode.length; i++)
            {
                thing.setColor(gameColor[secretCode[i]]);
                thing.fillPolygon(mySecret[i]);
            }
        }
    }
    
    /*
     * Mouse action events
     */
    private class MouseActionListener implements MouseListener, MouseMotionListener
    {
        public void mouseClicked (MouseEvent event)
        {
            Point mousePos = event.getPoint();
            
            if (mousePos != null)
            {
                for (int i = 0; i < myGuess.length; i++)
                {
                    if (myGuess[i].contains(mousePos))
                    {
                        if (g_iGuessColorIndex[g_iRound][i] == gameColor.length - 1)
                        {
                            g_iGuessColorIndex[g_iRound][i] = 0;
                        }
                        else
                        {
                            g_iGuessColorIndex[g_iRound][i]++;
                        }
						
                        repaint();
                    }
                }
            }
        }
        
        public void mouseMoved (MouseEvent event){}
        public void mouseDragged (MouseEvent event){}
        public void mousePressed (MouseEvent event){}
        public void mouseReleased (MouseEvent event){}
        public void mouseEntered (MouseEvent event){}
        public void mouseExited (MouseEvent event){}
    }
    
    /*
     * Button action events.
     */
    private class SelectionListener implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
            Object source = event.getSource();
            
            if (source == btnGuess)
            {
                if (!g_bGameOver && g_iRound < myProgress.length - 1)
                {
                    g_iGuessResult[g_iRound] = MasterMind.guess(g_iGuessColorIndex[g_iRound]);
                    repaint();
                    
                    if (g_iGuessResult[g_iRound][0] == 4)
                    {
                        g_bGameOver = true;
                        g_iRound++;
                        repaint();
                        JOptionPane.showMessageDialog(null, "You guessed the secret code", "You Win!", JOptionPane.INFORMATION_MESSAGE, congratsIcon);
                    }
                    else
                    {
                        if (g_iRound == 9)
                        {
                            g_bGameOver = true;
                            g_iRound++;
                            repaint();
                            JOptionPane.showMessageDialog(null, "You lost. Better luck next time.", "You Loose", JOptionPane.INFORMATION_MESSAGE, gameOverIcon);
                        }
                        else
                        {
                            repaint();
                            g_iRound++;
                        }
                    }
                }
            }
            else if (source == btnNewGame)
            {
                MasterMind.resetGame();
                resetGame();
            }
        }
    }
    
    /*
     * Menu action events
     */
    private class MenuListener implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
            Object source = event.getSource();
            
            if (source == menuNewGame)
            {
                MasterMind.resetGame();
                resetGame();
            }
            else if (source == menuSaveGame)
            {
                try
                {
                    saveGame();
                    JOptionPane.showMessageDialog(null, "The game has been saved to the following file:" + "\n" + saveFile, "Game Saved", JOptionPane.INFORMATION_MESSAGE);
                }
                catch (Exception e)
                {
                    JOptionPane.showMessageDialog(null, "Failed to save the game to the following file: " + "\n" + saveFile, "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            }
            else if (source == menuLoadGame)
            {
                tryLoadGame(false);
            }
            else if (source == menuExit)
            {
                System.exit(0);
            }
            else if (source == menuAbout)
            {
                JOptionPane.showMessageDialog(null, "A code-breaking game where you try to guess the computer's secret code" + "\n" + "Created by Patrick Kubiak", "About Mastermind", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    private void tryLoadGame(boolean noBroadcast)
    {
        try
        {
            loadGame();
            if (!noBroadcast)
            {
                JOptionPane.showMessageDialog(null, "The game has been loaded from the following file:" + "\n" + saveFile, "Game Loaded", JOptionPane.INFORMATION_MESSAGE);
            }
        }
        catch (Exception e)
        {
            if (!noBroadcast)
            {
                JOptionPane.showMessageDialog(null, "Failed to load the game from the following file: " + "\n" + saveFile, "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
	/*
     * Timer callback for logo
     */
    private class LogoTimerListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
           if (g_iLogoDeltaX == 550)
           {
               g_bLogoRight = true;
           }
           else if(g_iLogoDeltaX == (getWidth() - logoImage.getWidth()))
           {
              g_bLogoRight = false; 
           }
            
           if (g_bLogoRight)
           {
               g_iLogoDeltaX++;
           }
           else
           {
               g_iLogoDeltaX--;
           }
           
           repaint();
        }
    }
    
	/*
     * Save the game.
     */
    private void saveGame() throws Exception
    {
        try
        {
            String strSecretCode = new String("");
            String strRound = Integer.toString(g_iRound);
            
            String[][] strGuessColorIndexes = new String[11][4];
            String[][] strGuessResults = new String[11][2];
            
            String strEncryptedSecretCode;
            String strEncryptedRound;
            
            int[] secretCode = MasterMind.getSecretCode();
            int length, length2;
            int i, j;
            
            
            //prepare the data for encryption
            
            //secret code
            for (i = 0; i < secretCode.length; i++)
            {
                strSecretCode += Integer.toString(secretCode[i]);
            }
            
            length = strSecretCode.length();
            for (i = 0; i < 16 - length; i++)
            {
                strSecretCode += "\0";
            }
            
            //round
            length = strRound.length();
            for (i = 0; i < 16 - length; i++)
            {
                strRound += "\0";
            }
            
            
            //encrypt the data
            strEncryptedSecretCode = MastermindEncryptedSave.encrypt(strSecretCode, g_strEncryptionKey);
            strEncryptedRound = MastermindEncryptedSave.encrypt(strRound, g_strEncryptionKey);
            
            //write the data
            PrintWriter outputFile = new PrintWriter(saveFile);
            
            //write encrypted secret code
            outputFile.println(strEncryptedSecretCode);
            
            //write encrypted round count
            outputFile.println(strEncryptedRound);
            
            
            //write regular data
            
            //write guess color
            for (i = 0; i < g_iGuessColorIndex.length; i++)
            {
                for (j = 0; j < g_iGuessColorIndex[i].length; j++)
                {
                    strGuessColorIndexes[i][j] = Integer.toString(g_iGuessColorIndex[i][j]);
                    outputFile.print(strGuessColorIndexes[i][j]);
                }
                outputFile.println();
            }
            
            //guess result
            for (i = 0; i < strGuessResults.length; i++)
            {
                for (j = 0; j < strGuessResults[i].length; j++)
                {
                    strGuessResults[i][j] = Integer.toString(g_iGuessResult[i][j]);
                    outputFile.print(strGuessResults[i][j]);
                }
                outputFile.println();
            }
            
            
            //clear out the data and close file to access it
            outputFile.flush();
            outputFile.close();
        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, e, "Error Saving Game", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
	/*
     * Load the saved game.
     */
    private void loadGame() throws Exception
    {
        try
        {
			resetGame();
            int i, j, count;
            Scanner loadInput = new Scanner(saveFile);
            
            //load encrypted secret code and round
            String strEncryptedSecretCode = new String("");
            strEncryptedSecretCode = loadInput.nextLine();
            
            String strEncryptedRounds = new String("");
            strEncryptedRounds = loadInput.nextLine();
            
            //decrypt secret code and round
            String strSecretCode = new String("");
            String strRounds = new String("");
            strSecretCode = MastermindEncryptedSave.decrypt(strEncryptedSecretCode, g_strEncryptionKey);
            strRounds = MastermindEncryptedSave.decrypt(strEncryptedRounds, g_strEncryptionKey);
            
            //set round and secret code
            strRounds = strRounds.trim();
            g_iRound = Integer.parseInt(strRounds);
            
            int[] secretCode = new int[4];
            for (i = 0; i < secretCode.length; i++)
            {
                secretCode[i] = Integer.parseInt(String.valueOf(strSecretCode.charAt(i)));
            }
            MasterMind.setSecretCode(secretCode);
            
            //set progress
            String strGuessColorIndex;
            count = 0;
            strGuessColorIndex = loadInput.nextLine();
            
            do
            {
                for (i = 0; i < 4; i++)
                {
                    g_iGuessColorIndex[count][i] = Integer.parseInt(String.valueOf(strGuessColorIndex.charAt(i)));
                }
                
                strGuessColorIndex = loadInput.nextLine();
                count++;
            }
			while(strGuessColorIndex.length() == 4 && loadInput.hasNext());
            
            //set guess results
            String strGuessResults;
            count = 0;
            strGuessResults = strGuessColorIndex;
            
            while(strGuessResults.length() == 2 && loadInput.hasNext())
            {
                for (i = 0; i < 2; i++)
                {
                    g_iGuessResult[count][i] = Integer.parseInt(String.valueOf(strGuessResults.charAt(i)));
                }
                
                strGuessResults = loadInput.nextLine();
                count++;
            }
            
            repaint();
        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, e, "Error Loading Game", JOptionPane.INFORMATION_MESSAGE);
        }
        
        repaint();
    }
    
	/*
     * Reset the game.
     */
    private void resetGame()
    {        
        g_bLogoRight = false;
        g_bGameOver = false;
        g_iRound = 0;
        
        int i, j;
        for (i = 0; i < g_iGuessColorIndex.length; i++)
        {
            for (j = 0; j < g_iGuessColorIndex[i].length; j++)
            {
                g_iGuessColorIndex[i][j] = 0;
            }
        }
        
        for (i = 0; i < g_iGuessResult.length; i++)
        {
            for (j = 0; j < g_iGuessResult[i].length; j++)
            {
                g_iGuessResult[i][j] = 0;
            }
        }
        
        repaint();
    }
}
