//Convert Currency - Patka - 2/24/2014
//Program that converts from one currency to another

import javax.swing.JFrame;

public class CurrencyConverter
{    
    public static void main(String[] args)
    {
        //Create frame
        JFrame conversionFrame = new JFrame("Currency Converter");
        conversionFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        conversionFrame.getContentPane().add(new ConverterPanel());
        conversionFrame.pack();
        conversionFrame.setVisible(true);
    }
}
