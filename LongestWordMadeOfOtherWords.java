import java.util.*;

/**
 * Given a set of words, find the longest word that is composed of other words in the given set.
 * @author nirav99
 *
 */
public class LongestWordMadeOfOtherWords
{
  public static String getLongestWord(ArrayList<String> input)
  {
    Collections.sort(input, new LengthComparator());
    HashSet<String> wordSet = new HashSet<String>();
    
    for(String word : input)
    {
      wordSet.add(word.toLowerCase());
    }
    
    for(int i = 0; i < input.size(); i++)
    {
      for(int k = 1; k < input.get(i).length() - 1; k++)
      {
        String[] words = getSplitWords(input.get(i), k);
        
        if(words.length == 2 && wordSet.contains(words[0]) && wordSet.contains(words[1]))
          return input.get(i);
      }
    }
    
    return null;
  }
  
  /**
   * Splits the given word at the specified index and returns the split words.
   * @param input
   * @param splitIndex
   * @return
   */
  private static String[] getSplitWords(String input, int splitIndex)
  {
    String[] splitWords = new String[2];
    splitWords[0] = input.substring(0, splitIndex);
    splitWords[1] = input.substring(splitIndex);
    
    return splitWords;
  }
  
  public static void main(String[] args)
  {
    try
    {
      ArrayList<String> input = new ArrayList<String>();
      input.add("test");
      input.add("tester");
      input.add("testertest");
      input.add("testing");
      input.add("testingtester");
      
      System.out.println("Longest word composed of other words : " + getLongestWord(input));
    }
    catch(Exception e)
    {
      System.err.println(e.getMessage());
      e.printStackTrace();
    }
  }
}

/**
 * Sort Strings in descending order of length
 * @author nirav99
 *
 */
class LengthComparator implements Comparator<String>
{
  @Override
  public int compare(String s1, String s2)
  {
    if(s1.length() > s2.length())
      return -1;
    else
    if(s1.length() < s2.length())
      return 1;
    else
      return 0;
  }
}
