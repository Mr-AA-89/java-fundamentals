//PLN Conversion Rates Panel - Patka - 2/24/2014
//Display the conversion rates used for Euro conversions to the user.

import java.awt.*;
import javax.swing.*;
import java.text.DecimalFormat;

public class PLNRatesPanel extends JPanel
{
    private JLabel lblPLNRates;                                         //Currency Label
    private JLabel lblPLNtoUSD, lblPLNtoEUR, lblPLNtoARS, lblPLNtoBTC;  //Labels for Polish Zloty conversions
    
    //constructor
    public PLNRatesPanel()
    {
        //Create Currency Heading
        lblPLNRates = new JLabel("Polish Z" + (char)322 + "oty Conversion Rates");
		DecimalFormat dfBTC = new DecimalFormat("#.####");
		
        //Create USD Conversion Rates
        lblPLNtoUSD = new JLabel("1 Polish Z" + (char)322 + "oty = " + MoneyConversions.GetConversionRate("PLN", "USD") + " U.S. Dollars");
        lblPLNtoEUR = new JLabel("1 Polish Z" + (char)322 + "oty = " + MoneyConversions.GetConversionRate("PLN", "EUR") + " Euro");
        lblPLNtoARS = new JLabel("1 Polish Z" + (char)322 + "oty = " + MoneyConversions.GetConversionRate("PLN", "ARS") + " Argentine Peso");
        lblPLNtoBTC = new JLabel("1 Polish Z" + (char)322 + "oty = " + dfBTC.format(MoneyConversions.GetConversionRate("PLN", "BTC")) + " Bitcoins");
        
        //Add USD Conversion Rates To Panel
        add(lblPLNRates);
        add(lblPLNtoUSD);
        add(lblPLNtoEUR);
        add(lblPLNtoARS);
        add(lblPLNtoBTC);
        
        //Setup look of panel
        setPreferredSize(new Dimension(300, 160));
        setBackground(Color.cyan);
    }
}
