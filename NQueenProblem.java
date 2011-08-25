/**
 * Class to solve the problem of positioning N queens on chess board such that
 * no queen can hit another.
 * @author nirav99
 *
 */
public class NQueenProblem
{
  private int rows;             // Number of rows of board
  private int cols;             // Number of columns of board
  private int numSolutions = 0; // Number of solutions:w
  private int board[][];        // Game board
  
  /**
   * Class constructor
   * @param rows
   * @param cols
   */
  public NQueenProblem(int rows, int cols)
  {
    this.rows = rows;
    this.cols = cols;
    board = new int[rows][cols];
    
    initializeBoard();
  }
  
  /**
   * Initialize the board
   */
  private void initializeBoard()
  {
    for(int i = 0; i < rows; i++)
      for(int j = 0; j < cols; j++)
         board[i][j] = 0;
  }
 
  /**
   * Display current state of the board
   */
  public void printBoard()
  {
    for(int i = 0; i < rows; i++)
    {
      for(int j = 0; j < cols; j++)
      {
        if(board[i][j] > 0)
          System.out.print("X ");
        else
          System.out.print("_ ");
      }
      System.out.println();
    }
  }
  
  /**
   * Method to place the queens on the board
   */
  public void play()
  {
    for(int j = 0; j < cols; j++)
    {
      board[0][j] = 1;
      playRecursive(1);
      board[0][j] = 0;
    }
  }
  
  /**
   * Method implementing recursion
   */
  private void playRecursive(int queenNum)
  {
    if(queenNum >= rows)
      return;
    for(int j = 0; j < cols; j++)
    {
      board[queenNum][j] = 1;
      
      if(isBoardSafe())
      {
        if(queenNum == rows -1)
        {
          System.out.println();
          System.out.println("Solution : " + ++numSolutions);
          printBoard();
        }
        else
          playRecursive(queenNum + 1);
      }
      board[queenNum][j] = 0;
    }
  }
  
  /**
   * Method to check if the whole board is safe
   * @return
   */
  public boolean isBoardSafe()
  {
    int sum = 0;
    // Check row-wise
    for(int i = 0; i < rows; i++)
    {
      sum = 0;
      for(int j = 0; j < cols; j++)
      {
         sum += board[i][j];
         if(sum > 1)
           return false;
      }
    }
    
    // Check column-wise
    for(int j = 0; j < cols; j++)
    {
      sum = 0;
      for(int i = 0; i < rows; i++)
      {
        sum += board[i][j];
        if(sum > 1)
          return false;
      }
    }
    
    // 1st set of diagonals
    for(int i = 0; i < rows; i++)
    {
      sum = 0;
      for(int startR = i, startC = 0; startR < rows && startC < cols; startR++, startC++)
      { 
        sum += board[startR][startC];
        if(sum > 1)
          return false;
      }
    }
    
    //2nd set of diagonals
    for(int j = 0; j < cols; j++)
    {
      sum = 0;
      for(int startC = j, startR = 0; startR < rows && startC < cols; startR++, startC++)
      {
        sum += board[startR][startC];
        if(sum > 1)
          return false;
      }
    }
    
    //3rd set of diagonals
    for(int i = 0; i < rows; i++)
    {
      sum = 0;
      for(int startC = cols -1, startR = i; startR < rows && startC >= 0; startR++, startC--)
      {
        sum += board[startR][startC];
        if(sum > 1)
          return false;
      }
    }
    
    //4th set of diagonals
    for(int j = cols -1; j >= 0; j--)
    {
      sum = 0;
      for(int startR = 0, startC = j; startR < rows & startC >= 0; startR++, startC--)
      {
        sum += board[startR][startC];
        if(sum > 1)
          return false;
      }
    }
    // No errors found, return true
    return true;
  }
  
  public static void main(String args[])
  {
    if(args.length != 1)
    {
      printUsage();
      System.exit(-1);
    }
    
    int size = Integer.parseInt(args[0]);
	
    if(size == 2 || size == 3)
    {
      System.out.println("No solution exists for board size " + size);
      System.exit(0);
   	}
	
    NQueenProblem p = new NQueenProblem(size, size);
    p.play();
  }
  
  public static void printUsage()
  {
    System.err.println("This program provides one solution of placing N queens on NxN sized chess board");
    System.err.println();
    System.err.println("Usage : ");
    System.err.println("Enter the size of the board (i.e number of rows of the chess board)");
  }
}

