//Morra Support Class - Patka - 3/31/2014
//Support class for the morra game.

import java.util.Random;

public class Morra
{
    private int numRounds, roundsPlayed;                                                 //store number of rounds to play, and number of rounds already played
    private int roundWinner, playerWins, computerWins, winsNeeded;                       //store winner of the round, number of times player won, number of times computer won, and number of round wins a player needs to win the match
    private int computerFingers, playerFingers, computerTotal, playerTotal, roundTotal;  //store number of fingers computer chose, number of fingers player chose, total computer guessed, total player guessed, and actual round total
    private Random myRandNum = new Random();                                             //generate random numbers
    
	//constructor
	public Morra()
	{
		
	}
	
    /**
     * Reset the game.
     * @param rounds integer number of rounds to play
     */
    public void resetMorraGame(int rounds)
    {
        //reset game
        numRounds = rounds;
        roundsPlayed = 0;
        roundWinner = -1;
        computerWins = 0;
        playerWins = 0;
        computerFingers = -1;
        playerFingers = -1;
        computerTotal = 0;
        playerTotal = 0;
		
		/**
        * rounds = first player to x wins
        * 1 rounds = 1/1    +1/+2
        * 3 rounds = 2/3
        * 5 rounds = 3/5
        * 7 rounds = 4/7
        * 9 rounds = 5/9
        */
        
        winsNeeded = numRounds - ((int)(numRounds / 2));
    }
    
    //Throw a random number of fingers and guess a random total
    public void takeComputerMove()
    {
        computerFingers = myRandNum.nextInt(4) + 1;  //1 - 5
		
        //computerTotal = myRandNum.nextInt(8) + 2;    //2 - 10
		//BUG FIX: computer total now takes into account computer guess
		computerTotal = computerFingers + myRandNum.nextInt(4) + 1;			//computer fingers + player finger guess (1 - 5)
    }
    
    //Take player's move, take computer's move, and evaluate the round
    public void takePlayerMove(int numFingers, int numTotal)
    {
        //set human fingers and total
        playerFingers = numFingers;
        playerTotal = numTotal;
        
        //take computer move and evaluate round
        takeComputerMove();
        if (evaluateRound())
        {
            roundsPlayed++;
        }
    }
    
    /**
     * Evaluate winner of the round.
     * @return true if round is over, false otherwise.
     */
    public boolean evaluateRound()
    {
        boolean bRoundOver = false;
        roundTotal = computerFingers + playerFingers;
        
        if ((roundTotal == computerTotal) && (roundTotal == playerTotal))    //it's a tie
        {
            roundWinner = 2;
            bRoundOver = true;
        }
        else    //not a tie
        {
            if (roundTotal == computerTotal)    //computer won the round
            {
                roundWinner = 0;
                computerWins++;
                bRoundOver = true;
            }
            else if (roundTotal == playerTotal) //player won the round
            {
                roundWinner = 1;
                playerWins++;
                bRoundOver = true;
            }
        }
        
        return bRoundOver;
    }
    
    /**
     * Get the winner of the round.
     * @return 0 if computer won, 1 if human won, or 2 if tie.
     */
    public int getRoundWinner()
    {
        return roundWinner;
    }
    
    //Reset the round winner
    public void resetRoundWinner()
    {
        roundWinner = -1;
    }
    
    /**
     * Get the number of rounds played of the game.
     * @return integer number of rounds played
     */
    public int getRoundsPlayed()
    {
        return roundsPlayed;
    }
    
    /**
     * Get the winner of the game.
     * @return -1 if no winner, 0 if computer won, or 1 if human won.
     */
    public int getGameWinner()
    {
        if (computerWins >= winsNeeded)
        {
            return 0;
        }
        else if (playerWins >= winsNeeded)
        {
            return 1;
        }
        
        return -1;
    }
	
	//Get the number of round wins a player needs to meet or exceed to win the match
	public int getWinsNeeded()
	{
		return winsNeeded;
	}
    
    //Get the number of fingers the computer threw
    public int getComputerFingers()
    {
        return computerFingers;
    }
    
    //Get the number of fingers the player threw
    public int getPlayerFingers()
    {
        return playerFingers;
    }
    
    //Get the total number of fingers thrown in the round
    public int getRoundTotal()
    {
        return roundTotal;
    }
    
    //Get the computer's predicted total
    public int getComputerTotal()
    {
        return computerTotal;
    }
    
    //Get the player's predicted total
    public int getPlayerTotal()
    {
        return playerTotal;
    }
    
    //Get the computer's current score
    public int getComputerScore()
    {
        return computerWins;
    }
    
    //Get the player's current score
    public int getPlayerScore()
    {
        return playerWins;
    }
    
    //Check if the string can be safely converted into an integer
    public static boolean isInteger(String strNum)
    {
        try
        {
            Integer.parseInt(strNum);
        }
        catch (NumberFormatException e)
        {
            return false;
        }
        
        return true;
    }
}
