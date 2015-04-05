//EUR Conversion Rates Panel - Patka - 2/24/2014
//Display the conversion rates used for Euro conversions to the user.

import java.awt.*;
import javax.swing.*;

public class EURRatesPanel extends JPanel
{
    private JLabel lblEURRates;                                         //Currency Label
    private JLabel lblEURtoUSD, lblEURtoPLN, lblEURtoARS, lblEURtoBTC;  //Labels for Euro conversions
    
    //constructor
    public EURRatesPanel()
    {
        //Create Currency Heading
        lblEURRates = new JLabel("Euro Conversion Rates");
        
        //Create EUR Conversion Rates
        lblEURtoUSD = new JLabel("1 Euro = " + MoneyConversions.GetConversionRate("EUR", "USD") + " U.S. Dollars");
        lblEURtoPLN = new JLabel("1 Euro = " + MoneyConversions.GetConversionRate("EUR", "PLN") + " Polish Z" + (char)322 + "oty");
        lblEURtoARS = new JLabel("1 Euro = " + MoneyConversions.GetConversionRate("EUR", "ARS") + " Argentine Peso");
        lblEURtoBTC = new JLabel("1 Euro = " + MoneyConversions.GetConversionRate("EUR", "BTC") + " Bitcoins");
        
        //Add EUR Conversion Rates To Panel
        add(lblEURRates);
        add(lblEURtoUSD);
        add(lblEURtoPLN);
        add(lblEURtoARS);
        add(lblEURtoBTC);
        
        //Setup look of panel
        setPreferredSize(new Dimension(275, 160));
        setBackground(Color.cyan);
    }
}
