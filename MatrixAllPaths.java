/**
 * Class to print all paths from the top left corner to bottom right corner of a 2D matrix.
 * Only two operations MOVE_DOWN and MOVE_RIGHT are allowed.
 * @author nirav99
 *
 */
public class MatrixAllPaths
{
  private int matrix[][];
  private int rows;
  private int cols;
  private int path[];
  
  public MatrixAllPaths(int rows, int cols)
  {
    this.rows = rows;
    this.cols = cols;
    matrix = new int[rows][cols];
    
    path = new int[rows * cols];
    
    int value = 1;
    
    System.out.println("Initial Matrix : ");
    for(int i = 0; i < rows; i++)
    {
      for(int j= 0; j < cols; j++)
      {
        matrix[i][j] = value++;
        System.out.print(matrix[i][j] + " ");
      }
      System.out.println();
    }
  }
  
  public void printPath(int start, int end)
  {
    for(int i = start; i <= end; i++)
      System.out.print(path[i] + " ");
    System.out.println();
  }
  
  public void findPaths()
  {
    System.out.println("Paths :");

    if(rows == 1 && cols == 1)
      System.out.println(matrix[0][0]);

    int writePos = 1;
    path[0] = matrix[0][0];
    if(rows > 1)
      findPathRec(writePos, 1, 0);
    if(cols > 1)
      findPathRec(writePos, 0, 1);
  }
  
  private void findPathRec(int writePos, int nextR, int nextC)
  {
    if((nextR == rows -1) && (nextC == cols -1))
    {
      path[writePos] = matrix[nextR][nextC];
      printPath(0, writePos);
      return;
    }
    else
    {
      if(nextR < rows - 1)
      {
        path[writePos] = matrix[nextR][nextC];
        findPathRec(writePos + 1, nextR + 1, nextC);
      }
      if(nextC < cols - 1)
      {
        path[writePos] = matrix[nextR][nextC];
        findPathRec(writePos + 1, nextR, nextC + 1);
      }
    }
  }
  public static void main(String args[])
  {
    if(args.length != 2)
    {
      printUsage();
      System.exit(-1);
    }
    int rows = Integer.parseInt(args[0]);
    int cols = Integer.parseInt(args[1]);
    MatrixAllPaths m = new MatrixAllPaths(rows, cols);
    m.findPaths();
  }
  
  public static void printUsage()
  {
    System.err.println("Class to print all paths from top left to bottom right in 2D matrix");
    System.err.println("Only 2 moves, down and right are allowed");
    System.err.println("Usage : ");
    System.err.println("rows cols");
  }
}
