//BTC Conversion Rates Panel - Patka - 2/24/2014
//Display the conversion rates used for Bitcoin conversions to the user.

import java.awt.*;
import javax.swing.*;

public class BTCRatesPanel extends JPanel
{
    private JLabel lblBTCRates;                                         //Currency Label
    private JLabel lblBTCtoUSD, lblBTCtoEUR, lblBTCtoPLN, lblBTCtoARS;  //Labels for Bitcoin conversions
    
    //constructor
    public BTCRatesPanel()
    {
        //Create Currency Heading
        lblBTCRates = new JLabel("Bitcoin Conversion Rates");
        
        //Create ARS Conversion Rates
        lblBTCtoUSD = new JLabel("1 Bitcoin = " + MoneyConversions.GetConversionRate("BTC", "USD") + " U.S. Dollars");
        lblBTCtoEUR = new JLabel("1 Bitcoin = " + MoneyConversions.GetConversionRate("BTC", "EUR") + " Euro");
        lblBTCtoPLN = new JLabel("1 Bitcoin = " + MoneyConversions.GetConversionRate("BTC", "PLN") + " Polish Z" + (char)322 + "oty");
        lblBTCtoARS = new JLabel("1 Bitcoin = " + MoneyConversions.GetConversionRate("BTC", "ARS") + " Argentine Peso");
        
        //Add ARS Conversion Rates To Panel
        add(lblBTCRates);
        add(lblBTCtoUSD);
        add(lblBTCtoEUR);
        add(lblBTCtoPLN);
        add(lblBTCtoARS);
        
        //Setup look of panel
        setPreferredSize(new Dimension(258, 160));
        setBackground(Color.cyan);
    }
}
