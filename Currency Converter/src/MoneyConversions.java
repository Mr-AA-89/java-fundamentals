//Money Conversions - Patka - 2/24/2014
//Methods to convert from one currency to another.

public class MoneyConversions
{
    //conversion rates
    final static float USD_TO_EUR = 0.73f, USD_TO_PLN = 3.04f, USD_TO_ARS = 7.88f, USD_TO_BTC = 0.001f;
    final static float EUR_TO_USD = 1.37f, EUR_TO_PLN = 4.16f, EUR_TO_ARS = 10.88f, EUR_TO_BTC = 0.002f;
    final static float PLN_TO_USD = 0.33f, PLN_TO_EUR = 0.24f, PLN_TO_ARS = 2.62f, PLN_TO_BTC = 0.0005f;
	final static float ARS_TO_USD = 0.13f, ARS_TO_EUR = 0.09f, ARS_TO_PLN = 0.38f, ARS_TO_BTC = 0.0002f;
    final static float BTC_TO_USD = 588f, BTC_TO_EUR = 428.5f, BTC_TO_PLN = 1792f, BTC_TO_ARS = 4392f;
    
    //constructor
    public MoneyConversions()
    {
        
    }
    
    
    /**
     * Convert currency
     * 
     * @param   strCurrencyFrom     string currency to convert from
     *          "USD" - U.S. Dollars, "EUR" - Euro, "PLN" Polish Zloty, "ARS" Argentine Peso, or "BTC" - Bitcoin
     *          
     * @param   strCurrencyTo		string currency to convert to
     *          "USD" - U.S. Dollars, "EUR" - Euro, "PLN" Polish Zloty, "ARS" Argentine Peso, or "BTC" - Bitcoin
	 *
	 * @param   flAmount			float amount to convert
     *          
     * @return  float currency value or -1 on failure
     */
    public float ConvertCurrency(String strCurrencyFrom, String strCurrencyTo, float flAmount)
    {
        switch(strCurrencyFrom)
        {
            case "USD":
            {
                switch(strCurrencyTo)
                {
                    case "USD":
                    {
                        //convert USD to USD
                        return flAmount;
                    }
                    
                    case "EUR":
                    {
                        //convert USD to EUR
                        return flAmount * USD_TO_EUR;
                    }
                    
                    case "PLN":
                    {
                        //convert USD to PLN
                        return flAmount * USD_TO_PLN;
                    }
					
					case "ARS":
                    {
                        //convert USD to ARS
                        return flAmount * USD_TO_ARS;
                    }
                    
                    case "BTC":
                    {
                        //convert USD to BTC
                        return flAmount * USD_TO_BTC;
                    }
                }
            }
            
            case "EUR":
            {
                switch(strCurrencyTo)
                {
                    case "USD":
                    {
                        //convert EUR to USD
                        return flAmount * EUR_TO_USD;
                    }
                    
                    case "EUR":
                    {
                        //convert EUR to EUR
                        return flAmount;
                    }
                    
                    case "PLN":
                    {
                        //convert EUR to PLN
                        return flAmount * EUR_TO_PLN;
                    }
					
					case "ARS":
                    {
                        //convert EUR to ARS
                        return flAmount * EUR_TO_ARS;
                    }
                    
                    case "BTC":
                    {
                        //convert EUR to BTC
                        return flAmount * EUR_TO_BTC;
                    }
                }
            }
            
            case "PLN":
            {
                switch(strCurrencyTo)
                {
                    case "USD":
                    {
                        //convert PLN to USD
                        return flAmount * PLN_TO_USD;
                    }
                    
                    case "EUR":
                    {
                        //convert PLN to EUR
                        return flAmount * PLN_TO_EUR;
                    }
                    
                    case "PLN":
                    {
                        //convert PLN to PLN
                        return flAmount;
                    }
					
					case "ARS":
                    {
                        //convert PLN to ARS
                        return flAmount * PLN_TO_ARS;
                    }
                    
                    case "BTC":
                    {
                        //convert PLN to BTC
                        return flAmount * PLN_TO_BTC;
                    }
                }
            }
			
			case "ARS":
            {
                switch(strCurrencyTo)
                {
                    case "USD":
                    {
                        //convert ARS to USD
                        return flAmount * ARS_TO_USD;
                    }
                    
                    case "EUR":
                    {
                        //convert ARS to EUR
                        return flAmount * ARS_TO_EUR;
                    }
                    
                    case "PLN":
                    {
                        //convert ARS to PLN
                        return flAmount * ARS_TO_PLN;
                    }
					
					case "ARS":
                    {
                        //convert ARS to ARS
                        return flAmount;
                    }
                    
                    case "BTC":
                    {
                        //convert ARS to BTC
                        return flAmount * ARS_TO_BTC;
                    }
                }
            }
            
            case "BTC":
            {
                switch(strCurrencyTo)
                {
                    case "USD":
                    {
                        //convert BTC to USD
                        return flAmount * BTC_TO_USD;
                    }
                    
                    case "EUR":
                    {
                        //convert BTC to EUR
                        return flAmount * BTC_TO_EUR;
                    }
                    
                    case "PLN":
                    {
                        //convert BTC to PLN
                        return flAmount * BTC_TO_PLN;
                    }
					
					case "ARS":
                    {
                        //convert BTC to ARS
                        return flAmount * BTC_TO_ARS;
                    }
                    
                    case "BTC":
                    {
                        //convert BTC to BTC
                        return flAmount;
                    }
                }
            }
        }
        
        return -1;
    }
	
	
	/**
     * Get currency conversion rates
     * 
     * @param   strCurrencyFrom     string currency to convert from
     *          "USD" - U.S. Dollars, "EUR" - Euro, "PLN" Polish Zloty, "ARS" Argentine Peso, or "BTC" - Bitcoin
     *          
     * @param   strCurrencyTo     string currency to convert to
     *          "USD" - U.S. Dollars, "EUR" - Euro, "PLN" Polish Zloty, "ARS" Argentine Peso, or "BTC" - Bitcoin
     *          
     * @return  float currency conversion rate or -1 on failure
     */
    public static float GetConversionRate(String strCurrencyFrom, String strCurrencyTo)
    {
        switch(strCurrencyFrom)
        {
            case "USD":
            {
                switch(strCurrencyTo)
                {
                    case "USD":
                    {
                        //rate to convert USD to USD
                        return 1;
                    }
                    
                    case "EUR":
                    {
                        //rate to convert USD to EUR
                        return USD_TO_EUR;
                    }
                    
                    case "PLN":
                    {
                        //rate to convert USD to PLN
                        return USD_TO_PLN;
                    }
					
					case "ARS":
                    {
                        //rate to convert USD to ARS
                        return USD_TO_ARS;
                    }
                    
                    case "BTC":
                    {
                        //rate to convert USD to BTC
                        return USD_TO_BTC;
                    }
                }
            }
            
            case "EUR":
            {
                switch(strCurrencyTo)
                {
                    case "USD":
                    {
                        //rate to convert EUR to USD
                        return EUR_TO_USD;
                    }
                    
                    case "EUR":
                    {
                        //rate to convert EUR to EUR
                        return 1;
                    }
                    
                    case "PLN":
                    {
                        //rate to convert EUR to PLN
                        return EUR_TO_PLN;
                    }
					
					case "ARS":
                    {
                        //rate to convert EUR to ARS
                        return EUR_TO_ARS;
                    }
                    
                    case "BTC":
                    {
                        //rate to convert EUR to BTC
                        return EUR_TO_BTC;
                    }
                }
            }
            
            case "PLN":
            {
                switch(strCurrencyTo)
                {
                    case "USD":
                    {
                        //rate to convert PLN to USD
                        return PLN_TO_USD;
                    }
                    
                    case "EUR":
                    {
                        //rate to convert PLN to EUR
                        return PLN_TO_EUR;
                    }
                    
                    case "PLN":
                    {
                        //rate to convert PLN to PLN
                        return 1;
                    }
					
					case "ARS":
                    {
                        //rate to convert PLN to ARS
                        return PLN_TO_ARS;
                    }
                    
                    case "BTC":
                    {
                        //rate to convert PLN to BTC
                        return PLN_TO_BTC;
                    }
                }
            }
			
			case "ARS":
            {
                switch(strCurrencyTo)
                {
                    case "USD":
                    {
                        //rate to convert ARS to USD
                        return ARS_TO_USD;
                    }
                    
                    case "EUR":
                    {
                        //rate to convert ARS to EUR
                        return ARS_TO_EUR;
                    }
                    
                    case "PLN":
                    {
                        //rate to convert ARS to PLN
                        return ARS_TO_PLN;
                    }
					
					case "ARS":
                    {
                        //rate to convert ARS to ARS
                        return 1;
                    }
                    
                    case "BTC":
                    {
                        //rate to convert ARS to BTC
                        return ARS_TO_BTC;
                    }
                }
            }
            
            case "BTC":
            {
                switch(strCurrencyTo)
                {
                    case "USD":
                    {
                        //rate to convert BTC to USD
                        return BTC_TO_USD;
                    }
                    
                    case "EUR":
                    {
                        //rate to convert BTC to EUR
                        return BTC_TO_EUR;
                    }
                    
                    case "PLN":
                    {
                        //rate to convert BTC to PLN
                        return BTC_TO_PLN;
                    }
					
					case "ARS":
                    {
                        //rate to convert BTC to ARS
                        return BTC_TO_ARS;
                    }
                    
                    case "BTC":
                    {
                        //rate to convert BTC to BTC
                        return 1;
                    }
                }
            }
        }
        
        return -1;
    }
}
