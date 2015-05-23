//Rules Panel - Patka - 4/3/2014
//Display the rules of the Morra game.

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class RulesPanel extends JPanel
{
    private JLabel lblRuleHeader1, lblRuleHeader2, lblRuleHeader3;     //labels for rule headings
    private JLabel lblRule1, lblRule2, lblRule3, lblRule4;             //labels to display the rules
    private JButton btnNext, btnPrevious;                              //buttons to navigate rule book
    private String strRuleHeader1, strRuleHeader2, strRuleHeader3;     //strings to store rule headings
    private String strRule1, strRule2, strRule3, strRule4, strRule5,   //strings to store rules
                   strRule6, strRule7, strRule8, strRule9;
    private int iPage;                                                 //current page in rule book
    
    //constructor
    public RulesPanel()
    {
        //definitions
        iPage = 1;
        
        //create rule headings
        strRuleHeader1 = "Number of Rounds";
        strRuleHeader2 = "Play a Round";
        strRuleHeader3 = "Evaluation";
        
        //create rules
        strRule1 = "The number of rounds in a game follows the 'best-of' format";
        strRule2 = "To play a round, each competitor chooses two things:";
        strRule3 = "1. A number of fingers to 'throw out'";
        strRule4 = "2. A total number that includes their fingers and";
        strRule5 = "their prediction of what the opponent will throw";
        strRule6 = "A round continues until only one person has guessed";
        strRule7 = "the total correctly. That person wins the round, and the";
        strRule8 = "match continues until someone has won a";
        strRule9 = " majority of rounds.";
        
        //create labels for rule headings
        lblRuleHeader1 = new JLabel(strRuleHeader1);
        lblRuleHeader2 = new JLabel("");
        lblRuleHeader3 = new JLabel("");
        
        //create labels for rules
        lblRule1 = new JLabel(strRule1);
        lblRule2 = new JLabel("");
        lblRule3 = new JLabel("");
        lblRule4 = new JLabel("");
        
        //create navigation buttons
        btnNext = new JButton("Next Page");
        btnPrevious = new JButton("Previous Page");
        
        //setup action listeners
        SelectionListener selListener = new SelectionListener();
        btnNext.addActionListener(selListener);
        btnPrevious.addActionListener(selListener);
        
        //add rules to panel
        add(lblRuleHeader1);
        add(lblRuleHeader2);
        add(lblRuleHeader3);
        add(lblRule1);
        add(lblRule2);
        add(lblRule3);
        add(lblRule4);
        add(btnPrevious);
        add(btnNext);
        
        //setup look of panel
        btnPrevious.setVisible(false);
        setPreferredSize(new Dimension(350, 150));
        setBackground(Color.cyan);
    }
    
    //listener for all selection
    private class SelectionListener implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
            Object source = event.getSource();
            
            if ((source == btnNext) || (source == btnPrevious))
            {
                resetAllPages();
                
                if (source == btnNext)
                {
                    if (iPage == 1 || iPage == 2)
                    {
                       iPage++;
                    }
                }
                
                if (source == btnPrevious)
                {
                    if (iPage == 2 || iPage == 3)
                    {
                       iPage--;
                    }
                }
                
                setPage(iPage);
            }
        }
        
		//clear all text
        public void resetAllPages()
        {
            //reset headings
            lblRuleHeader1.setText("");
            lblRuleHeader2.setText("");
            lblRuleHeader3.setText("");
            
            //reset rules
            lblRule1.setText("");
            lblRule2.setText("");
            lblRule3.setText("");
            lblRule4.setText("");
        }
        
		/**
		 * Display a specific page of the rule book.
		 * @param page integer page to display (1, 2, or 3)
		 */
        public void setPage(int page)
        {
            resetAllPages();
            
            switch (page)
            {
                case 1:
                {
                    lblRuleHeader1.setText(strRuleHeader1);
                    lblRule1.setText(strRule1);
                    btnPrevious.setVisible(false);
                    btnNext.setVisible(true);
                    break;
                }
                
                case 2:
                {
                    lblRuleHeader2.setText(strRuleHeader2);
                    lblRule1.setText(strRule2);
                    lblRule2.setText(strRule3);
                    lblRule3.setText(strRule4);
                    lblRule4.setText(strRule5);
                    btnPrevious.setVisible(true);
                    btnNext.setVisible(true);
                    break;
                }
                
                case 3:
                {
                    lblRuleHeader1.setText(strRuleHeader3);
                    lblRule1.setText(strRule6);
                    lblRule2.setText(strRule7);
                    lblRule3.setText(strRule8);
                    lblRule4.setText(strRule9);
                    btnPrevious.setVisible(true);
                    btnNext.setVisible(false);
                    break;
                }
            }
        }
    }
}
