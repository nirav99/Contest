/**
 * Performs complete string searches and prefix searches using a Trie
 * Assumes that the data is always lowercase letters.
 * Assuming there at n words with maximum length m,
 * Time complexity to build a Trie is O(mn)
 * Time complexity to search for a word / prefix is O(m)
 * @author nirav99
 *
 */
public class TrieSearch
{
  private TrieNode root;
  
  public TrieSearch()
  {
  	root = new TrieNode();
  }
  
  /**
   * Adds the given word to the Trie
   * @param word
   */
  public void addWord(String word)
  {
  	if(word == null || word.isEmpty())
  		return;
  	
  	word = word.toLowerCase();
  	
  	TrieNode trav = root;
  	
  	for(int i = 0; i < word.length(); i++)
  	{
  		// This is the index of the child node of the current TrieNode
  		int index = word.charAt(i) - 'a';
 // 		System.out.println(index + " " + word);
  		if(trav.children[index] == null)
  		{
  			trav.children[index] = new TrieNode();
  		}
  		trav = trav.children[index];
  	}
  	// Once the word is completely inserted, signal the node to indicate that it holds the last letter
  	trav.isLeaf = true;
  }
  
  /**
   * Searches the given word in the trie
   * @param word
   * @return
   */
  public boolean isWordPresent(String word)
  {
  	if(word == null || word.isEmpty())
  		return false;
  	
  	word = word.toLowerCase();
  	
  	TrieNode node = searchNode(word);
  	
  	// If the node is not null (implies that the word is present)
  	// and that node is a leaf node (implies that the word given was a complete word in the trie)
    return (node != null && node.isLeaf);
  }
  
  /**
   * Checks if the given prefix or a complete word is present in the Trie
   * @param prefix
   * @return
   */
  public boolean isPrefixPresent(String prefix)
  {
  	if(prefix == null || prefix.isEmpty())
  		return false;
  	
  	TrieNode node = searchNode(prefix.toLowerCase());
  	
  	return (node != null);
  }
  
  /**
   * Private helper method to search for a word in the trie
   * @param word
   * @return
   */
  private TrieNode searchNode(String word)
  {
  	TrieNode trav = root;
  	
  	for(int i = 0; i < word.length(); i++)
  	{
  		int index = word.charAt(i) - 'a';
  		TrieNode child = trav.children[index];
  		
  		if(child == null)
  			return null;
  		trav = child;
  	}
  	
  	// If the trav node is same as the root then the given word does not appear in the trie
  	return (trav != root) ? trav : null;
  }
  
  public static void main(String[] args)
  {
  	try
  	{
  		TrieSearch trieSearch = new TrieSearch();
  		trieSearch.addWord("to");
  		trieSearch.addWord("tea");
  		trieSearch.addWord("ted");
  		trieSearch.addWord("ten");
  		trieSearch.addWord("a");
  		trieSearch.addWord("in");
  		trieSearch.addWord("inn");
  		
  		String[] words = {"a", "ten", "in", "inn", "te"};
  		
  		for(String word : words)
  		{
  			System.out.println("Word : " + word + ", is present : " + trieSearch.isWordPresent(word));
  		}
  		
  		String[] prefixes = {"te", "in", "t"};
  		
  		for(String prefix : prefixes)
  		{
  			System.out.println("Prefix : " + prefix + ", is present : " + trieSearch.isPrefixPresent(prefix));
  		}
  	}
  	catch(Exception e)
  	{
  		System.err.println(e.getMessage());
  		e.printStackTrace();
  	}
  }
}

/**
 * A single node of the Trie
 * @author nirav99
 *
 */
class TrieNode
{
	boolean isLeaf; // Is it the last node ?
	TrieNode[] children; // Array of children
	
	TrieNode()
	{
		isLeaf = false;
		children = new TrieNode[26]; // One child per letter
	}
}
