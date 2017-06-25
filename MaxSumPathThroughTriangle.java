import java.util.*;

/**
 * Given a trianble, calculate the maxinum sum of the values in the triangle
 * by adding value of current element to its left parent, vertical parent or right parent.
 * i.e. for
 * value_triangle[i][j] = triangle[i][j] + max{triangle[i -1][j -1], triangle[i -1][j], triangle[i -1][j + 1]}
 * The solution is based on dynamic programming.
 * @author nirav99
 *
 */
public class MaxSumPathThroughTriangle
{
  private int[][] triangle;
  private int maxRows;
  
  private int[][] cost;
  
  public MaxSumPathThroughTriangle(int[][] triangle)
  {
  	maxRows = triangle.length;
  	
    this.triangle = triangle;
    
    cost = new int[maxRows][maxRows];
    
    for(int i = 0; i < maxRows; i++)
    {
    	for(int j = 0; j < maxRows; j++)
    		cost[i][j] = 0;
    }
  }
  
  public int calculateMaxSumPath()
  {
    for(int i = 0; i < maxRows; i++)
    {
    	for(int j = 0; j <= i; j++)
    	{
    		cost[i][j] = getMaxParentSum(i, j) + triangle[i][j];
    	}
    }

    int maxSum = cost[maxRows - 1][0];
    
    for(int i = 1; i < maxRows; i++)
    {
    	if(maxSum < cost[maxRows - 1][i])
    	  maxSum = cost[maxRows - 1][i];
    }
    
    return maxSum;
  }
  
  private int getMaxParentSum(int i, int j)
  {
    int[] parentArray = new int[3];
    
    Arrays.fill(parentArray, 0);
    
    if(i - 1 >= 0)
    {
    	if(j - 1 >= 0)
    		parentArray[0] = cost[i - 1][j - 1];
    	
    	parentArray[1] = cost[i - 1][j];
    	
    	if(j + 1 <= maxRows - 1)
    		parentArray[2] = cost[i - 1][j + 1];
    }
    
    int max = parentArray[0];
    
    for(int index = 1; index < parentArray.length; index++)
    {
    	if(max < parentArray[index])
    		max = parentArray[index];
    }
    
    return max;
  }
  
  public static void main(String[] args)
  {
    try
    {
      int[][] triangle = { {1}, {2,3}, {4, 5, 6}, {7, 10, 9, 8}};
   
      MaxSumPathThroughTriangle maxSum = new MaxSumPathThroughTriangle(triangle);
      System.out.println("MAX SUM = " + maxSum.calculateMaxSumPath());
    }
    catch(Exception e)
    {
      System.err.println(e.getMessage());
      e.printStackTrace();
    }
  }
}
