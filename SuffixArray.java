/**
 * Class to search a string based on suffix array lookup
 * @author niravs
 *
 */
public class SuffixArray
{
  private String dataString = null; // String to build the array for
  private int suffixList[] = null;  // Suffix array
  
  /**
   * Class constructor
   * @param data - the original string 
   */
  public SuffixArray(String data)
  {
    dataString = data;
    suffixList = new int[dataString.length()];
    
    buildSA();
    sortSA();
    System.out.println("Sorted Suffix Array : ");
    printSA();
  }
  
  /**
   * Method to build the suffix array
   */
  private void buildSA()
  {
    for(int i = 0; i < dataString.length(); i++)
    {
      suffixList[i] = i;
    }
  }
  
  /**
   * Method to print the suffix array
   */
  private void printSA()
  {
    int idx;
    for(int i = 0; i < dataString.length(); i++)
    {
      idx = suffixList[i];
      System.out.println(dataString.substring(idx));
    }
  }
  
  /**
   * Method to sort the suffix array based on string comparison
   */
  private void sortSA()
  {
    int len = dataString.length();
    String s1 = null;
    String s2 = null;
    int temp;
    
    for(int i = 0; i < len; i++)
    {
      for(int j = i + 1; j < len; j++)
      {
        s1 = dataString.substring(suffixList[i]);
        s2 = dataString.substring(suffixList[j]);
        
        if(s1.compareTo(s2) > 0)
        {
          temp = suffixList[i];
          suffixList[i] = suffixList[j];
          suffixList[j] = temp;
        }
      }
    }
  }
  
  /**
   * Method to search the suffix array using binary search
   * @param searchString - String to search for
   * @return
   */
  public boolean searchSA(String searchString)
  {
    int low  = 0;
    int high = dataString.length() - 1;
    int mid;
    int idx;
    
    while(low < high)
    {
      mid = low + (high - low) / 2;
      
      idx = suffixList[mid];
      
      if(dataString.substring(idx).startsWith(searchString))
      {
        return true;
      }
      else
      if(dataString.substring(idx).compareTo(searchString) <= 0)
      {
        low = mid + 1;
      }
      else
      {
        high = mid - 1;
      }
    }
    return false;
  }
  
  public static void main(String args[])
  {
    if(args.length != 2)
    {
      printUsage(args);
      System.exit(-1);
    }
    
    SuffixArray sa = new SuffixArray(args[0]);
    
    if(true == sa.searchSA(args[1]))
    {
      System.out.println("Search String : " + args[1] + " is present");
    }
    else
    {
      System.out.println("Search String : " + args[1] + " is NOT present");
    }
  }
  
  public static void printUsage(String args[])
  {
    System.err.println("Usage : ");
    System.err.println("java SuffixArray originalstring searchstring");
    System.err.println("e.g. java SuffixArray banana ana");
  }
}
