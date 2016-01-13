//Game of Life Class - Patrick Kubiak - 10/15/2014
//Static methods for computing generations of the game of life.

public class GameOfLife
{
    /**
     * Calculate the next generation of the array.
     * 
     * @param board         Boolean 2D array of the current generation.
     * @return              Boolean 2D array of the next generation.
     */
    public static Boolean[][] computeNextGen(Boolean[][] board)
    {
        int rowLen = board.length;
        int colLen = board[0].length;
        Boolean[][] newBoard = new Boolean[rowLen][colLen];
        
        for (int i = 0; i < board.length; i++)
        {
            for (int j = 0; j < board[i].length; j++)
            {
                newBoard[i][j] = getNewValue(board, i, j);
            }
        }
        
        return newBoard;
    }
    
    /**
     * Calculate the next generation of the specified cell.
     * 
     * @param board         Boolean 2D array of the current generation.
     * @param x             X position of the cell to get the new value of in the 2D array.
     * @param y             Y position of the cell to get the new value of in the 2D array.
     * @return              New state of the cell.
     */
    public static Boolean getNewValue(Boolean[][] board, int x, int y)
    {
        int liveCount = 0;
        
        if (x-1 >= 0 && x-1 < board.length && y-1 >= 0 && y-1 < board.length)     {if (board[x-1][y-1] == true)  {liveCount++;}}
        if (x >= 0  && x < board.length && y-1 >= 0 && y-1 < board.length)        {if (board[x][y-1] == true)    {liveCount++;}}
        if (x+1 >= 0 && x+1 < board.length && y-1 >= 0 && y-1 < board.length)     {if (board[x+1][y-1] == true)  {liveCount++;}}
        
        if (x-1 >= 0 && x-1 < board.length)                                       {if (board[x-1][y] == true)    {liveCount++;}}
        if (x >= 0 && x < board.length)                                           {if (board[x][y] == true)      {liveCount++;}}
        if (x+1 >= 0 && x+1 < board.length)                                       {if (board[x+1][y] == true)    {liveCount++;}}
        
        if (x-1 >= 0 && x-1 < board.length && y+1 >= 0 && y+1 < board.length)     {if (board[x-1][y+1] == true)  {liveCount++;}}
        if (x >= 0  && x < board.length && y+1 >= 0 && y+1 < board.length)        {if (board[x][y+1] == true)    {liveCount++;}}
        if (x+1 >= 0 && x+1 < board.length && y+1 >= 0 && y+1 < board.length)     {if (board[x+1][y+1] == true)  {liveCount++;}}
        
        Boolean newValue = false;       //all other values set cell to death
        
        if (liveCount == 3)
        {
            newValue = true;            //all-field sum is 3, the inner field state for the next generation will be life
        }
        else if(liveCount == 4)
        {
            newValue = board[x][y];     //all-field sum is 4, the inner field retains its current state
        }
        
        return newValue;
    }
}
