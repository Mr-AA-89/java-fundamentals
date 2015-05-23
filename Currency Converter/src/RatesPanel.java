//Conversion Rates Panel - Patka - 2/24/2014
//Display the conversion rates used to the user.

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class RatesPanel extends JPanel
{
    private JButton btnUSDRates, btnEURRates, btnPLNRates, btnARSRates, btnBTCRates;              //Button for conversions
    
    //constructor
    public RatesPanel()
    {
        //Create conversion rates buttons
        btnUSDRates = new JButton("U.S. Dollar Conversions");
		btnEURRates = new JButton("Euro Conversions");
		btnPLNRates = new JButton("Polish Z" + (char)322 + "oty Conversions");
		btnARSRates = new JButton("Argentine Peso Conversions");
		btnBTCRates = new JButton("Bitcoin Conversions");
		
		//Create listeners for buttons
        btnUSDRates.addActionListener(new USDRatesListener());
		btnEURRates.addActionListener(new EURRatesListener());
		btnPLNRates.addActionListener(new PLNRatesListener());
		btnARSRates.addActionListener(new ARSRatesListener());
		btnBTCRates.addActionListener(new BTCRatesListener());
		
        //Add conversion rates buttons to panel
        add(btnUSDRates);
		add(btnEURRates);
		add(btnPLNRates);
        add(btnARSRates);
        add(btnBTCRates);
		
		//Setup look of panel
		setPreferredSize(new Dimension(280, 160));
		setBackground(Color.cyan);
    }
    
    private class USDRatesListener implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
           //Create USD conversion rates frame
           JFrame USDFrame = new JFrame("U.S Dollar Conversion Rates");
           USDFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);		//Exit when last frame is finished
           USDFrame.getContentPane().add(new USDRatesPanel());
           USDFrame.pack();
           USDFrame.setVisible(true);
        }
    }
	
	private class EURRatesListener implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
           //Create EUR conversion rates frame
           JFrame EURFrame = new JFrame("Euro Conversion Rates");
           EURFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);		//Exit when last frame is finished
           EURFrame.getContentPane().add(new EURRatesPanel());
           EURFrame.pack();
           EURFrame.setVisible(true);
        }
    }
	
	private class PLNRatesListener implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
           //Create PLN conversion rates frame
           JFrame PLNFrame = new JFrame("Polish Z" + (char)322 + "oty Conversion Rates");
           PLNFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);		//Exit when last frame is finished
           PLNFrame.getContentPane().add(new PLNRatesPanel());
           PLNFrame.pack();
           PLNFrame.setVisible(true);
        }
    }
    
    private class ARSRatesListener implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
           //Create ARS conversion rates frame
           JFrame ARSFrame = new JFrame("Argentine Peso Conversion Rates");
           ARSFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);		//Exit when last frame is finished
           ARSFrame.getContentPane().add(new ARSRatesPanel());
           ARSFrame.pack();
           ARSFrame.setVisible(true);
        }
    }
    
    private class BTCRatesListener implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
           //Create BTC conversion rates frame
           JFrame BTCFrame = new JFrame("Bitcoin Conversion Rates");
           BTCFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);		//Exit when last frame is finished
           BTCFrame.getContentPane().add(new BTCRatesPanel());
           BTCFrame.pack();
           BTCFrame.setVisible(true);
        }
    }
    
    
}
