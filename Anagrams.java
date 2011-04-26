/**
 * Class to check if a given set of words are anagrams of each other
 * @author nirav99
 *
 */

import java.util.Arrays;

public class Anagrams
{
  public static void main(String[] args)
  {
    char content[] = null;
    
    for(int i = 0; i < args.length; i++)
    {
      System.out.println(args[i]);
      content = args[i].toCharArray();
      Arrays.sort(content);
      args[i] = new String(content);
    }

    boolean isAnagram = true;

   for(int i = 0; i < args.length -1 && isAnagram == true; i++)
   {
     if(args[i].compareToIgnoreCase(args[i + 1]) != 0)
     {
       isAnagram = false;
     }
   }

   if(isAnagram == true)
   {
     System.out.println("The words are anagrams");
   }
   else
   {
     System.out.println("The words are not anagrams");
   }
  }
}
