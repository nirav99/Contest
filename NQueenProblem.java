/**
 * Class to solve the problem of positioning N queens on chess board such that
 * no queen can hit another.
 * @author nirav99
 *
 */
public class NQueenProblem
{
  private int rows; // Number of rows of board
  private int cols; // Number of columns of board
  private int numSolutions = 0; // Number of solutions
  private int board[][]; // Game board
  
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
    System.out.println("Solution : " + ++numSolutions);
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
   * Method to play the game of placing the queens
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
   * Recursive method
   * @param queenNum
   */
  private void playRecursive(int queenNum)
  {
    if(queenNum >= rows)
      return;
    for(int j = 0; j < cols; j++)
    {
      if(isPositionSafe(queenNum, j))
      {
        board[queenNum][j] = 1;
        if(queenNum == rows -1)
          printBoard();
        else
          playRecursive(queenNum + 1);
      }
      board[queenNum][j] = 0;
    }
  }
  
  /**
   * Check if the specified position is safe before actually placing the
   * queen.
   * @param r
   * @param c
   * @return
   */
  public boolean isPositionSafe(int r, int c)
  {
    int sum = 0;
    // Check the row
    for(int j = 0; j < cols; j++)
    {
      sum += board[r][j];
      
      if(sum >= 1)
        return false;
    }
    
    sum = 0;
    // Check the column
    for(int i = 0; i < rows; i++)
    {
      sum += board[i][c];
      if(sum >= 1)
        return false;
    }
    
    sum = 0;
    // Check the diagonal towards top left
    for(int currR = r, currC = c; currR >= 0 && currC >= 0; currR--, currC--)
    {
      sum += board[currR][currC];
      if(sum >= 1)
        return false;
    }
    
 // Check the diagonal towards bottom right
    for(int currR = r, currC = c; currR < rows && currC < cols; currR++, currC++)
    {
      sum += board[currR][currC];
      if(sum >= 1)
        return false;
    }
    
    sum = 0;
    // Check the diagonal towards top right
    for(int currR = r, currC = c; currR >= 0 && currC < cols; currR--, currC++)
    {
      sum += board[currR][currC];
      if(sum >= 1)
        return false;
    }
    
    // Check the diagonal towards bottom left
    for(int currR = r, currC = c; currR < rows && currC >= 0; currR++, currC--)
    {
      sum = board[currR][currC];
      if(sum >= 1)
        return false;
    }
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

