//Mastermind Control Class - Patka - 5/3/2014
//Support class to control the mastermind game

import java.util.Random;

public class MastermindControl
{ 
    private Random myRandom = new Random();    //generate random numbers
    private int[] secretCode = new int[4];
    private boolean[] redPegLocation = {false, false, false, false};
    private boolean[] whitePegLocation = {false, false, false, false};
    
    /*
     * Constructor.
     */
    public MastermindControl()
    {
        generateSecretCode();
    }
    
    /*
     * Generate a secret code.
     */ 
    public void generateSecretCode()
    {
        for (int i = 0; i < secretCode.length; i++)
        {
            secretCode[i] = myRandom.nextInt(5); //0 - 5
        }
    }
    
    /*
     * Retrieve the secret code.
     */ 
    public int[] getSecretCode()
    {
        return secretCode;
    }
    
    /*
     * Set the secret code.
     */ 
    public void setSecretCode(int[] code)
    {
        secretCode = code;
    }
    
    /*
     * Guess the secret code.
     *
     * @param guessColorIndex   Integer array of color indexes to guess (size 4).
     * @return                  Integer array size 2.
     *                          Index 0 - red peg count, 1 - white peg count.
     */
    public int[] guess(int guessColorIndexes[])
    {
        int redPegCount = countRedPegs(guessColorIndexes);
        int whitePegCount = countWhitePegs(guessColorIndexes);
        int resultPegs[] = {redPegCount, whitePegCount};
        
        for (int i = 0; i < redPegLocation.length; i++)
        {
            redPegLocation[i] = false;
            whitePegLocation[i] = false;
        }
        
        return resultPegs;
    }
    
    /*
     * Determine number of red pegs to give.
     *
     * @param guessedColorIndexes[]     Integer array of colors guessed (0 to 5) (size 4).
     * @return                          Integer red peg count.
     */
    public int countRedPegs(int guessColorIndexes[])
    {
        int redPegCount = 0;
        
        for (int i = 0; i < secretCode.length; i++)
        {
            if (secretCode[i] == guessColorIndexes[i])
            {
                redPegLocation[i] = true;
                redPegCount++;
            }
        }
        
        return redPegCount;
    }
    
    /*
     * Determine number of white pegs to give. Must be called after countRedPegs().
     *
     * @param guessedColorIndexes[]     Integer array of colors guessed (0 to 5)
     * @return                          Integer white peg count.
     */
    public int countWhitePegs(int guessColorIndexes[])
    {
        int whitePegCount = 0;
        
        for (int i = 0; i < guessColorIndexes.length; i++)
        {            
            for (int j = 0; j < secretCode.length; j++)
            {
                if (secretCode[j] == guessColorIndexes[i] && !redPegLocation[i] && !redPegLocation[j] && !whitePegLocation[j])
                {
                    whitePegCount++;
                    whitePegLocation[j] = true;
                    j = secretCode.length;
                }
            }
        }
        
        return whitePegCount;
    }
    
    /*
     * Resets the game.
     */ 
    public void resetGame()
    {
        generateSecretCode();       //generate a new secret code.
    }
}
