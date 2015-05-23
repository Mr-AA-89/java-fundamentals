//Postfix Evaluator Class - Patrick Kubiak - 11/25/2014
//Evaluate a postfix expression

import java.util.Scanner;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class PostfixEval
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Scanner input = new Scanner(System.in);
        Postfix PostfixEvaluator = new Postfix();
        String strExp = new String();
        Boolean bValid;
        double dblResult;
        char choice;
        
        do
        {
            System.out.println("\fWelcome to the Postfix Evaluator.");
            System.out.println("(e)valuate or e(x)it");
            System.out.print("Select: ");
            choice = input.next().charAt(0);
            
            switch (choice)
            {
                case 'e':
                {
                    //evaluate a postfix expression
                    System.out.print("Enter the postfix expression: ");
                    strExp = reader.readLine();
                    dblResult = PostfixEvaluator.evalPostfixExp(strExp);
                    bValid = PostfixEvaluator.isPostfixResultValid();
                    
                    if (bValid)
                    {
                        System.out.println("Result: " + dblResult);
                    }
                    else
                    {
                        System.out.println("The expression is not in valid postfix form.");
                    }
                    
                    System.out.print("Press enter to continue...");
                    System.in.read();
                    break;
                }
                
                case 'x':
                {
                    //exit the program
                    System.out.println("\nHave a nice day.");
                    break;
                }
            }
        }
        while(choice != 'x');
    }
}
