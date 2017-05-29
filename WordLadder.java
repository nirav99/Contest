import java.util.*;

/**
 * Given a start word, find the minimum of transformations to tranform it to the given end word.
 * Only one character substitution is allowed in one transformation.
 * Each intermediate word formed must exist in the given dictionary.
 * @author nirav99
 *
 */
public class WordLadder
{
  private final String ALPHABET = "abcdefghijklmnopqrstuvwxyz";
  
  private String startWord;
  private String endWord;
  private HashSet<String> dictionary;
  private HashSet<String> seenWords;
  
  public WordLadder(String startWord, String endWord, HashSet<String> dictionary)
  {
    this.dictionary = dictionary;
    this.startWord = startWord;
    this.endWord = endWord;
    this.seenWords = new HashSet<String>();
  }
  
  class State
  {
  	String word;
  	int numSteps;
  	
  	State(String word, int numSteps)
  	{
  		this.word = word;
  		this.numSteps = numSteps;
  	}
  }
  
  /**
   * Uses breadth-first-search to find the next set of words (i.e. words with 1 character substitution).
   */
  public void playLadder()
  {
    Queue<State> queue = new ArrayDeque<State>();
    queue.add(new State(startWord, 0));
    seenWords.add(startWord);
    
    String currWord = null;
    
    while(!queue.isEmpty())
    {
      State state = queue.poll();
      
      if(state.word.equals(endWord))
      {
      	System.out.println("Found end word " + endWord + " Num Transformations : " + state.numSteps);
      	return;
      }
      currWord = state.word;
      
      for(int i = 0; i < currWord.length(); i++)
      {
      	for(int j = 0; j < ALPHABET.length(); j++)
      	{
      		StringBuilder newWord = new StringBuilder(currWord);
      		newWord.replace(i, i + 1, ALPHABET.substring(j, j + 1));
      		
      		if(!seenWords.contains(newWord.toString()) && dictionary.contains(newWord.toString()))
      		{
//      			System.out.println("Adding : " + newWord.toString());
      			queue.add(new State(newWord.toString(), state.numSteps + 1));
      			seenWords.add(newWord.toString());
      		}
      	}
      }
    }
  }
  
	public static void main(String[] args)
	{
		try
		{
      String[] wordList = {"hot","dot","dog","lot","log","cog"};
      String startWord = "hit";
      String endWord = "cog";
      
      HashSet<String> dictionary = new HashSet<String>();
      dictionary.addAll(Arrays.asList(wordList));
      
      WordLadder wordLadder = new WordLadder(startWord, endWord, dictionary);
      wordLadder.playLadder();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
