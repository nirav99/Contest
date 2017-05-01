/**
 * Given a target number, find the sequence of unique numbers whose squares sum to the target.
 * e.g. Given 100, it can be represented in the following ways:
 * 1) 10 ^ 2
 * 2) 6 ^ 2 + 8 ^ 2
 * 3) 1 ^ 2 + 3 ^ 2 + 4 ^ 2 + 5 ^ 2 + 7 ^ 2
 * Thus, there are 3 solutions.
 * @author nirav99
 *
 */
public class PowerSum
{
  private int numSolutions;
  
  private int target;
  private int limit;
  
  public PowerSum(int target)
  {
  	this.target = target;
  	numSolutions = 0;
  }
  
  public void solve()
  {
  	System.out.println("Target sum to reach : " + target);
  	limit = (int) Math.round(Math.pow(target, 0.5));
//  	System.out.println("Limit = " + limit);
  	
  	int[] solutions = new int[limit];
  	
  	for(int i = 1; i <= limit; i++)
  	{
  		solutions[0] = i;
  		solveRecursive(i, i * i, solutions, 1);
  	}
  }
  
  private void solveRecursive(int i, int currSum, int[] solutions, int writeIndex)
  {
  	if(currSum == target)
  	{
  		System.out.println("Current solution : ");
  		numSolutions++;
  		
  		for(int x = 0; x < writeIndex; x++)
  			System.out.print(solutions[x] + " ");
  		System.out.println();
  	}
  	else
  	if(currSum > target)
  		return;
  	else
  	{
  		int nextNum = i + 1;
  		if(nextNum <= limit)
  		{
  			solutions[writeIndex] = nextNum;
  		  solveRecursive(nextNum, currSum + nextNum * nextNum, solutions, writeIndex + 1);
  		  solveRecursive(nextNum, currSum, solutions, writeIndex);
  		}
  	}
  }
  
  public void printNumSolutions()
  {
  	System.out.println("Num solutions = " + numSolutions);
  }
  
  public static void main(String[] args)
  {
  	try
  	{
  		int target = 100;
  		PowerSum psum = new PowerSum(target);
  		psum.solve();
  		psum.printNumSolutions();
  		
  	}
  	catch(Exception e)
  	{
  		System.err.println(e.getMessage());
  		e.printStackTrace();
  	}
  }
}
