import java.util.*;

public class MaxSumPathThroughTriangle
{
  private int[][] triangle;
  private int maxSum = 0;
  
  private HashMap<String, Integer> sumMap;
  
  public MaxSumPathThroughTriangle(int[][] triangle)
  {
    this.triangle = triangle;
    sumMap = new HashMap<String, Integer>();
    calculateSum();
  }
  
  public int maxSum()
  {
    return this.maxSum;
  }
  
  private void calculateSum()
  {
    int[] colArray;
    int currSum;
    
    
    for(int i = 0; i < triangle.length; i++)
    {
      colArray = triangle[i];
      
      for(int j = 0; j < colArray.length; j++)
      {
        currSum = colArray[j] + maxParentsSum(i, j, colArray.length);
        
        putSum(i, j, currSum);
        
        if(currSum > maxSum)
          maxSum = currSum;
      }
    }
    
    Set<Map.Entry<String, Integer>> entrySet = sumMap.entrySet();
    
    System.out.println("Hashmap values = ");
    for(Map.Entry<String, Integer> entry : entrySet)
      System.out.println(entry.getKey() + " --> " + entry.getValue());
  }
  
  private int maxParentsSum(int i, int j, int length)
  {
    int parentRow = i - 1;
    int parentCol;
    int[] parentSum = new int[3];
    int parentLength = length - 1;
    
    if(parentRow >= 0)
    {
      parentCol = j -1;
      if(parentCol >= 0)
        parentSum[0] = getSum(parentRow, parentCol);
      parentCol = j;
      
      if(parentCol < parentLength)
      {
        parentSum[1] = getSum(parentRow, parentCol);
        parentCol = j + 1;
        if(parentCol < parentLength)
          parentSum[2] = getSum(parentRow, parentCol);
      }
    }
    
    int max = parentSum[0];
    
    if(parentSum[1] > max)
      max = parentSum[1];
    if(parentSum[2] > max)
      max = parentSum[2];
    
    return max;
  }
  
  private int getSum(int i, int j)
  {
    String key = i + "_" + j;
    
    Integer value = sumMap.get(key);
    
    return (value != null) ? value : 0;
  }
  
  private void putSum(int i, int j, int sum)
  {
    String key = i + "_" + j;
    sumMap.put(key, sum);
  }
  
  public static void main(String[] args)
  {
    try
    {
      int[][] triangle = { {1}, {2,3}, {4, 5, 6}, {7, 10, 9, 8}};
   
      MaxSumPathThroughTriangle maxSum = new MaxSumPathThroughTriangle(triangle);
      System.out.println("MAX SUM = " + maxSum.maxSum());
    }
    catch(Exception e)
    {
      System.err.println(e.getMessage());
      e.printStackTrace();
    }
  }
}
