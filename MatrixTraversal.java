/**
 * 
 */

/**
 * @author nirav99
 *
 */
public class MatrixTraversal
{
  private int a[][];
  private int rows;
  private int cols;
  
  public MatrixTraversal(int rows, int cols) throws Exception
  {
	this.rows = rows;
	this.cols = cols;
	
	if(rows < 1 || cols < 1)
      throw new Exception("Invalid array bounds");
	
    a = new int[rows][cols];
    int next = 1;
    
    for(int i = 0; i < rows; i++)
      for(int j = 0; j < cols; j++)
      {
        a[i][j] = next++;
      }
  }
  
  public void printMatrix()
  {
    for(int i = 0; i < rows; i++)
    {
       for(int j = 0; j < cols; j++)
         System.out.print(a[i][j] + " ");
       System.out.println();
    }    
  }

  private void print(int r, int c)
  {
    System.out.print(a[r][c] + " ");
  }
  
  public void spiralTraverse()
  {
    int minRow = 0;
    int maxRow = rows -1 ;
    int minCol = 0;
    int maxCol = cols - 1;
    int size = rows * cols;
    int numVisited = 0;
    
    int currR, currC;
    
    while(true)
    {
      currR = minRow;
      currC = minCol;
      
      while(currC <= maxCol)
      {
        numVisited++;
        print(currR, currC++);
      }
      currC--;
      currR++;
      
      if(numVisited == size)
    	  break;
      
      while(currR <= maxRow)
      {
        numVisited++;
        print(currR++, currC);
      }
      currR--;
      currC--;
      
      if(numVisited == size)
        break;
      
      while(currC >= minCol)
      {
        numVisited++;
        print(currR, currC--);
      }
      currC++;
      currR--;

      if(numVisited == size)
          break;
      
      while(currR > minRow)
      {
        numVisited++;
        print(currR--, currC);
      }
      
      if(numVisited == size)
        break;
      
      minRow++;
      maxRow--;
      minCol++;
      maxCol--;
    }
    System.out.println();
  }
  
  /**
   * @param args
   */
  public static void main(String[] args)
  {
	  
    if(args.length != 2)
    {
      printUsage();
      System.exit(-1);
    }

    try
    {
      int rows = Integer.parseInt(args[0]); 
      int cols = Integer.parseInt(args[1]);
      MatrixTraversal trav = new MatrixTraversal(rows, cols);
      System.out.println("Original Matrix");
      trav.printMatrix();
      System.out.println("Spiral Traversal");
      trav.spiralTraverse();
    }catch(Exception e)
    {
      System.err.println(e.getMessage());
      e.printStackTrace();
    }
  }
  
  public static void printUsage()
  {
    System.out.println("Usage:");
    System.out.println("NumRows NumCols");
    System.out.println("NumRows - Number of rows of matrix");
    System.out.println("NumCols - Number of columns of matrix");
  }
}
