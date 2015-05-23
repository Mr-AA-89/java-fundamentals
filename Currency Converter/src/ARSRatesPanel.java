//ARS Conversion Rates Panel - Patka - 2/24/2014
//Display the conversion rates used for Argentine Peso conversions to the user.

import java.awt.*;
import javax.swing.*;
import java.text.DecimalFormat;

public class ARSRatesPanel extends JPanel
{
    private JLabel lblARSRates;                                         //Currency Label
    private JLabel lblARStoUSD, lblARStoEUR, lblARStoPLN, lblARStoBTC;  //Labels for Argentine Peso conversions
    
    //constructor
    public ARSRatesPanel()
    {
        //Create Currency Heading
        lblARSRates = new JLabel("Argentine Peso Conversion Rates");
		DecimalFormat dfBTC = new DecimalFormat("#.####");
        
        //Create ARS Conversion Rates
        lblARStoUSD = new JLabel("1 Argentine Peso = " + MoneyConversions.GetConversionRate("ARS", "USD") + " U.S. Dollars");
        lblARStoEUR = new JLabel("1 Argentine Peso = " + MoneyConversions.GetConversionRate("ARS", "EUR") + " Euro");
        lblARStoPLN = new JLabel("1 Argentine Peso = " + MoneyConversions.GetConversionRate("ARS", "PLN") + " Polish Z" + (char)322 + "oty");
        lblARStoBTC = new JLabel("1 Argentine Peso = " + dfBTC.format(MoneyConversions.GetConversionRate("ARS", "BTC")) + " Bitcoins");
        
        //Add ARS Conversion Rates To Panel
        add(lblARSRates);
        add(lblARStoUSD);
        add(lblARStoEUR);
        add(lblARStoPLN);
        add(lblARStoBTC);
        
        //Setup look of panel
        setPreferredSize(new Dimension(275, 160));
        setBackground(Color.cyan);
    }
}
