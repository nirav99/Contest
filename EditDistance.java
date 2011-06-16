/**
 * Class to validate that given set of strings are at least k-edit distance
 * apart.
 */
import java.util.*;
import java.io.*;

/**
 * @author niravs
 *
 */
public class EditDistance
{
  private ArrayList<String>stringList  = null; // List of strings to check for edit distance
  private BufferedReader reader        = null; // To read the input file
  private int minEditDistance          = 2;    // Min edit distance required
  
  public EditDistance(String fileName) throws IOException
  {
    String line;
    boolean error = false;
    reader = new BufferedReader(new FileReader(fileName));
    
    stringList = new ArrayList<String>();
    
    while((line = reader.readLine()) != null)
    {
      stringList.add(line.trim());
    }
    
    for(int i = 0; i < stringList.size(); i++)
    {
      for(int j = i + 1; j < stringList.size(); j++)
      {
        if(false == dist2Apart(stringList.get(i), stringList.get(j)))
        {
          System.err.println("Strings " + stringList.get(i) + " " + stringList.get(j) + " are not dist-k apart");
          System.err.println("k = " + minEditDistance);
          error = true;
        }
      }
    }
    if(!error)
      System.out.println("No errors found");
  }

  /**
   * Method to check if two strings are at least 2-edit distance apart
   * @param s1
   * @param s2
   * @return
   */
  private boolean dist2Apart(String s1, String s2)
  {
    int diffLen = s1.length() - s2.length();
    
    if(diffLen < 0)
      diffLen *= -1;
    
    if(diffLen >= minEditDistance)
      return true;
    
    int diffCount = diffLen;
    
    for(int i = 0; i < Math.min(s1.length(), s2.length()); i++)
    {
      if(s1.charAt(i) != s2.charAt(i))
      {
        diffCount++;
        
        if(diffCount >= minEditDistance)
          return true;
      }
    }
    return false;
  }
  
  /**
   * @param args
   */
  public static void main(String[] args)
  {
    if(args.length != 1)
    {
      printUsage();
      System.exit(-1);
    }
    try
    {
      EditDistance ed = new EditDistance(args[0]);
    }
    catch(Exception e)
    {
      System.err.println(e.getMessage());
      e.printStackTrace();
    }
  }

  public static void printUsage()
  {
    System.err.println("Usage:");
    System.err.println("java EditDistance FileName");
    System.err.print("FileName - File containing the strings to check, one");
    System.err.println(" string per line");
  }
}

