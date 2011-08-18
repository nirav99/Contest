import java.util.*;
/**
 * Class to study dynamic programming method to partially align 2
 * 2 strings using Smith-Waterman algorithm.
 */
public class SmithWaterman 
{
  private int matchCost    = 2;  // Cost of match
  private int mismatchCost = -1; // cost of mismatch
  private String sequence1;      // 1st sequence - iIndex
  private String sequence2;      // 2nd sequence - jIndex
  private int len1;              // Length of sequence 1
  private int len2;              // Length of sequence 2
  private int H[][] = null;      // Max cost matrix
  
  private int maxValue = -1;     // Max value in matrix
  private int iIndex   = -1;     //"i" index of max value  
  private int jIndex   = -1;     // "j" index of max value
  private StringBuilder align1;  // Alignment result for sequence 1
  private StringBuilder align2;  // Alignment result for sequence 2

  private int iPath[] = null;    // Back-tracking path for sequence 1
  private int jPath[] = null;    // Back-tracking path for sequence 2

  /**
   * Class constructor
   * @param sequence1 - Sequence 1 to align 
   * @param sequence2 - Sequence 2 to align
   */
  public SmithWaterman(String sequence1, String sequence2)
  {
    this.sequence1 = sequence1;
    this.sequence2 = sequence2;
    
    len1 = sequence1.length();
    len2 = sequence2.length();
    
    H = new int[len1 + 1][len2 + 1];
    
    for(int i = 0; i < len1 + 1; i++)
    {
      H[i][0] = 0;
    }
    for(int j = 0; j < len2 + 1; j++)
    {
      H[0][j] = 0;
    }
    
    System.out.println(sequence1);
    System.out.println(sequence2);

    int temp = sequence1.length();
    if(temp < sequence2.length())
      temp = sequence2.length();

    temp = temp * 2; 
    iPath = new int[temp];
    jPath = new int[temp];
   
//    System.out.println("Length of path array : " + temp);
  }
  
  /**
   * Method to perform the alignment
   */
  public void align()
  {
    // Calculate max value for each location
    // and fill the matrix
    for(int i = 1; i < len1 + 1; i++)
    {
      for(int j = 1; j < len2 + 1; j++)
      {
        H[i][j] = maximum(i, j);
      }
    }

    for(int i = 0; i < len1 + 1; i++)
    {
      for(int j = 0; j < len2 + 1; j++)
      {
        System.out.print(H[i][j] + " ");
      }
      System.out.println("");
    }

    findMaxValue();
    backTrack();
  }
  
  /**
   * Private helper method to find maximum value
   * for a given matrix cell based on smith waterman
   * local alignment formula
   */
  private int maximum(int i , int j)
  {
    int max = 0;
    int cost = 0;
    
    if(sequence1.charAt(i -1) == sequence2.charAt(j -1))
			cost = matchCost;
    else
			cost = mismatchCost;
    
    int match = H[i -1][j - 1] + cost;
    int deletion = H[i -1][j] + mismatchCost;
    int insertion = H[i][j -1] + mismatchCost;
    
    if(max < match)
			max = match;
    if(max < deletion)
			max = deletion;
    if(max < insertion)
			max = insertion;
    return max;
  }
 
  /**
   * Private helper method to find the largest value in the cost matrix.
   * In case of multiple matches, the value with larger index is reported.
   * This method assigns member variables iIndex and jIndex to i and j values
   * of the maximum value cell.
   */
  private void findMaxValue()
  {
    int max = -1;
    int idx1 = -1;
    int idx2 = -1;

    for(int i = 1; i < len1 + 1; i++)
    {
      for(int j = 1; j < len2 + 1; j++)
      {
        if(H[i][j] >= max)
        {
          max = H[i][j];
          idx1 = i;
          idx2 = j;
        }
      }
    }
    iIndex = idx1;
    jIndex = idx2;
    maxValue = max;
  }
  
