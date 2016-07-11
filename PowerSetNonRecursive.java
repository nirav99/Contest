import java.util.*;

/**
 * Non-recursive implementation of power set
 * @author Nirav
 *
 */
public class PowerSetNonRecursive
{
  private String input;
  private HashSet<HashSet<String>> powerSet;
  
  public PowerSetNonRecursive(String input)
  {
    this.input = input;
    powerSet = new HashSet<HashSet<String>>();
  }
  
  public void getAllPowerSets()
  {
    int N = input.length();
    
    for(int i = 0; i < Math.pow(2, N); i++)
    {
      addSet(i);
    }
  }
  
  private void addSet(int number)
  {
    HashSet<String> set = new HashSet<String>();
    
    int index = input.length() - 1;
    while(number > 0)
    {
      if((number & 1) != 0)
        set.add(input.substring(index, index + 1));
      number = number >> 1;
      index--;
    }
    powerSet.add(set);
  }
  
  public void showAllSets()
  {
    Iterator<HashSet<String>> iter = powerSet.iterator();
    
    while(iter.hasNext())
    {
      printSet(iter.next());
    }
  }
  
  private void printSet(HashSet<String> set)
  {
    if(set.size() < 1)
      System.out.println("{ }");
    else
    {
      System.out.print("{ ");
      for(String s : set)
        System.out.print(s + " ");
      System.out.println("}");
    }
  }
  
  public static void main(String[] args)
  {
    try
    {
      String input = "ABC";
      PowerSetNonRecursive psnr = new PowerSetNonRecursive(input);
      psnr.getAllPowerSets();
      psnr.showAllSets();
    }
    catch(Exception e)
    {
      e.printStackTrace();
    }
  }
}
