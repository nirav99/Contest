import java.util.*;

/**
 * Given an array of 2D matrices, find the optimal cost of multiplying these matrices using dynamic programming
 * @author nirav99
 *
 */
public class OptimalMatrixMultiplicationCost
{
  public static int optimalMultiplicationCost(ArrayList<Matrix> matrices)
  {
  	// This is the array of all the columns in the matrices. We start counting from index 1.
  	// Index zero contains the number of rows of the first matrix.
  	int[] c = new int[matrices.size() + 1];
  	
  	for(int i = 0; i < matrices.size(); i++)
  	{
  		c[i] = matrices.get(i).rows;
  		c[i + 1] = matrices.get(i).cols;
   	}
  	
  	int N = matrices.size();
  	
  	int[][] cost = new int[c.length][c.length];
  	
  	for(int i = 0; i < c.length; i++) // Set the cost of diagonals, i.e. multiplyin matrix to itself as zero.
  			cost[i][i] = 0;
  	
  	for(int k = 1; k < N; k++)
  	{
  		for(int left = 1; left <= N - k; left++)
  		{
  			int right = left + k;
  			cost[left][right] = Integer.MAX_VALUE;
  			
  			for(int i = left; i < right; i++)
  			{
  				int newCost = cost[left][i] + cost[i + 1][right] + c[left - 1] * c[i] * c[right];
  				
  				if(newCost < cost[left][right])
  				{
  					cost[left][right] = newCost;
  				}
  			}
  		}
  	}
  	
  	return cost[1][N];
  }
  
  public static void main(String[] args)
  {
  	try
  	{
  		ArrayList<Matrix> matrices = new ArrayList<Matrix>();
  		matrices.add(new Matrix(2,3));
  		matrices.add(new Matrix(3,5));
  		matrices.add(new Matrix(5,2));
  		
  		System.out.println("Optimal multiplication cost : " + optimalMultiplicationCost(matrices));
  	}
  	catch(Exception e)
  	{
  		e.printStackTrace();
  	}
  }
}

class Matrix
{
	int rows;
	int cols;
	
	Matrix(int r, int c)
	{
		this.rows = r;
		this.cols = c;
	}
}
