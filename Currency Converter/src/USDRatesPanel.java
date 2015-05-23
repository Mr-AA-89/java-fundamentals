//USD Conversion Rates Panel - Patka - 2/24/2014
//Display the conversion rates used for U.S. Dollar conversions to the user.

import java.awt.*;
import javax.swing.*;

public class USDRatesPanel extends JPanel
{
    private JLabel lblUSDRates;                                         //Currency Label
    private JLabel lblUSDtoEUR, lblUSDtoPLN, lblUSDtoARS, lblUSDtoBTC;  //Labels for U.S. Dollar conversions
    
    //constructor
    public USDRatesPanel()
    {
        //Create Currency Heading
        lblUSDRates = new JLabel("U.S. Dollar Conversion Rates");
        
        //Create USD Conversion Rates
        lblUSDtoEUR = new JLabel("1 U.S. Dollar = " + MoneyConversions.GetConversionRate("USD", "EUR") + " Euro");
        lblUSDtoPLN = new JLabel("1 U.S. Dollar = " + MoneyConversions.GetConversionRate("USD", "PLN") + " Z" + (char)322 + "oty");
        lblUSDtoARS = new JLabel("1 U.S. Dollar = " + MoneyConversions.GetConversionRate("USD", "ARS") + " Argentine Peso");
        lblUSDtoBTC = new JLabel("1 U.S. Dollar = " + MoneyConversions.GetConversionRate("USD", "BTC") + " Bitcoins");
        
        //Add USD Conversion Rates To Panel
        add(lblUSDRates);
        add(lblUSDtoEUR);
        add(lblUSDtoPLN);
        add(lblUSDtoARS);
        add(lblUSDtoBTC);
        
        //Setup look of panel
        setPreferredSize(new Dimension(275, 160));
        setBackground(Color.cyan);
    }
}
