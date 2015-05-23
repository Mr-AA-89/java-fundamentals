//Postfix Class - Patrick Kubiak - 11/26/2014
//Validate and evaluate postfix expressions.

import java.util.Stack;

public class Postfix
{
    private Boolean valid;
    
    public Postfix()
    {
        valid = false;
    }
    
    /**
     * Evaluate a postfix expression
     * @param strExp        String postfix expression to evaluate.
     * @return              Double result of the postfix expression.
     */
    public double evalPostfixExp(String strExp)
    {
        String[] explodedExp = strExp.split(" ");
        Stack<Double> stackResult = new Stack<Double>();
        String strPart;
        double num, num1, num2, result;
        valid = true;
        
        for (int i = 0; i < explodedExp.length; i++)
        {
            strPart = explodedExp[i];
            if (isNumeric(strPart))
            {
                num = Double.parseDouble(strPart);
                stackResult.push(num);
            }
            else if (isOperator(strPart))
            {
                if (stackResult.size() < 2)
                {
                    valid = false;
                }
                else
                {
                    num1 = stackResult.pop();
                    num2 = stackResult.pop();
                    result = compute(num2, num1, strPart.charAt(0));
                    stackResult.push(result);
                }
            }
        }
        
        if (stackResult.size() == 1 && valid)
        {
            return stackResult.pop();
        }
        else
        {
            valid = false;
        }
        
        return -1;
    }
    
    /**
     * Compute the sum/difference/product/quotient/modulus of two operands.
     * 
     * @param num1      Double first operand.
     * @param num2      Double second operand.
     * @param op        Character operation (+, -, *, /, ^, or %).
     * @return          Double result or -1 if operation is invalid.
     */
    public static double compute(double num1, double num2, char op)
    {
        switch (op)
        {
            case '+': return num1 + num2;
            case '-': return num1 - num2;
            case '*': return num1 * num2;
            case '/': return num1 / num2;
            case '^': return Math.pow(num1, num2);
            case '%': return num1 % num2;
        }
        
        return -1;
    }
    
    /**
     * Check if a string is an operator.
     * 
     * @param strOp     String to check if it is an operator.
     * @return          True if it is an operator, false otherwise.
     */
    public static boolean isOperator(String strOp)
    {
        switch (strOp)
        {
            case "+": case "-": case "*": case "/": case "^": case "%":
            {
                return true;
            }
        }
        
        return false;
    }
    
    /**
     * Check if a string is a number.
     * 
     * @param strNum    String to check if it is a number.
     * @return          True if it is a number, false otherwise.
     */
    public static boolean isNumeric(String strNum)
    {  
        return strNum.matches("[-+]?\\d*\\.?\\d+");
    }
    
    /**
     * Check if the postfix expression processed by the evaluator is valid.
     * @return              True if expression is valid, false otherwise.
     */
    public Boolean isPostfixResultValid()
    {
        return valid;
    }
}
