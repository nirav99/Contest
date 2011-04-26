/**
 * Class to check if a given set of words are anagrams of each other
 * @author nirav99
 *
 */

import java.util.Arrays;

public class Anagrams
{
  
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
	  char content[];
	  
    for(int i = 0; i < args.length; i++)
    {
		  System.out.println(args[i]);
			content = args[i].toCharArray();
			Arrays.sort(content);
			args[i] = new String(content);
    }
      
    boolean notAnagram = false;
      
    for(int i = 0; !notAnagram && i < args.length - 1; i++)
    {
		 if(args[i].compareToIgnoreCase(args[i + 1]) != 0)
		 {
		  notAnagram = true;
		 }
    }

    if(notAnagram == false)
    {
		 System.out.println("The words are anagrams");
    }
    else
    {
		 System.out.println("The words are not anagrams");
    }
	}
}
