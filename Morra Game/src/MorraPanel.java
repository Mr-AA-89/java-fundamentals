//Morra Panel Class - Patka - 3/31/2014
//Panel to play a game of Morra against the computer.

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MorraPanel extends JPanel
{
    private JRadioButton radFingers1, radFingers2, radFingers3, radFingers4, radFingers5; //radio button to decide number of fingers to throw
    private ButtonGroup grpFingers;                             //group for radio buttons
    private JLabel lblGuessTotal, lblBlank;                     //label for prediction of total and spacing
    private JTextField txtGuessTotal;                           //textbox for prediction of total
    private JButton btnThrowFingers, btnDisplayRules;           //button to throw fingers and display rule book
    private String strNumRounds;                                //string number of rounds
    private int rounds, roundsPlayed;                           //number of rounds
	private Morra thisMorraGame = new Morra();					//morra object for game
    
    //constructor
    public MorraPanel()
    {
        //setup game + panel
        
        //create radio buttons and group
        radFingers1 = new JRadioButton("Throw 1 Fingers");
        radFingers2 = new JRadioButton("Throw 2 Fingers");
        radFingers3 = new JRadioButton("Throw 3 Fingers");
        radFingers4 = new JRadioButton("Throw 4 Fingers");
        radFingers5 = new JRadioButton("Throw 5 Fingers");
        grpFingers = new ButtonGroup();
        
        //add radio buttons to group
        grpFingers.add(radFingers1);
        grpFingers.add(radFingers2);
        grpFingers.add(radFingers3);
        grpFingers.add(radFingers4);
        grpFingers.add(radFingers5);
        
        //create label for textbox
        lblGuessTotal = new JLabel("Guess Total: ");
        lblBlank = new JLabel("                  ");
        
        //create textbox
        txtGuessTotal = new JTextField(2);
        
        //create buttons
        btnThrowFingers = new JButton("Throw!");
        btnDisplayRules = new JButton("Rule Book");
        
        //setup action listeners
        SelectionListener selListener = new SelectionListener();
        btnThrowFingers.addActionListener(selListener);
        btnDisplayRules.addActionListener(selListener);
        
        //add content to panel
        add(lblGuessTotal);
        add(txtGuessTotal);
        add(lblBlank);
        add(radFingers1);
        add(radFingers2);
        add(radFingers3);
        add(radFingers4);
        add(radFingers5);
        add(btnThrowFingers);
        add(btnDisplayRules);
        
        //setup look and feel of panel
        setPreferredSize(new Dimension(250, 150));
        setBackground(Color.cyan);
        
        //default selection for fingers to throw
        radFingers1.setSelected(true);
        
        startMorraGame();
    }
    
    public void startMorraGame()
    {
        rounds = 0;
        
        do
        {
            strNumRounds = JOptionPane.showInputDialog("Enter an odd number of rounds to play (minimum 3):");
            if (Morra.isInteger(strNumRounds))              //if input of rounds to play is an integer
            {
                rounds = Integer.parseInt(strNumRounds);
            }
        }
        while ((rounds < 3) || ((rounds % 2) == 0));        //while rounds is less than 3, or rounds is even keep asking
        
        thisMorraGame.resetMorraGame(rounds);
    }
    
    //listener for all selection
    private class SelectionListener implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
            Object source = event.getSource();
            
            if (source == btnThrowFingers)
            {
                int roundWinner, computerFingers, computerTotal, playerFingers, playerTotal;
                int total, winner, winsNeeded, again;
                String strResult = "", strTotal = txtGuessTotal.getText();
                
                if (Morra.isInteger(strTotal))
                {
                    total = Integer.parseInt(strTotal);
                    thisMorraGame.takeComputerMove();
                    
                    if (radFingers1.isSelected())
                    {
                        thisMorraGame.takePlayerMove(1, total);         //takePlayerMove(int numFingers, int numTotal)
                    }
                    else if (radFingers2.isSelected())
                    {
                        thisMorraGame.takePlayerMove(2, total);
                    }
                    else if (radFingers3.isSelected())
                    {
                        thisMorraGame.takePlayerMove(3, total);
                    }
                    else if (radFingers4.isSelected())
                    {
                        thisMorraGame.takePlayerMove(4, total);
                    }
                    else if (radFingers5.isSelected())
                    {
                        thisMorraGame.takePlayerMove(5, total);
                    }
                    
                    roundWinner = thisMorraGame.getRoundWinner();
                    computerFingers = thisMorraGame.getComputerFingers();
                    computerTotal = thisMorraGame.getComputerTotal();
                    playerFingers = thisMorraGame.getPlayerFingers();
                    playerTotal = thisMorraGame.getPlayerTotal();
					winsNeeded = thisMorraGame.getWinsNeeded();
                    
                    switch (roundWinner)
                    {
                        case 0:
                        {
                            strResult = "The Computer Wins The Round!\n";
                            break;
                        }
                        
                        case 1:
                        {
                            strResult = "The Player Wins The Round!\n";
                            break;
                        }
                        
                        case 2:
                        {
                            strResult = "The Round Ended In A Tie.\n";
                            break;
                        }
                        
                        default:
                        {
                            strResult = "No one wins the round.\n";
                            break;
                        }
                    }
                    
                    strResult += "\nCurrent Score: Player - " + thisMorraGame.getPlayerScore() + " Computer - " + thisMorraGame.getComputerScore();
                    strResult +="\nFirst player to win " + winsNeeded + " rounds wins the match";

                    strResult += "\n\nThe player threw " + playerFingers;
                    strResult += (playerFingers == 1) ? (" finger "):(" fingers ");
                    strResult +=  "and guessed the total to be " + playerTotal;
                    
                    strResult += "\nThe computer threw " + computerFingers;
                    strResult += (computerFingers == 1) ? (" finger "):(" fingers ");
                    strResult += "and guessed the total to be " + computerTotal;
                    
                    strResult +="\nThe total was " + thisMorraGame.getRoundTotal();
                    
                    JOptionPane.showMessageDialog(null, strResult);
                    thisMorraGame.resetRoundWinner();
                    
                    winner = thisMorraGame.getGameWinner();
                    if (winner != -1)                   //a winner of the game has been determined
                    {
                        switch (winner)
                        {
                            case 0:
                            {
                                strResult = "The Computer Won The Match!";
                                break;
                            }
                            
                            case 1:
                            {
                                strResult = "The Player Won The Match!";
                                break;
                            }
                        }
                        
                        strResult +="\nScore: Player - " + thisMorraGame.getPlayerScore() + " Computer - " + thisMorraGame.getComputerScore();
                        strResult +="\n\nDo you want to play again?";
                        
                        again = JOptionPane.showConfirmDialog(null, strResult);
                        if (again == JOptionPane.YES_OPTION)
                        {
                            startMorraGame();
                        }
                        else
                        {
                            System.exit(0);
                        }
                    }
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Error: Please enter a valid total.");
                }
            }
            
            if (source == btnDisplayRules)
            {
                //display rules panel
                JFrame rulesFrame = new JFrame("Rule Book");
                rulesFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);       //exit when last frame is finished
                rulesFrame.setResizable(false);
                rulesFrame.getContentPane().add(new RulesPanel());
                rulesFrame.pack();
                rulesFrame.setVisible(true);
            }
        }
    }
}
