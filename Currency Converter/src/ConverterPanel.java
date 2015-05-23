//Converter Panel - Patka - 2/24/2014
//GUI components for currency converter

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class ConverterPanel extends JPanel
{
    private JLabel lblCurrencyFrom, lblCurrencyTo, lblFromType, lblToType;      //Labels for conversion to and from
    private JButton btnConvert,                                                 //Buttons for conversion to and from
        btnUSDfrom, btnUSDto,
        btnEURfrom, btnEURto,
        btnPLNfrom, btnPLNto,
        btnARSfrom, btnARSto,
        btnBTCfrom, btnBTCto;
    private JTextField txtCurrencyFrom, txtCurrencyOutput;                      //Textfields for input and output
    private JButton btnClear;                                                   //Button to clear all textfields and selected currencies
    private JButton btnRates;                                                   //Show conversion rates
    private MoneyConversions Money = new MoneyConversions();                    //Money conversion object
    
    //constructor
    public ConverterPanel()
    {
        //Create labels
        lblCurrencyFrom = new JLabel("Currency to convert from:");
        lblCurrencyTo = new JLabel("Currency to convert to:");
        lblFromType = new JLabel("");
        lblToType = new JLabel("");
        
        //Create textboxes
        txtCurrencyFrom = new JTextField(10);
        txtCurrencyOutput = new JTextField(20);
        
        //Create buttons        
        btnUSDfrom = new JButton("U.S. Dollars");
        btnUSDto = new JButton("U.S. Dollars");
        
        btnEURfrom = new JButton("Euro");
        btnEURto = new JButton("Euro");
        
        btnPLNfrom = new JButton("Polish Z" + (char)322 + "oty");
        btnPLNto = new JButton("Polish Z" + (char)322 + "oty");
        
        btnARSfrom = new JButton("Argentine Peso");
        btnARSto = new JButton("Argentine Peso");
        
        btnBTCfrom = new JButton("Bitcoin");
        btnBTCto = new JButton("Bitcoin");
        
        btnRates = new JButton("Show Conversion Rates");
        btnConvert = new JButton("Convert");
        btnClear = new JButton("Clear");
        
        //Create listeners for buttons
        btnConvert.addActionListener(new ConvertListener());
        
        btnUSDfrom.addActionListener(new USDListenerFrom());
        btnUSDto.addActionListener(new USDListenerTo());
        
        btnEURfrom.addActionListener(new EURListenerFrom());
        btnEURto.addActionListener(new EURListenerTo());
        
        btnPLNfrom.addActionListener(new PLNListenerFrom());
        btnPLNto.addActionListener(new PLNListenerTo());
        
        btnARSfrom.addActionListener(new ARSListenerFrom());
        btnARSto.addActionListener(new ARSListenerTo());
        
        btnBTCfrom.addActionListener(new BTCListenerFrom());
        btnBTCto.addActionListener(new BTCListenerTo());
        
        btnRates.addActionListener(new RatesListener());
        btnClear.addActionListener(new ClearListener());
        
        //Add convert from content of panel
        add(lblCurrencyFrom);
        add(txtCurrencyFrom);
        add(lblFromType);
        add(btnUSDfrom);
        add(btnEURfrom);
        add(btnPLNfrom);
        add(btnARSfrom);
        add(btnBTCfrom);
        
        //Add convert to content of panel
        add(lblCurrencyTo);
        add(lblToType);
        add(btnUSDto);
        add(btnEURto);
        add(btnPLNto);
        add(btnARSto);
        add(btnBTCto);
        
        //Add calculate content of panel
        add(txtCurrencyOutput);
		add(btnRates);
		add(btnConvert);
		add(btnClear);
        
        //Setup look of panel
        setPreferredSize(new Dimension(800, 150));
        setBackground(Color.green);
        
    }
  
    private class ConvertListener implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
           String strFromCurrency = new String(lblFromType.getText());                          //string type of currency to convert from
           String strToCurrency = new String(lblToType.getText());                              //string type of currency to convert to
           String strAmount = new String(txtCurrencyFrom.getText());                            //string amount to convert
           
           float old_money = Float.parseFloat(strAmount);                                       //float amount to convert
           float new_money = Money.ConvertCurrency(strFromCurrency, strToCurrency, old_money);  //float result of conversion
           
           if (new_money == -1)     //conversion error
           {
                txtCurrencyOutput.setText("Error: Currency not specified.");
           }
           else
           {
                //format output (ex. 100 USD = 73.0 EUR)
                txtCurrencyOutput.setText(strAmount + " " + strFromCurrency + " = " + new_money + " " + strToCurrency);
           }
        }
    }
    
    private class USDListenerFrom implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
           lblFromType.setText("USD");
        }
    }
    
    private class USDListenerTo implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
           lblToType.setText("USD");
        }
    }
    
    private class EURListenerFrom implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
            lblFromType.setText("EUR");
        }
    }
    
    private class EURListenerTo implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
           lblToType.setText("EUR");
        }
    }
    
    private class PLNListenerFrom implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
            lblFromType.setText("PLN");
        }
    }
    
    private class PLNListenerTo implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
           lblToType.setText("PLN");
        }
    }
    
    private class ARSListenerFrom implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
            lblFromType.setText("ARS");
        }
    }
    
    private class ARSListenerTo implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
           lblToType.setText("ARS");
        }
    }
    
    private class BTCListenerFrom implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
           lblFromType.setText("BTC");
        }
    }
    
    private class BTCListenerTo implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
           lblToType.setText("BTC");
        }
    }
    
    private class ClearListener implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
		   //clear selected currency labels and textfields
           lblFromType.setText("");
           lblToType.setText("");
           txtCurrencyFrom.setText("");
           txtCurrencyOutput.setText("");
        }
    }
	
	private class RatesListener implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
           //rates
           JFrame ratesFrame = new JFrame("Currency Conversion Rates");
           ratesFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);		//Exit when last frame is finished
           ratesFrame.getContentPane().add(new RatesPanel());
           ratesFrame.pack();
           ratesFrame.setVisible(true);
        }
    }
	
	
}