  /**
   * Private helper method to find the maximum value path terminating in
   * the maximum value cell in the matrix.
   */
  private void backTrack()
  {
    boolean foundZeroScore = false;
    int left, top, diag;

    int iPathIdx = 0;
    int jPathIdx = 0;

    iPath[iPathIdx++] = iIndex;
    jPath[jPathIdx++] = jIndex;
     
    while(true)
    {
    //  System.out.println("iIndex = " + iIndex + " " + sequence1.charAt(iIndex
    //  - 1) + " jIndex = " + jIndex + " " +  sequence2.charAt(jIndex - 1));

      if(H[iIndex][jIndex] == 0)
      {
        System.out.println("found zero");
        foundZeroScore = true;
        break;
      }
      if(iIndex == 1 && jIndex == 1)
      {
        break;
      }
      if(iIndex > 1 && jIndex > 1)
      {
        diag = H[iIndex -1][jIndex - 1];
      }
      else
      {
        diag = 0;
      }

      if(iIndex > 1)
      {
        top = H[iIndex -1][jIndex];
      }
      else
      {
        top = 0;
      }
 
      if(jIndex > 1)
      {
        left = H[iIndex][jIndex - 1];
      }
      else
      {
        left = 0;
      }
     
      if(diag == 0 && top == 0 && left == 0)
        break; 
      if(diag >= top && diag >= left && diag > 0)
      {
			iIndex--;
			jIndex--;
        iPath[iPathIdx++] = iIndex;
        jPath[jPathIdx++] = jIndex;
      }
      else
      if(top > diag && top >= left && top > 0)
      {
        iIndex--;
        iPath[iPathIdx++] = iIndex;
        jPath[jPathIdx++] = jIndex;
      }
      else
      if(left > diag && left >= top && left > 0)
      {
        jIndex--;
        iPath[iPathIdx++] = iIndex;
        jPath[jPathIdx++] = jIndex;
      }
    }

    for(int i = iPath.length - 1; i >= 0; i--)
    {
      if(iPath[i] > 0 && jPath[i] > 0)
      {
        System.out.println(iPath[i] + " , " + jPath[i]);
      }
    }

    align1 = new StringBuilder();
    align2 = new StringBuilder();
    buildAlignmentSequence(align1, sequence1, iPath);
    buildAlignmentSequence(align2, sequence2, jPath);
    System.out.println();
    System.out.println();
    System.out.println("The sequences are : ");
    System.out.println(sequence1);
    System.out.println(sequence2);
    System.out.println();
    System.out.println("The Alignments are : ");
    System.out.println(align1);
    System.out.println(align2);
  }
  
  /**
   * Private helper method to build the alignment strings for the given
   * sequences.
   */
  private void buildAlignmentSequence(StringBuilder align, String sequence, int pathIndex[])
  {
    int i;

    for(int idx = 0; idx < pathIndex.length / 2; idx++)
    {
      i = pathIndex[idx];
      pathIndex[idx] = pathIndex[pathIndex.length -1 -idx];
      pathIndex[pathIndex.length -1 -idx] = i;
    }

    for(int idx = 0; idx < pathIndex.length; idx+=1)
    {
      if(pathIndex[idx] > 0)
      {
        if(idx > 0 && pathIndex[idx -1] == pathIndex[idx])
          align.append("-");
        else
          align.append(sequence.charAt(pathIndex[idx] -1));
      }
    } 
  }

  public static void main(String[] args) 
  {
    if(args.length != 2)
    {
      printUsage();
      System.exit(-1);
    }
    String sequence1 = args[0];
    String sequence2 = args[1];
 
    SmithWaterman sm = new SmithWaterman(sequence1, sequence2);
    sm.align();
  }
 
  public static void printUsage()
  {
    System.out.println("Usage:");
    System.out.println("java SmithWaterman sequence1 sequence2");
  }
}
