/**
 * Given a set of strings, find the longest common prefix.
 * Time complexity:
 * M = length of the largest string
 * N = total number of strings (words)
 * 1) To add all the words to a trie O(mn)
 * 2) To perform a walk on Trie to find a prefix O(m)
 * @author nirav99
 *
 */
public class LongestCommonPrefix
{
  public static void main(String[] args)
  {
  	try
  	{
  		Trie trie = new Trie();
  		String[] setOfWords = {"geeksforgeeks", "geeks", "geek", "geezer"};
  		System.out.println("Trie built");
  		
  		for(String word : setOfWords)
  			trie.addWord(word);
  		
  		System.out.println("The longest common prefix : " + trie.longestCommonPrefix());
  	}
  	catch(Exception e)
  	{
  		e.printStackTrace();
  	}
  }
}

class Trie
{
	private TrieNode root;
	
	public Trie()
	{
		root = new TrieNode();
	}
	
	public void addWord(String word)
	{
		if(word == null || word.isEmpty())
			return;
		word = word.toLowerCase();
		
		TrieNode trav = root;
		
		for(int i = 0; i < word.length(); i++)
		{
			int index = word.charAt(i) - 'a';
			
		  if(trav.children[index] == null)
		  {
		  	trav.children[index] = new TrieNode();
		  }
		  trav = trav.children[index];
		}
		trav.isLeaf = true;
	}
	
	/**
	 * Performs a walk on the trie and as long as each node has exactly one child, continues storing the characters.
	 * These characters form the longest common prefix.
	 * @return
	 */
	public String longestCommonPrefix()
	{
		StringBuilder prefix = new StringBuilder();
		
		for(TrieNode trav = root; trav != null; )
		{
			if(countChildren(trav) == 1 && !trav.isLeaf)
      {
				int childIndex = getIndexOfFirstChild(trav);
	      char ch = (char) ('a' + childIndex);
	      prefix.append(ch);
	      trav = trav.children[childIndex];
      }
			else
				break;
		}
		
		return prefix.length() > 0 ? prefix.toString() : "";
	}
	
	/**
	 * Counts the number of children of the given trie node
	 * @param trav
	 * @return
	 */
	private int countChildren(TrieNode trav)
	{
		int count = 0;
	  for(int i = 0; i < 26; i++)
	  {
	  	if(trav.children[i] != null)
	  		count++;
	  }
	  
	  return count;
	}
	
	private int getIndexOfFirstChild(TrieNode trav)
	{
		for(int i = 0; i < 26; i++)
		{
			if(trav.children[i] != null)
				return i;
		}
		return -1;
	}
	/**
	 * A single Trie node
	 * @author nirav99
	 *
	 */
	class TrieNode
	{
		boolean isLeaf;
		TrieNode[] children;
		
		TrieNode()
		{
			isLeaf = false;
			children = new TrieNode[26];
		}
	}
}

