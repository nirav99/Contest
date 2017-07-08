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
  
  private HashMap<String, String> backTrackMap;
  
  public WordLadder(HashSet<String> dictionary)
  {
    this.dictionary = dictionary;
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
  public void playLadder(String startWord, String endWord)
  {
    this.startWord = startWord;
    this.endWord = endWord;
    
    this.seenWords = new HashSet<String>();
    backTrackMap = new HashMap<String, String>();
    
    System.out.println("\nStart word : " + startWord + " End word : " + endWord);
    Queue<State> queue = new ArrayDeque<State>();
    queue.add(new State(startWord, 0));
    seenWords.add(startWord);
    
    String currWord = null;
    
    while(!queue.isEmpty())
    {
      State state = queue.poll();
      
      if(state.word.equals(endWord))
      {
        System.out.println("Found end word " + endWord + "\nNum Transformations : " + state.numSteps);
        listTransformations();
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
//            System.out.println("Adding : " + newWord.toString());
            queue.add(new State(newWord.toString(), state.numSteps + 1));
            seenWords.add(newWord.toString());
            backTrackMap.put(newWord.toString(), currWord);
          }
        }
      }
    }
  }
  
  /**
   * After the word is transformed, prints all the transformations
   */
  private void listTransformations()
  {
    ArrayList<String> list = new ArrayList<String>();
    
    list.add(endWord);
    
    String currWord = endWord;
 
    while(true)
    {
      String prevWord = backTrackMap.get(currWord);
      list.add(prevWord);
      currWord = prevWord;
      
      if(currWord.equals(startWord))
        break;
    }
    
    for(int i = list.size() - 1; i > 0; i--)
      System.out.print(list.get(i) + " -> ");
    System.out.println(list.get(0));
  }
  
  public static void main(String[] args)
  {
    try
    {
      String[] wordList = {"hot","dot","dog","lot","log","cog", "lamp", "camp", "line", "lime", "limp", "like", "pit", "pot"};
      
      HashSet<String> dictionary = new HashSet<String>();
      dictionary.addAll(Arrays.asList(wordList));
      
      WordLadder wordLadder = new WordLadder(dictionary);
      wordLadder.playLadder("hit", "cog");
      wordLadder.playLadder("damp", "like");
      wordLadder.playLadder("hit", "pot");
    }
    catch(Exception e)
    {
      e.printStackTrace();
    }
  }
}
