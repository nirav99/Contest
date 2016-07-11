import java.util.*;

/**
 * Finds the solutions that add upto a given number.
 * Assumptions: only positive unique integers in the given array.
 * @author Nirav
 *
 */
public class NumbersAddingUpToThreshold
{
  private int[] input;
  private int X;
  private int totalSolutions;
  
  public NumbersAddingUpToThreshold(int[] givenArray, int X)
  {
    this.X = X;
    
    ArrayList<Integer> temp = new ArrayList<Integer>();
    
    for(int i = 0; i < givenArray.length; i++)
    {
      if(givenArray[i] <= X)
        temp.add(givenArray[i]);
    }
    
    if(temp.size() > 0)
    {
      Collections.sort(temp);
      input = new int[temp.size()];
      
      for(int i = 0; i < temp.size(); i++)
        input[i] = temp.get(i);
    }
    
    System.out.println("Sorted array : ");
    for(int i = 0; i < input.length; i++)
      System.out.print(input[i] + " ");
    System.out.println("\n");
  }
  
  public void solve()
  {
    if(input == null || input.length < 1)
    {
      System.out.println("No solutions.");
      return;
    }
    
    HashSet<Integer> currentSolution;
    
    System.out.println("Solutions : ");
    for(int i = 0; i < input.length; i++)
    {
      currentSolution = new HashSet<Integer>();
      currentSolution.add(input[i]);
      solveRecursive(currentSolution, i + 1, X - input[i]);
    }
    
    System.out.println("Total solutions = " + totalSolutions);
  }
  
  private void solveRecursive(HashSet<Integer> currentSolution, int startIndex, int partialSum)
  {
    if(startIndex >= input.length)
      return;
    
 //   System.out.println("Start  = " + input[startIndex] + " Partial Sum = " + partialSum);
    int currNum = input[startIndex];
    int newSum = partialSum - currNum;
    
    // Found the solution
    if(newSum == 0)
    {
      totalSolutions++;
      currentSolution.add(currNum);
      printSolution(currentSolution);
      return;
    }
    else
    {
      if(newSum >= currNum)
      {
        currentSolution.add(currNum);
        solveRecursive(currentSolution, startIndex + 1, partialSum - currNum);
      }
      currentSolution.remove(currNum);
      solveRecursive(currentSolution, startIndex + 1, partialSum);
    }
  }
  
  private void printSolution(HashSet<Integer> currentSolution)
  {
    Iterator<Integer> iter = currentSolution.iterator();
    
    while(iter.hasNext())
      System.out.print(iter.next() + " ");
    System.out.println();
    
    currentSolution = new HashSet<Integer>();
  }
  
  public static void main(String[] args)
  {
    try
    {
      int[] input = {17, 8, 32, 3, 6, 4, 2, 1};
      int X = 10;
      
      NumbersAddingUpToThreshold xx = new NumbersAddingUpToThreshold(input, X);
      
      long startTime = System.currentTimeMillis();
      xx.solve();
      long endTime = System.currentTimeMillis();
      
      System.out.println("Execution time = " + (endTime - startTime)  + " msec");
    }
    catch(Exception e)
    {
      e.printStackTrace();
    }
  }
}
