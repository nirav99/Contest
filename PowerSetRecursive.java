import java.util.*;

/**
 * Class to implement set of all subsets of a given string using recursive method
 * @author Nirav
 *
 */
public class PowerSetRecursive
{
  private HashSet<HashSet<String>> powerSet;
  private String input;
  
  public PowerSetRecursive(String input)
  {
    this.input = input;
    powerSet = new HashSet<HashSet<String>>();
    powerSet.add(new HashSet<String>()); // Add the empty set
  }
  
  public void getPowerSet()
  {
    String temp;
    for(int i = 0; i < input.length(); i++)
    {
      HashSet<String> newSet = new HashSet<String>();
      temp = new String(input.substring(i, i + 1));
      newSet.add(temp);
      powerSet.add(newSet);
      getPowerSetRecursive(newSet, i + 1);
    }
  }
  
  private void getPowerSetRecursive(HashSet<String> set, int level)
  {
    if(level >= input.length())
      return;
    else
    {
      String toAdd = input.substring(level, level + 1);
      HashSet<String> newSet = new HashSet<String>(set);
      newSet.add(toAdd);
      powerSet.add(newSet);
      getPowerSetRecursive(newSet, level + 1);
      getPowerSetRecursive(set, level + 1);
    }
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
      String input = "A";
      PowerSetRecursive psr = new PowerSetRecursive(input);
      psr.getPowerSet();
      psr.showAllSets();
    }
    catch(Exception e)
    {
      e.printStackTrace();
    }
  }
}
